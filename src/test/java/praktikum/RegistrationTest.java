package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;

import io.qameta.allure.junit4.DisplayName;

import org.junit.After;

import org.junit.Before;

import org.junit.Test;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import praktikum.pageobject.SignInPage;

import praktikum.pageobject.MainPage;

import praktikum.pageobject.RegistrationPage;

import praktikum.user.User;

import praktikum.user.UserGenerator;

import static praktikum.Client.APP_URL;



public class RegistrationTest {

    WebDriver driver;

    private User user = new User();

    private final UserGenerator generator = new UserGenerator();



    @Before

    public void startUp() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.get(APP_URL);

    }



    @Test

    @DisplayName("Create new user")

    public void registrationNewUserTest() {

        MainPage mainPage = new MainPage(driver);

        mainPage.clickLoginButton();



        SignInPage signInPage = new SignInPage(driver);

        signInPage.clickRegisterLink();



        RegistrationPage registrationPage = new RegistrationPage(driver);

        user = generator.random();

        registrationPage.registerNewUser(user.getName(), user.getEmail(), user.getPassword());

        registrationPage.checkSuccessfulRegistration();

    }



    @Test

    @DisplayName("Create new user with wrong password")

    public void registrationNewUserWithWrongPasswordTest(){

        MainPage mainPage = new MainPage(driver);

        mainPage.clickLoginButton();



        SignInPage signInPage = new SignInPage(driver);

        signInPage.clickRegisterLink();



        RegistrationPage registrationPage = new RegistrationPage(driver);

        user = generator.random();

        user.setPassword("123");

        registrationPage.registerNewUser(user.getName(), user.getEmail(), user.getPassword());

        registrationPage.checkUnsuccessfulRegistration();

    }



    @After

    public void endUp() {

        driver.quit();

    }

}
