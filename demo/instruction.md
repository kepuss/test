#Gluu Gateway UMA study case.


### 1. Parties

![alt text](https://drive.google.com/uc?export=view&id=18K_UjNZIWw-86MuSEUKEYO3_0rJxtYiU "Logo Title Text 1")


### 2. RS Configuration
1. Resource configuration (Kong API configuration)
````
curl -X POST http://gg.example.com:8001/apis 
    --data "name=demo_api" 
    --data "hosts=demo_host" 
    --data "upstream_url=https://gluu.org"
````

2. Configuration of oAuth plugin

````
curl -X POST http://gg.example.com:8001/apis/demo_api/plugins 
    --data "name=gluu-oauth2-client-auth" 
    --data "config.op_server=https://ce-gluu.example.com" 
    --data "config.oxd_http_url=https://localhost:8443"

````

3. Securing RS with UMA

````
curl -X POST --url http://gg.example.com:8001/apis/demo_api/plugins/ 
    --data "name=gluu-oauth2-rs" 
    --data "config.oxd_host=https://localhost:8443" 
    --data "config.uma_server_host=https://ce-gluu.example.com" 
    --data "config.protection_document=[ { \"path\": \"$API_PATH\", \"conditions\": [ { \"httpMethods\": [ \"GET\" ], \"scope_expression\": { \"rule\": { \"and\": [ { \"var\": 0 } ] }, \"data\": [ \"http://photoz.example.com/dev/actions/view\" ] } } ] } ]"`
````

protection_document (pretty printed)
```json
[
  {
    "path": "$API_PATH",
    "conditions": [
      {
        "httpMethods": [
          "GET"
        ],
        "scope_expression": {
          "rule": {
            "and": [
              {
                "var": 0
              }
            ]
          },
          "data": [
            "http:\/\/photoz.example.com\/dev\/actions\/view"
          ]
        }
      }
    ]
  }
]
```

### 3. UMA client registration
1. Register consumer
````
curl -X POST http://gg.example.com:8001/consumers/ 
    --data "username=uma_client"
````
2. Register UMA client
````
curl -X POST http://gg.example.com:8001/consumers/uma_client/gluu-oauth2-client-auth/ 
    --data name="uma_consumer_cred" 
    --data op_host="ce-gluu.example.com" 
    --data oxd_http_url="https://localhost:8443" 
    --data uma_mode=true
````
From the last call you get client_id and client_secret

### 4. Call UMA protected API

### 5. How to redirect to the claims ??

### 6. Resolve failed UMA policy 

That shows how to configure the RS

How to configure the AS
How to register the UMA client
How to call an UMA protected API
and how to redirect to the claims
and how a failed UMA RPT policy can be resolved by a redirect to the claims gathering endpoint (i.e. by presenting the PCT after claims gather )