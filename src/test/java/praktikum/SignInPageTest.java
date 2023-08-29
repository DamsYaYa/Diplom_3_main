package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;

import io.qameta.allure.junit4.DisplayName;

import io.restassured.response.ValidatableResponse;

import org.junit.After;

import org.junit.Before;

import org.junit.Test;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import praktikum.pageobject.SignInPage;

import praktikum.pageobject.MainPage;

import praktikum.pageobject.RestorePasswordPage;

import praktikum.user.User;

import praktikum.user.UserGenerator;

import praktikum.user.UserClient;



import static praktikum.Client.APP_URL;



public class SignInPageTest {

    WebDriver driver;

    private User user = new User();

    private final UserClient client = new UserClient();

    private final UserGenerator generator = new UserGenerator();

    private String accessToken;





    @Before

    public void startUp() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.get(APP_URL);

    }



    @Test

    @DisplayName("Signing from login button ")

    public void loginFromLoginButtonTest(){

        MainPage mainPage = new MainPage(driver);

        mainPage.clickLoginButton();



        SignInPage signInPage = new SignInPage(driver);

        user = generator.random();

        ValidatableResponse response = client.create(user);

        accessToken = response.extract().path("accessToken").toString();

        signInPage.loginUser(user.getEmail(), user.getPassword());

        signInPage.checkMainPageAfterLogin();

    }



    @Test

    @DisplayName("Signing from Account button ")

    public void loginFromAccountButtonTest(){

        MainPage mainPage = new MainPage(driver);

        mainPage.clickAccountButton();



        SignInPage signInPage = new SignInPage(driver);

        user = generator.random();

        ValidatableResponse response = client.create(user);

        accessToken = response.extract().path("accessToken").toString();

        signInPage.loginUser(user.getEmail(), user.getPassword());

        signInPage.checkMainPageAfterLogin();

    }



    @Test

    @DisplayName("Signing from registration page")

    public void loginFromRegistrationPageTest(){

        MainPage mainPage = new MainPage(driver);

        mainPage.clickLoginButton();



        SignInPage signInPage = new SignInPage(driver);

        signInPage.clickRegisterLink();

        signInPage.clickLoginLink();

        user = generator.random();

        ValidatableResponse response = client.create(user);

        accessToken = response.extract().path("accessToken").toString();

        signInPage.loginUser(user.getEmail(), user.getPassword());

        signInPage.checkMainPageAfterLogin();

    }



    @Test

    @DisplayName("Signing from Restore password page")

    public void loginFromRecoverPasswordPageTest(){

        MainPage mainPage = new MainPage(driver);

        mainPage.clickLoginButton();



        SignInPage signInPage = new SignInPage(driver);

        signInPage.clickRestorePasswordLink();



        RestorePasswordPage restorePasswordPage = new RestorePasswordPage(driver);

        restorePasswordPage.clickLoginButtonFromRecoverPasswordLink();



        user = generator.random();

        ValidatableResponse response = client.create(user);

        accessToken = response.extract().path("accessToken").toString();

        signInPage.loginUser(user.getEmail(), user.getPassword());

        signInPage.checkMainPageAfterLogin();

    }



    @After

    public void endUp() {

        client.delete(accessToken);

        driver.quit();

    }

}
