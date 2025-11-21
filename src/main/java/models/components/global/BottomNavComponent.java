package models.components.global;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class BottomNavComponent {

    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By loginLabelSel = MobileBy.AccessibilityId("Login");
    private static final By formLabelSel = MobileBy.AccessibilityId("Forms");
    private static final By swipeLabelSel = MobileBy.AccessibilityId("Swipe");

    public BottomNavComponent(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    //Đi đặc tả hành vi
    //Return Mobile Element
    public  MobileElement loginLabelElem (){
        return appiumDriver.findElement(loginLabelSel);
    }

    public  MobileElement formsLabelElem (){
        return appiumDriver.findElement(formLabelSel);
    }

    public  MobileElement swipeLabelElem (){
        return appiumDriver.findElement(swipeLabelSel);
    }

    //Main interaction method
    @Step("Click on login label")
    public void clickLoginLabel(){
         appiumDriver.findElement(loginLabelSel).click();
    }


    }

