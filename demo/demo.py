import requests
import json


ggHost="dev1.gluu.org"
ceHost="ce-dev6.gluu.org"
host="demo.example.com"
path="/roadmap"

#------------Create API
# 1. Resource configuration (Kong API configuration)
requests.delete("http://"+ggHost+":8001/apis/demo_api")
response= requests.post("http://"+ggHost+":8001/apis",headers={"Content-Type":"application/x-www-form-urlencoded"},params={"name":"demo_api",
                                                                                                                           "hosts":host,
                                                                                                                           "upstream_url":"https://gluu.org"})
print("Status: "+str(response.status_code))
print(json.dumps(response.json(), indent=2))

# 2. Configuration of oAuth plugin
response= requests.post("http://"+ggHost+":8001/apis/demo_api/plugins",headers={"Content-Type":"application/x-www-form-urlencoded"},
                        params={"name":"gluu-oauth2-client-auth","config.op_server":"https://"+ceHost,"config.oxd_http_url":"https://localhost:8443"})
print("Status: "+str(response.status_code))
print(json.dumps(response.json(), indent=2))

# 3. Securing RS with UMA
response= requests.post("http://"+ggHost+":8001/apis/demo_api/plugins",headers={"Content-Type":"application/x-www-form-urlencoded"}, params={"name":"gluu-oauth2-rs","config.oxd_host":"https://localhost:8443","config.uma_server_host":"https://"+ceHost,
                                                                                                                                             "config.protection_document":"[ { \"path\": \""+path+"\", \"conditions\": [ { \"httpMethods\": [ \"GET\" ], \"scope_expression\": {\"rule\": { \"and\": [ { \"var\": 0 } ] }, \"data\": [ \"http://photoz.example.com/dev/actions/view\" ] } } ] } ]"})
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
                        params={"name":"uma_consumer_cred","op_host":ceHost,"oxd_http_url":"https://localhost:8443","uma_mode":"true"})
print("Status: "+str(response.status_code))
print(json.dumps(response.json(), indent=2))

consumer_oxd_id = response.json()['oxd_id']
consumer_client_id = response.json()['client_id']
consumer_client_secret = response.json()['client_secret']

#--------- LOGIN
# 1. LogIn Consumer
response= requests.post("https://"+ggHost+":8443/get-client-token",headers={"Content-Type":"application/json"},
                        json={"oxd_id":consumer_oxd_id,"client_id":consumer_client_id,"client_secret":consumer_client_secret,
                                      "op_host":"https://"+ceHost, "scope":["uma_protection","openid"]}, verify=False)
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


#------------- rpt
# 4. Get RPT token
response= requests.post("https://"+ggHost+":8443/uma-rp-get-rpt",headers={"Content-Type":"application/json","Authorization":"Bearer "
                                                                                                                                 ""+consumer_access_token},
                        json={"oxd_id":consumer_oxd_id,"ticket":ticket,"protection_access_token":consumer_access_token}, verify=False)
print("Status: "+str(response.status_code))
print(response.request.body)
print(response.request.headers)
print(json.dumps(response.json(), indent=2))
rpt = response.json()['data']['access_token']



#--------------get secured API--------
response= requests.get("http://"+ggHost+":8000"+path,headers={"Host":host,"Authorization":"Bearer "+rpt})
print("Status: "+str(response.status_code))
print(response.request.body)
print(response.request.headers)
print(json.dumps(response.json(), indent=2))

