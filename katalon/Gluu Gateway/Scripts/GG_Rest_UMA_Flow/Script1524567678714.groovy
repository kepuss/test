import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

import org.junit.After
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.interactions.Actions as Actions
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import groovy.json.JsonSlurper as JsonSlurper
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.RestRequestObjectBuilder 
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.client.HttpClient
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.HttpResponse
import org.apache.http.message.BasicHeader


uma_oxdid="c0d02eab-2545-44f1-8986-26103bd5b1f1"
uma_clientid="@!19CF.B296.532F.83E2!0001!25C1.E1E4!0008!8E72.ADC7.04EE.4E93"
uma_clientsecret="fef6b962-5a0a-491c-876c-c7abc1d7c0cb"
oxdId="37c9f7c1-2c09-4fb5-97d0-5aaefeb030d8"
clientId="@!19CF.B296.532F.83E2!0001!25C1.E1E4!0008!9650.E539.33FC.8A55"
clientSecret="da536362-c4e3-4cab-9121-ee63bc960959"



JsonSlurper parser = new JsonSlurper()

//-------------UMA client accessToken

RequestObject request = findTestObject('get token')

request.setHttpBody(((((('{"oxd_id":"' + uma_oxdid) + '","client_id":"') + uma_clientid) + '","client_secret":"') + uma_clientsecret) +
	'","op_host":"https://ce-dev6.gluu.org", "scope":["openid","uma_protection"]}')

response = WS.sendRequest(request)

def parsedResp = parser.parseText(response.getResponseBodyContent())

def umaAccessToken = parsedResp.get("data").get("access_token")

//-------------customer client accessToken

RequestObject clientRequest = findTestObject('get token')

clientRequest.setHttpBody(((((('{"oxd_id":"' + oxdId) + '","client_id":"') + clientId) + '","client_secret":"') + clientSecret) +
	'","op_host":"https://ce-dev6.gluu.org", "scope":["openid","uma_protection"]}')

clientResponse = WS.sendRequest(clientRequest)

def clientParsedResp = parser.parseText(clientResponse.getResponseBodyContent())

def customerAccessToken = clientParsedResp.get("data").get("access_token")

//------------------get ticket ----------------
RequestObject ticketRequest = findTestObject('UmaRsCheckAccess')

ticketRequest.setHttpBody('{"oxd_id":"'+uma_oxdid+'","path":"/test","http_method":"GET","protection_access_token":"'+umaAccessToken+'"}')

ticketRequest.getHttpHeaderProperties().add(new TestObjectProperty('Authorization', null, 'Bearer ' + umaAccessToken))
ticketResponse = WS.sendRequest(ticketRequest)

def ticketParsedResp = parser.parseText(ticketResponse.getResponseBodyContent())

def ticket = ticketParsedResp.get("data").get("ticket")

//------------------get rpt ----------------
RequestObject rptRequest = findTestObject('GetRpt')

rptRequest.setHttpBody('{"oxd_id":"'+oxdId+'","ticket":"'+ticket+'","protection_access_token":"'+customerAccessToken+'"}')

rptRequest.getHttpHeaderProperties().add(new TestObjectProperty('Authorization', null, 'Bearer ' + customerAccessToken))
rptResponse = WS.sendRequest(rptRequest)

def rptParsedResp = parser.parseText(rptResponse.getResponseBodyContent())

def rpt = rptParsedResp.get("data").get("access_token")

//------------------RequestAPI ----------------

HttpUriRequest apiRequest = new HttpGet("http://dev1.gluu.org:8000/test/test")
apiRequest.addHeader(new BasicHeader("Authorization", 'Bearer ' + rpt))
apiRequest.addHeader(new BasicHeader("Host", 'example.com'))
	
HttpClient client = new DefaultHttpClient();
HttpResponse httpResponse = client.execute(apiRequest);

int a =5
