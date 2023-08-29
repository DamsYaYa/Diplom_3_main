package praktikum.pageobject;

import io.qameta.allure.Step;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class MainPage {

    WebDriver driver;

    public MainPage(WebDriver driver) {

        this.driver = driver;

    }



    private final By enterText = By.xpath(".//main/div/h2[text()='Вход']");

    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");

    private final By enterAccountButton = By.xpath(".//a[@href='/account']");

    private final By bunsButton = By.xpath("//span[@class='text text_type_main-default'][text()='Булки']");

    private final By saucesButton = By.xpath("//span[@class='text text_type_main-default'][text()='Соусы']");

    private final By fillingsButton = By.xpath("//span[@class='text text_type_main-default'][text()='Начинки']");

    private final By activeIngredient = By.cssSelector(".tab_tab_type_current__2BEPc");



    @Step("Click signIn button on main page")

    public void clickLoginButton() {

        driver.findElement(loginButton).click();

        new WebDriverWait(driver, 5)

                .until(ExpectedConditions.visibilityOfElementLocated(enterText));

    }



    @Step("Click Account button on main page")

    public void clickAccountButton() {

        driver.findElement(enterAccountButton).click();

        new WebDriverWait(driver, 5)

                .until(ExpectedConditions.visibilityOfElementLocated(enterText));

    }



    @Step("Select buns section")

    public void clickOnBunsButton() {

        driver.findElement(bunsButton).click();



    }

    @Step("Select sauces section")

    public void clickOnSaucesButton() {

        driver.findElement(saucesButton).click();

    }

    @Step("Select fillings section")

    public void clickOnFillingsButton() {

        driver.findElement(fillingsButton).click();

    }



    @Step("Get name of active Ingredient")

    public String getTextOfActiveIngredient(){

        return driver.findElement(activeIngredient).getText();

    }



    @Step("Check buns section is selected")

    public void checkBuns() {

        clickOnSaucesButton();

        clickOnBunsButton();

        String expected = "Булки";

        assertEquals(expected, getTextOfActiveIngredient());





    }

    @Step("Check sauces section is selected")

    public void checkSauce() {

        clickOnSaucesButton();

        String expected = "Соусы";

        assertEquals(expected, getTextOfActiveIngredient());

    }

    @Step("Check fillings section is selected")

    public void checkFillings() {

        clickOnFillingsButton();

        String expected = "Начинки";

        assertEquals(expected, getTextOfActiveIngredient());

    }

}
