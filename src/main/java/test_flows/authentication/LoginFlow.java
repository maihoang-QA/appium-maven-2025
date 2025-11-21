package test_flows.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.testng.Assert;
import test_data.LoginCreds;

public class LoginFlow {

    private AppiumDriver<MobileElement> appiumDriver;
    private LoginPage loginPage;

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    public LoginFlow initLoginPage(){
        loginPage = new LoginPage(appiumDriver);
        return this;
    }
    public LoginFlow navigateToLoginForm(){
        if (loginPage==null) {
            initLoginPage();
        }
        //Bottom Nav Comp
        BottomNavComponent bottomNavComponent = loginPage.bottomNavComp();
        bottomNavComponent.clickLoginLabel();
        return this;
    }

    public LoginFlow login(LoginCreds loginCreds){
        if (loginPage ==null)
            throw new RuntimeException("Please use method navigateToLoginForm first");
        //Fill login Forms
        loginPage.inputUsername(loginCreds.getUsername())
                .inputPassword(loginCreds.getPassword())
                .clickLoginBtn();
        return this;


    }

    public LoginFlow verifyLoginSuccess(){
        //Verification
        String actualLoginMsg = loginPage.loginDialogComp().msgTitleSel();
        boolean isTitleCorrect = actualLoginMsg.equals("Success");

        String customErrMsg = "[ERR] Login msg title incorrect";
        Assert.assertTrue(isTitleCorrect, customErrMsg);
        return this;

    }

    public void verifyLoginWithIncorrectCreds(){

    }
}
