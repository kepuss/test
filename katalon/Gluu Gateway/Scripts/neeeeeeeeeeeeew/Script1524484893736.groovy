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

WebUI.openBrowser('')

WebUI.navigateToUrl('https://dev1.gluu.org:1338/#!/login')

WebUI.click(findTestObject('Page_Gluu Gateway (7)/button_Login'))

WebUI.setText(findTestObject('Page_oxAuth - Login (7)/input_loginFormusername'), 'michal')

WebUI.setText(findTestObject('Page_oxAuth - Login (7)/input_loginFormpassword'), 'secret')

WebUI.click(findTestObject('Page_Gluu Gateway (7)/a_CONSUMERS'))

WebUI.click(findTestObject('Page_Gluu Gateway (7)/button_Create consumer'))

WebUI.setText(findTestObject('Page_Gluu Gateway (7)/input_form-control ng-untouche_1'), 'newC')

WebUI.click(findTestObject('Page_Gluu Gateway (7)/button_Submit Consumer'))

WebUI.click(findTestObject('Page_Gluu Gateway (7)/strong_newC'))

WebUI.click(findTestObject('Page_Gluu Gateway (7)/uib-tab-heading_CREDENTIALS'))

WebUI.click(findTestObject('Page_Gluu Gateway (7)/a_OAUTH2'))

WebUI.click(findTestObject('Page_Gluu Gateway (7)/button_create credentials'))

WebUI.click(findTestObject('Page_Gluu Gateway (7)/span_'))

WebUI.click(findTestObject('Page_Gluu Gateway (7)/div_Create OAuth2'))

WebUI.setText(findTestObject('Page_Gluu Gateway (7)/input_form-control ng-untouche_1'), 'newnew')

WebUI.click(findTestObject('Page_Gluu Gateway (7)/div_Create OAuth2'))

WebUI.click(findTestObject('Page_Gluu Gateway (7)/div_Create OAuth2'))

WebUI.click(findTestObject('Page_Gluu Gateway (7)/button_Submit'))

WebUI.click(findTestObject('Page_Gluu Gateway (7)/td_ddb81cdb-15fa-4a34-92d7-c59'))

WebUI.click(findTestObject('Page_Gluu Gateway (7)/td_19CF.B296.532F.83E2000125C1'))

WebUI.click(findTestObject('Page_Gluu Gateway (7)/td_9de0a056-e4bf-47fd-9783-572'))

WebUI.click(findTestObject('Page_Gluu Gateway (7)/button_OK'))

WebUI.closeBrowser()

