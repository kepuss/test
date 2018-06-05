import requests
import json

ggHost="dev1.gluu.org"

response= requests.post("http://"+ggHost+":8001/apis",headers={"Content-Type":"application/x-www-form-urlencoded"},params={"name":"demo_api","hosts":"demo.example.com","upstream_url":"https://gluu.org"})
print(response.status_code)
print(json.dumps(response.json(), indent=2))


 # curl -X POST http://$GG_HOST:8001/apis --data "name=$APINAME" --data "hosts=$HOST" --data "upstream_url=$UPSTREAMURL"
 #    curl -X POST http://$GG_HOST:8001/apis/$APINAME/plugins --data "name=gluu-oauth2-client-auth" --data "config.op_server=https://ce-dev6.gluu.org" --data "config.oxd_http_url=https://localhost:8443"
# pyth