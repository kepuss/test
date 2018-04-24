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

WebUI.click(findTestObject('Page_Gluu Gateway (5)/button_Login'))

WebUI.setText(findTestObject('Page_oxAuth - Login (5)/input_loginFormusername'), 'michal')

WebUI.setText(findTestObject('Page_oxAuth - Login (5)/input_loginFormpassword'), 'secret')

WebUI.sendKeys(findTestObject('Page_oxAuth - Login (5)/input_loginFormpassword'), Keys.chord(Keys.ENTER))

WebUI.click(findTestObject('Page_Gluu Gateway (5)/a_APIS'))

WebUI.click(findTestObject('Page_Gluu Gateway (5)/a_Security'))

WebUI.click(findTestObject('Page_Gluu Gateway (5)/div_UMA-RS plugin'))

WebUI.click(findTestObject('Page_Gluu Gateway (5)/td_84164f52-7986-406d-87bd-006'))

WebUI.click(findTestObject('Page_Gluu Gateway (5)/i_mdi mdi-eye'))

WebUI.click(findTestObject('Page_Gluu Gateway (5)/span_19CF.B296.532F.83E2000125'))

WebUI.click(findTestObject('Page_Gluu Gateway (5)/i_mdi mdi-eye_1'))

WebUI.click(findTestObject('Page_Gluu Gateway (5)/span_b93e0539-ff1e-4b95-81bc-2'))

WebUI.closeBrowser()

