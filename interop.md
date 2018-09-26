###Interoperation design

### Endpoint setup
Create API
```aidl
POST /apis HTTP/1.1
Host: demo.gluu.org:8001
Content-Type: application/x-www-form-urlencoded

name=api-keycloak-interop&
hosts=api-keycloak-interop&u
pstream_url=https://jsonplaceholder.typicode.com
```

Secure it with oauth plugin with Keycloak IDP
```aidl
POST /apis/api-keycloak-interop/plugins HTTP/1.1
Host: demo.gluu.org:8001
Content-Type: application/x-www-form-urlencoded

name=gluu-oauth2-client-auth&
config.op_server=https://keycloak-server-route-keycloak.b9ad.pro-us-east-1.openshiftapps.com/auth/realms/oxd&
config.oxd_http_url=https://localhost:8443
```

Create UMA resource
```aidl
POST /apis/api-keycloak-interop/plugins HTTP/1.1
Host: demo.gluu.org:8001
Content-Type: application/x-www-form-urlencoded

name=gluu-oauth2-rs&
config.oxd_host=https://localhost:8443&
config.uma_server_host=https://keycloak-server-route-keycloak.b9ad.pro-us-east-1.openshiftapps.com/auth/realms/oxd&
config.protection_document=[ { "path": "/posts", "conditions": [ { "httpMethods": [ "GET" ], "scopes":  ["uma_scope"]} ] } ]
```

### Consumer setup
```aidl
POST /consumers HTTP/1.1
Host: demo.gluu.org:8001
Content-Type: application/x-www-form-urlencoded

username=keycloak-consumer
```


Create credentials
```aidl
POST /consumers/keycloak-consumer/gluu-oauth2-client-auth HTTP/1.1
Host: demo.gluu.org:8001
Content-Type: application/x-www-form-urlencoded

name=uma-creds-1537444208&
op_host=https%3A%2F%2Fkeycloak-server-route-keycloak.b9ad.pro-us-east-1.openshiftapps.com%2Fauth%2Frealms%2Foxd&
uma_mode=true&
oxd_http_url=https%3A%2F%2Flocalhost%3A8443
```