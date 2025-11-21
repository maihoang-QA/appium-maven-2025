package test.authentication;

import driver.DriverFactorySample;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;
import test_data.LoginCreds;

public class LoginTestEx extends BaseTest {

    public void loginWithCorrectCreds() {
            //Init Driver
        //Thằng nào gọi getDriver() là tự thằng đó tạo cái session của 1 cái thread riêng
            AppiumDriver<MobileElement> androidDriver = getDriver();
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
            boolean isTitleCorrect = actualLoginMsg.equals("success.....");

            String customErrMsg = "[ERR] Login msg title incorrect";
            Assert.assertTrue(isTitleCorrect, customErrMsg + " | assertEquals");
            Assert.assertEquals(actualLoginMsg, "success", "[ERR] Login msg tittle incorrect!");
        }
    }



