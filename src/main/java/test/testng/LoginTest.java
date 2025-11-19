package test.testng;

import org.testng.annotations.*;

public class LoginTest {


    @BeforeTest
    public void beforeTest(){
        System.out.println("LoginTest | beforeTest");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("LoginTest | beforeClass");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("LoginTest | afterClass");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("LoginTest | afterTest");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("LoginTest | beforeMethod");
    }

    @Test
    public void loginCorrectCreds(){
        System.out.println("LoginTest | loginCorrectCreds");

    }

    @Test
    public void loginInCorrectCreds(){
        System.out.println("LoginTest | loginInCorrectCreds");

    }
}
