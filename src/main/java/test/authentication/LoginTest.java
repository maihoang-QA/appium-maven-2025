package test.authentication;

import driver.DriverFactorySample;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Description;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;
import test.BaseTest;
import test_data.LoginCreds;
import test_data.authentication.DataObjectBuilder;
import test_flows.authentication.LoginFlow;

import java.util.Base64;

public class LoginTest extends BaseTest {

    @Description("Test login with data driven....")
    @Test(dataProvider = "invalidloginCredsData", description = "Login Test", priority = 1)
    public void loginWithInCorrectCreds(LoginCreds loginCreds) {
                //Init Driver
                AppiumDriver<MobileElement> androidDriver = getDriver();
                LoginFlow loginFlow = new LoginFlow(androidDriver);
                loginFlow.navigateToLoginForm()
                         .login(loginCreds)
                         .verifyLoginWithIncorrectCreds();
        }

    @Test(description = "Login Test", priority = 2)
    public void loginWithCorrectCreds() {

        String jsonLoc = "/src/main/resources/test-data/authentication/ValidLoginCreds.json";
        LoginCreds loginCreds =  DataObjectBuilder.builDataObject(jsonLoc, LoginCreds[].class)[0];
                //Init Driver
                AppiumDriver<MobileElement> androidDriver = getDriver();
                LoginFlow loginFlow = new LoginFlow(androidDriver);
                loginFlow.navigateToLoginForm()
                         .login(loginCreds)
                         .verifyLoginSuccess();
        }


        @DataProvider
        public  LoginCreds[] invalidloginCredsData(){
            String jsonLoc = "/src/main/resources/test-data/authentication/InValidLoginCreds.json";
           return DataObjectBuilder.builDataObject(jsonLoc, LoginCreds[].class);

        }

    }

