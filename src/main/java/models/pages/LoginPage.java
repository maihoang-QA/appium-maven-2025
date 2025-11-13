package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.components.authentication.LoginDialogComponent;
import models.components.global.BottomNavComponent;
import org.openqa.selenium.By;

public class LoginPage {

    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By usernameSel = MobileBy.AccessibilityId("input-email");
    private static final By passwordSel = MobileBy.AccessibilityId("input-password");
    private static final By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");
    private BottomNavComponent bottomNavComp;
    private LoginDialogComponent loginDialogComp;

    public LoginPage(AppiumDriver<MobileElement> appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    public LoginPage inputUsername(String username){
        appiumDriver.findElement(usernameSel).sendKeys(username);
        return this;
    }

    public LoginPage inputPassword(String password){
        appiumDriver.findElement(passwordSel).sendKeys(password);
        return this;
    }

    public LoginPage clickLoginBtn(){
        appiumDriver.findElement(loginBtnSel).click();
        return this;
    }
    public BottomNavComponent bottomNavComp(){
        return new BottomNavComponent(appiumDriver);
    }

    public LoginDialogComponent loginDialogComp(){
        return new LoginDialogComponent(appiumDriver);
    }
}
//return this; = trả về đối tượng hiện tại của class, giúp gọi chuỗi các method liên tiếp nhau dễ đọc và gọn hơn.
// Ví dụ:
//LoginPage loginPage = new LoginPage(appiumDriver);
//loginPage
//    .inputUsername("abc")
//    .inputPassword("123")
//    .clickLogin();
// Thay vì gọi: loginPage.inputUsername("abc") &&  loginPage.inputPassword("123") && loginPage.clickLogin();
