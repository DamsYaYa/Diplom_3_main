package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;

import io.qameta.allure.junit4.DisplayName;

import org.junit.After;

import org.junit.Before;

import org.junit.Test;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import praktikum.pageobject.MainPage;



import static praktikum.Client.APP_URL;



public class MainPageTest {

    WebDriver driver;



    @Before

    public void startUp() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.get(APP_URL);

    }



    @Test

    @DisplayName("Select buns section")

    public void bunsTest() {

        MainPage mainPage = new MainPage(driver);

        mainPage.checkBuns();

    }



    @Test

    @DisplayName("Select sauces section")

    public void saucesTest() {

        MainPage mainPage = new MainPage(driver);

        mainPage.checkSauce();

    }



    @Test

    @DisplayName("Select fillings section")

    public void fillingsTest() {

        MainPage mainPage = new MainPage(driver);

        mainPage.checkFillings();

    }



    @After

    public void endUp() {

        driver.quit();

    }

}
