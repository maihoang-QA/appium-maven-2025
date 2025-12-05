package test;

import driver.DriverFactorEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BaseTest {

//    private final static List<DriverFactorEx> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
//    private static ThreadLocal<DriverFactorEx> driverThread;
//    private String udid;
//    private String port;
//    private String systemPort;
//
//    //alwaysRun = true: dù có như nào thì cái này nó sẽ dc thực thi
//    @BeforeTest(alwaysRun = true)
//    @Parameters({"udid", "port", "systemPort"})
//    public void beforeTest(String udid, String port, String systemPort) {
//        System.out.println(udid + "|" + port + "|" + systemPort);
//        this.udid = udid;
//        this.port = port;
//        this.systemPort = systemPort;
//        // () -> {} : annonymus function : function ko có tên
//        driverThread = ThreadLocal.withInitial(() -> {
//            DriverFactorEx driverThread = new DriverFactorEx();
//            driverThreadPool.add(driverThread);
//            return driverThread;
//        });
//    }
//
//    @AfterTest(alwaysRun = true)
//    public static void afterTest() {
//        driverThread.get().quitAppiumSession();
////        for (DriverFactorEx webDriverThread : driverThreadPool) {
////            webDriverThread.quitAppiumSession();
////        }
//    }
//
//    //Thread nào chạy lên n sẽ return về đúng appium session cho mình
//    public AppiumDriver<MobileElement> getDriver() {
//        return driverThread.get().getAppiumDriver(udid, port, systemPort);
//    }
//
////    //TODO: this can be enum type
////    public static AppiumDriver<MobileElement> getDriver (String mobileDriverName){
////        return driverThread.get().getDriver(browserName);
////    }
//
////    //TODO: capture screen and attach into report
////    @AfterMethod(alwaysRun = true)
////    public void afterMethod(ITestResult result){
////
////    }
//
//    @AfterMethod(alwaysRun = true)
//    public void afterMethod(ITestResult result) {
//        if (result.getStatus() == ITestResult.FAILURE) {
//            // 1. Get the test Method name
//            String testMethodName = result.getName();
//
//            // 2. Declare the file location
//            Calendar calendar = new GregorianCalendar();
//            int y = calendar.get(Calendar.YEAR);
//            int m = calendar.get(Calendar.MONTH);
//            int d = calendar.get(Calendar.DATE);
//            int hr = calendar.get(Calendar.HOUR_OF_DAY);
//            int min = calendar.get(Calendar.MINUTE);
//            int sec = calendar.get(Calendar.SECOND);
//            String dateTaken = y + "-" + (m + 1) + "-" + d + "-" + hr + "-" + min + "-" + sec;
//            String fileLocation = System.getProperty("user.dir") + "/screenshot/" + testMethodName + "_" + dateTaken + ".png";
//
//            // 3. Declare the file name
//
//            // 4. Save the screenshot to the system
//            File screenShot = driverThread.get().getAppiumDriver().getScreenshotAs(OutputType.FILE);
//
//            try {
//                FileUtils.copyFile(screenShot, new File(fileLocation));
//                Path content = Paths.get(fileLocation);
//                try (InputStream is = Files.newInputStream(content)) {
//                    Allure.addAttachment(testMethodName, is);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }



    private static ThreadLocal<DriverFactorEx> driverThread = new ThreadLocal<>();

    private String udid;
    private String port;
    private String systemPort;

    @BeforeTest(alwaysRun = true)
    @Parameters({"udid", "port", "systemPort"})
    public void beforeTest(String udid, String port, String systemPort) {
        System.out.println("INIT THREAD: " + udid + "|" + port + "|" + systemPort);
        this.udid = udid;
        this.port = port;
        this.systemPort = systemPort;

        driverThread.set(new DriverFactorEx());
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        if (driverThread.get() != null) {
            driverThread.get().quitAppiumSession();
            driverThread.remove();
        }
    }

    public AppiumDriver<MobileElement> getDriver() {
        return driverThread.get().getAppiumDriver(udid, port, systemPort);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {

            String testName = result.getName();

            Calendar c = Calendar.getInstance();
            String timestamp = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" +
                    c.get(Calendar.DATE) + "-" + c.get(Calendar.HOUR_OF_DAY) + "-" +
                    c.get(Calendar.MINUTE) + "-" + c.get(Calendar.SECOND);

            String filePath = System.getProperty("user.dir") + "/screenshot/" +
                    testName + "_" + timestamp + ".png";

            try {
                File src = getDriver().getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(src, new File(filePath));

                Path content = Paths.get(filePath);
                try (InputStream is = Files.newInputStream(content)) {
                    Allure.addAttachment(testName, is);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}



