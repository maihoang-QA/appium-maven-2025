package test;

import driver.DriverFactorEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BaseTest {

    private final static List<DriverFactorEx> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactorEx> driverThread;

    //alwaysRun = true: dù có như nào thì cái này nó sẽ dc thực thi
    @BeforeSuite(alwaysRun = true)
    public static void beforeSuite() {
        // () -> {} : annonymus function : function ko có tên
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactorEx driverThread = new DriverFactorEx();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
    }

    @AfterSuite(alwaysRun = true)
    public static void afterSuite() {
        for (DriverFactorEx webDriverThread : driverThreadPool) {
            webDriverThread.quitAppiumSession();
        }
    }

    //Thread nào chạy lên n sẽ return về đúng appium session cho mình
    public static AppiumDriver<MobileElement> getDriver() {
        return driverThread.get().getAppiumDriver();
    }

//    //TODO: this can be enum type
//    public static AppiumDriver<MobileElement> getDriver (String mobileDriverName){
//        return driverThread.get().getDriver(browserName);
//    }

//    //TODO: capture screen and attach into report
//    @AfterMethod(alwaysRun = true)
//    public void afterMethod(ITestResult result){
//
//    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // 1. Get the test Method name
            String testMethodName = result.getName();

            // 2. Declare the file location
            Calendar calendar = new GregorianCalendar();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH);
            int d = calendar.get(Calendar.DATE);
            int hr = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);
            int sec = calendar.get(Calendar.SECOND);
            String dateTaken = y + "-" + (m + 1) + "-" + d + "-" + hr + "-" + min + "-" + sec;
            String fileLocation = System.getProperty("user.dir") + "/screenshot/" + testMethodName + "_" + dateTaken + ".png";

            // 3. Declare the file name

            // 4. Save the screenshot to the system
            File screenShot = driverThread.get().getAppiumDriver().getScreenshotAs(OutputType.FILE);

            try {
                FileUtils.copyFile(screenShot, new File(fileLocation));
                Path content = Paths.get(fileLocation);
                try (InputStream is = Files.newInputStream(content)) {
                    Allure.addAttachment(testMethodName, is);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}



