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

WebUI.openBrowser('')

WebUI.navigateToUrl('https://dev1.gluu.org:1338/#!/login')

WebUI.click(findTestObject('Page_Gluu Gateway/button_Login'))

WebUI.click(findTestObject('Page_oxAuth - Login/input_loginFormloginButton'))

WebUI.setText(findTestObject('Page_oxAuth - Login/input_loginFormusername'), 'michal')

WebUI.setText(findTestObject('Page_oxAuth - Login/input_loginFormpassword'), 'secret')

WebUI.click(findTestObject('Page_oxAuth - Login/input_loginFormloginButton'))

WebUI.delay(0, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_Gluu Gateway/a_APIS'))

WebUI.click(findTestObject('Page_Gluu Gateway/a_Add New Api'))

WebUI.setText(findTestObject('Page_Gluu Gateway/input_form-control ng-pristine'), 'new_api' + new Random().nextInt())

WebUI.setText(findTestObject('Page_Gluu Gateway (1)/input_form-control ng-pristine'), 'example2.com')

WebUI.setText(findTestObject('Page_Gluu Gateway/input_form-control ng-untouche'), '/test')

WebUI.setText(findTestObject('Page_Gluu Gateway/input_form-control ng-untouche_1'), 'GET,POST')

WebUI.setText(findTestObject('Page_Gluu Gateway/input_form-control ng-pristine_1'), 'https://www.google.com.au/')

WebUI.click(findTestObject('Page_Gluu Gateway/div_Create API'))

WebUI.click(findTestObject('Page_Gluu Gateway/button_Submit API'))

WebUI.closeBrowser()

RequestObject request = findTestObject('TestApiCall')

response2 = WS.sendRequest(request)

result = response2.getStatusCode()

WebUI.verifyEqual(result, 200)

