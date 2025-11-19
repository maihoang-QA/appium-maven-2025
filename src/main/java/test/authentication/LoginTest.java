package test.authentication;

import driver.DriverFactorySample;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest {
    private  SoftAssert softAssert;

    @BeforeClass
    public void beforeClass(){
        softAssert = new SoftAssert();
    }
    @AfterClass
    public void afterClass(){
        softAssert.assertAll();
    }

    @Ignore
    @Test(priority = 2)
    public void loginWithCorrectCreds() {
            DriverFactorySample.startAppiumServer();

            try {

                //Init Driver
                AndroidDriver<MobileElement> androidDriver = DriverFactorySample.getAndroidDriver();
                //Login Page
                LoginPage loginPage = new LoginPage(androidDriver);

                //Bottom Nav Comp
                BottomNavComponent bottomNavComponent = loginPage.bottomNavComp();
                bottomNavComponent.clickLoginLabel();
                bottomNavComponent.formsLabelElem().click();
                bottomNavComponent.loginLabelElem().click();

                //Fill login Forms
                loginPage.inputUsername("mai@gmail.com")
               .inputPassword("12345678")
               .clickLoginBtn();

                //Verification
                String actualLoginMsg = loginPage.loginDialogComp().msgTitleSel();
                boolean isTitleCorrect = actualLoginMsg.equals("success");

                String customErrMsg = "[ERR] Login msg title incorrect";
                softAssert.assertTrue(isTitleCorrect, customErrMsg + " | assertEquals");
                softAssert.assertEquals(actualLoginMsg, "success", "[ERR] Login msg tittle incorrect!");

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                DriverFactorySample.stopAppiumServer();
            }
        }

        @Test(priority = 1, dependsOnMethods = {"a2Void"})
        void  a1Void(){
            System.out.println("this should be executed first");
        }

        @Test
        void  a2Void(){
            Assert.assertTrue(false);
        }

    }

