package test.authentication;

import driver.DriverFactorySample;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {
    public static void main(String[] args) {
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

                String loginMsg = loginPage.loginDialogComp().msgTitleSel();
                System.out.println(loginMsg);
            } catch (Exception ignored) {
            }finally {
                DriverFactorySample.stopAppiumServer();
            }
        }

    }

