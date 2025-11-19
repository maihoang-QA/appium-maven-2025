package test.testng;

import org.testng.annotations.*;

public class SignupTest {
    @BeforeTest
    public void beforeTest(){
        System.out.println("beforeTest | beforeTest");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("beforeTest | beforeClass");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("beforeTest | afterClass");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("beforeTest | afterTest");
    }





    @Test
    public void signupWithCorrectEmail(){
        System.out.println("beforeTest | loginCorrectCreds");

    }

    @Test
    public void signupWithInCorrectEmail(){
        System.out.println("beforeTest | loginInCorrectCreds");

    }
}
