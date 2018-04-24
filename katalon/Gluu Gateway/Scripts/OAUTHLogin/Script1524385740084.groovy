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
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import groovy.json.JsonSlurper as JsonSlurper
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty

WebUI.openBrowser('')

WebUI.navigateToUrl('https://dev1.gluu.org:1338/#!/login')

WebUI.click(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/button_Login'))

WebUI.setText(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_oxAuth - Login/input_loginFormusername'), 
    'michal')

WebUI.setText(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_oxAuth - Login/input_loginFormpassword'), 
    'secret')

WebUI.sendKeys(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_oxAuth - Login/input_loginFormpassword'), 
    Keys.chord(Keys.ENTER))

WebUI.delay(3, FailureHandling.STOP_ON_FAILURE)

WebUI.maximizeWindow()

WebUI.click(findTestObject('Page_Gluu Gateway/a_APIS'))

WebUI.click(findTestObject('Page_Gluu Gateway/a_Add New Api'))

WebUI.delay(3)

WebUI.setText(findTestObject('Page_Gluu Gateway/input_form-control ng-pristine'), 'new_api' + new Random().nextInt())

WebUI.setText(findTestObject('Page_Gluu Gateway (1)/input_form-control ng-pristine'), 'example.com')

WebUI.setText(findTestObject('Page_Gluu Gateway/input_form-control ng-untouche'), '/test_auth')

WebUI.setText(findTestObject('Page_Gluu Gateway/input_form-control ng-untouche_1'), 'GET,POST')

WebUI.setText(findTestObject('Page_Gluu Gateway/input_form-control ng-pristine_1'), 'https://www.google.com.au/')

WebUI.scrollToElement(findTestObject('Page_Gluu Gateway (2)/button_Submit API'), 5)

WebUI.click(findTestObject('Page_Gluu Gateway/button_Submit API'))

WebUI.click(findTestObject('Page_Gluu Gateway/div_APIs'))

WebUI.click(findTestObject('Page_Gluu Gateway/a_Plugins'))

WebUI.click(findTestObject('Page_Gluu Gateway/button_add plugin'))

WebUI.click(findTestObject('Page_Gluu Gateway/a_Custom'))

WebUI.click(findTestObject('Page_Gluu Gateway/button_btn btn-link btn-icon b'))

WebUI.click(findTestObject('Page_Gluu Gateway/button_add plugin_1'))

WebUI.click(findTestObject('Page_Gluu Gateway/i_mdi mdi-close'))

WebUI.delay(5)

WebUI.click(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/span_CONSUMERS'))

WebUI.click(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/button_Create consumer'))

WebUI.setText(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/input_form-control ng-pristine'), 
    'new_consumer' + new Random().nextInt())

WebUI.click(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/button_Submit Consumer'))

WebUI.delay(5)

WebUI.click(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/strong_new_consumer'))

WebUI.click(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/uib-tab-heading_ACL GROUPS'))

WebUI.click(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/uib-tab-heading_CREDENTIALS'))

WebUI.click(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/a_OAUTH2'))

WebUI.click(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/button_create credentials'))

WebUI.delay(5)

WebUI.setText(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/input_form-control ng-pristine'), 
    'new_name')

WebUI.scrollToElement(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/button_Submit'), 
    0)

WebUI.click(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/button_Submit'))

WebUI.delay(5)

WebUI.waitForElementVisible(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/td_0b98c9ea-11ba-4137-9f66-185'), 
    5)

WebUI.click(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/td_0b98c9ea-11ba-4137-9f66-185'))

oxdId = WebUI.getText(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/td_0b98c9ea-11ba-4137-9f66-185'))

WebUI.click(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/td_19CF.B296.532F.83E2000125C1'))

clientId = WebUI.getText(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/td_19CF.B296.532F.83E2000125C1'))

WebUI.click(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/td_94bf787c-a42d-4a66-b8d3-41d'))

clientSecret = WebUI.getText(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/td_94bf787c-a42d-4a66-b8d3-41d'))

WebUI.click(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/button_OK'))

WebUI.delay(5)

WebUI.click(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/a_'))

WebUI.click(findTestObject('Page_Gluu Gateway/Page_oxAuth - Login/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/Page_Gluu Gateway/a_Logout'))

WebUI.closeBrowser()

RequestObject request = findTestObject('get token')

request.setHttpBody(((((('{"oxd_id":"' + oxdId) + '","client_id":"') + clientId) + '","client_secret":"') + clientSecret) + 
    '","op_host":"https://ce-dev6.gluu.org", "scope":["openid","uma_protection"]}')

response = WS.sendRequest(request)

JsonSlurper parser = new JsonSlurper()

def parsedResp = parser.parseText(response.getResponseBodyContent())

accessToken = parsedResp.get('data').get('access_token')

RequestObject request2 = findTestObject('ApiCall')

request2.getHttpHeaderProperties().add(new TestObjectProperty('Authorization', null, 'Bearer ' + accessToken))

response2 = WS.sendRequest(request)

result = response2.getStatusCode()

WebUI.verifyEqual(result, 200)

