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

import praktikum.user.User;

import praktikum.user.UserGenerator;

import static praktikum.Client.APP_URL;

import praktikum.user.UserClient;



public class ProfilePageTest {

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

    @DisplayName("Transition from the main page to personal account page after authorization")

    public void accountPageFromMainPage(){

        MainPage mainPage = new MainPage(driver);

        mainPage.clickLoginButton();



        SignInPage signInPage = new SignInPage(driver);

        user = generator.random();

        ValidatableResponse response = client.create(user);

        accessToken = response.extract().path("accessToken").toString();

        signInPage.loginUser(user.getEmail(), user.getPassword());

        signInPage.checkAccountPageFromMainPage();

    }



    @Test

    @DisplayName("Transition from account page to constructor page")

    public void constructorPageFromAccountPage(){

        MainPage mainPage = new MainPage(driver);

        mainPage.clickLoginButton();



        SignInPage signInPage = new SignInPage(driver);

        user = generator.random();

        ValidatableResponse response = client.create(user);

        accessToken = response.extract().path("accessToken").toString();

        signInPage.loginUser(user.getEmail(), user.getPassword());

        signInPage.checkConstructorPageFromMainPage();

    }

    @Test

    @DisplayName("Transition from account page by clicking on the Burger logo")

    public void mainPageFromAccountPageByClickOnLogo(){

        MainPage mainPage = new MainPage(driver);

        mainPage.clickLoginButton();



        SignInPage signInPage = new SignInPage(driver);

        user = generator.random();

        ValidatableResponse response = client.create(user);

        accessToken = response.extract().path("accessToken").toString();

        signInPage.loginUser(user.getEmail(), user.getPassword());

        signInPage.checkMainPageFromAccountPageByClickOnLogo();

    }



    @Test

    @DisplayName("Logout test")

    public void exitAccountTest(){

        MainPage mainPage = new MainPage(driver);

        mainPage.clickLoginButton();



        SignInPage signInPage = new SignInPage(driver);

        user = generator.random();

        ValidatableResponse response = client.create(user);

        accessToken = response.extract().path("accessToken").toString();

        signInPage.loginUser(user.getEmail(), user.getPassword());

        signInPage.checkExitFromAccount();

    }



    @After

    public void endUp() {

        client.delete(accessToken);

        driver.quit();

    }

}
