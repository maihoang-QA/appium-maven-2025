package test.testng;

import org.testng.annotations.*;

public class DemoTest {

    @BeforeTest
    public void beforeTest(){
        System.out.println("DemoTest | beforeTest");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("DemoTest | beforeClass");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("DemoTest | afterClass");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("DemoTest | afterTest");
    }



    @Test
    public void loginDemoTest(){
        System.out.println("DemoTest | loginCorrectCreds");

    }

    @Test
    public void loginInCorrectDemoTest(){
        System.out.println("DemoTest | loginInCorrectCreds");

    }
}
