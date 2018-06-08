import requests
import json
import os
import time
import urlparse


claimsGathering=False
# It is good to gen scopes before test
if claimsGathering:
    scope="new_demo_scope"
else:
    scope="non-gather"

ggHost="demo.gluu.org"
ceHost="ce-dev6.gluu.org"
host="demo.example.com"
path="/posts"
upsreamUrl="https://jsonplaceholder.typicode.com"
claimsGatheringUrl="https://client.example.com/cb"

#------------Create API
# 1. Resource configuration (Kong API configuration)
requests.delete("http://"+ggHost+":8001/apis/demo_api")
response= requests.post("http://"+ggHost+":8001/apis",headers={"Content-Type":"application/x-www-form-urlencoded"},params={"name":"demo_api",
                                                                                                                           "hosts":host,
                                                                                                                           "upstream_url":upsreamUrl})
print("Status: "+str(response.status_code))
print(json.dumps(response.json(), indent=2))

# 2. Configuration of oAuth plugin
response= requests.post("http://"+ggHost+":8001/apis/demo_api/plugins",headers={"Content-Type":"application/x-www-form-urlencoded"},
                        params={"name":"gluu-oauth2-client-auth","config.op_server":"https://"+ceHost,"config.oxd_http_url":"https://localhost:8443"})
print("Status: "+str(response.status_code))
print(json.dumps(response.json(), indent=2))

# 3. Securing RS with UMA
response= requests.post("http://"+ggHost+":8001/apis/demo_api/plugins",headers={"Content-Type":"application/x-www-form-urlencoded"}, params={"name":"gluu-oauth2-rs","config.oxd_host":"https://localhost:8443","config.uma_server_host":"https://"+ceHost,
                                                                                                                                             "config.protection_document":"[ { \"path\": \""+path+"\", \"conditions\": [ { \"httpMethods\": [ \"GET\" ], \"scope_expression\": {\"rule\": { \"and\": [ { \"var\": 0 } ] }, \"data\": [ \""+scope+"\" ] } } ] } ]"})
print("Status: "+str(response.status_code))
print(json.dumps(response.json(), indent=2))

uma_oxd_id = response.json()['config']['oxd_id']
uma_client_id = response.json()['config']['client_id']
uma_client_secret = response.json()['config']['client_secret']

#---------Create consumer
# 1. Register consumer
requests.delete("http://"+ggHost+":8001/consumers/uma_client")
response= requests.post("http://"+ggHost+":8001/consumers",headers={"Content-Type":"application/x-www-form-urlencoded"},
                        params={"username":"uma_client"})
print("Status: "+str(response.status_code))
print(json.dumps(response.json(), indent=2))

# 2. Register UMA credentials
response= requests.post("http://"+ggHost+":8001/consumers/uma_client/gluu-oauth2-client-auth/",headers={"Content-Type":"application/x-www-form-urlencoded"},
                        params={"name":"uma_consumer_cred"+str(time.time()),"op_host":ceHost,"oxd_http_url":"https://localhost:8443","uma_mode":"true"})
print("Status: "+str(response.status_code))
print(json.dumps(response.json(), indent=2))

consumer_oxd_id = response.json()['oxd_id']
consumer_client_id = response.json()['client_id']
consumer_client_secret = response.json()['client_secret']

#--------- LOGIN
# 1. LogIn Consumer
response= requests.post("https://"+ggHost+":8443/get-client-token",headers={"Content-Type":"application/json"},
                        json={"oxd_id":consumer_oxd_id,"client_id":consumer_client_id,"client_secret":consumer_client_secret,
                                      "op_host":"https://"+ceHost, "scope":["uma_protection","openid, demo_scope"]}, verify=False)
print("Status: "+str(response.status_code))
print(json.dumps(response.json(), indent=2))
consumer_access_token = response.json()['data']['access_token']


#  2. LogIn UmaResource
response= requests.post("https://"+ggHost+":8443/get-client-token",headers={"Content-Type":"application/json"},
                        json={"oxd_id":uma_oxd_id,"client_id":uma_client_id,"client_secret":uma_client_secret,"op_host":"https://"+ceHost,
                              "scope":["uma_protection","openid"]}, verify=False)
print("Status: "+str(response.status_code))
print(json.dumps(response.json(), indent=2))
uma_access_token = response.json()['data']['access_token']

#----------Ticket
# 3. Get resource ticket
response= requests.post("https://"+ggHost+":8443/uma-rs-check-access",headers={"Content-Type":"application/json","Authorization":"Bearer "
                                                                                                                                 ""+uma_access_token},
                        json={"oxd_id":uma_oxd_id,"path":path,"http_method":"GET"}, verify=False)
print("Status: "+str(response.status_code))
print(json.dumps(response.json(), indent=2))
ticket = response.json()['data']['ticket']




#--------------------
#--------------------
#----------CLAIMS GATHERING
#
#   Remember to add policy in ce
#
#--------------get claims gathering url
if claimsGathering:
    # ------------- rpt
    # 4. Get RPT token
    response = requests.post("https://" + ggHost + ":8443/uma-rp-get-rpt",
                             headers={"Content-Type": "application/json", "Authorization": "Bearer "
                                                                                           "" + consumer_access_token},
                             json={"oxd_id": consumer_oxd_id, "ticket": ticket, "scope": [scope],
                                   "protection_access_token": consumer_access_token}, verify=False)
    print("Status: " + str(response.status_code))
    print(response.request.body)
    print(response.request.headers)
    print(json.dumps(response.json(), indent=2))
    need_info_ticket = response.json()['data']['details']['ticket']


    response= requests.post("https://"+ggHost+":8443/uma-rp-get-claims-gathering-url",headers={"Content-Type":"application/json","Authorization":"Bearer "
                                                                                                                                 ""+consumer_access_token},
                        json={"oxd_id":consumer_oxd_id,"ticket":need_info_ticket,"claims_redirect_uri":claimsGatheringUrl}, verify=False)
    print("Status: "+str(response.status_code))
    print(json.dumps(response.json(), indent=2))
    claims_url = response.json()['data']['url']
    parsed = urlparse.urlparse(claims_url)
    claims_client_id= urlparse.parse_qs(parsed.query)['client_id']
    print ("Add claims gathering url "+claimsGatheringUrl+" for client id "+claims_client_id[0]+ " in "+ceHost)
    print("Open claims gathering url in browser - " + claims_url)
    redirectUrl = raw_input("Please enter response url: ")
    parsedRedirectUrl = urlparse.urlparse(redirectUrl)
    ticket= urlparse.parse_qs(parsedRedirectUrl.query)['ticket'][0]
#------------------
#------------------



#------------- rpt
# 4. Get RPT token
response= requests.post("https://"+ggHost+":8443/uma-rp-get-rpt",headers={"Content-Type":"application/json","Authorization":"Bearer "
                                                                                                                                 ""+consumer_access_token},
                        json={"oxd_id":consumer_oxd_id,"ticket":ticket,"scope":[scope],"protection_access_token":consumer_access_token}, verify=False)
print("Status: "+str(response.status_code))
print(response.request.body)
print(response.request.headers)
print(json.dumps(response.json(), indent=2))
rpt = response.json()['data']['access_token']


#--------------get secured API--------
response= requests.get("http://"+ggHost+":8000"+path,headers={"Host":host, "Authorization": 'Bearer {}'.format(rpt)})
print("Status: "+str(response.status_code))
print(response.request.body)
print(response.request.headers)
print(json.dumps(response.json(), indent=2))












