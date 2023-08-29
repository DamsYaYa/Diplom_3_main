package praktikum.pageobject;

import io.qameta.allure.Step;

import org.junit.Assert;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;



public class RegistrationPage {

    WebDriver driver;



    public RegistrationPage(WebDriver driver) {

        this.driver = driver;

    }



    private final By nameField = By.xpath(".//div[./label[text()='Имя']]/input[@name='name']");

    private final By emailField = By.xpath(".//div[./label[text()='Email']]/input[@name='name']");

    private final By passwordField = By.xpath(".//div[./label[text()='Пароль']]/input[@name='Пароль']");



    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");

    private final By enterText = By.xpath(".//main/div/h2[text()='Вход']");

    private final By wrongPasswordText = By.xpath(".//p[text()='Некорректный пароль']");

    private final By registerText = By.xpath(".//main/div/h2[text()='Регистрация']");



    @Step("Fill name field")

    public void fillNameField(String name) {

        driver.findElement(nameField).sendKeys(name);

    }



    @Step("Fill email field")

    public void fillEmailField(String email) {

        driver.findElement(emailField).sendKeys(email);

    }



    @Step("Fill password field")

    public void fillPasswordField(String password) {

        driver.findElement(passwordField).sendKeys(password);

    }



    @Step("Click Register button")

    public void clickRegisterButton() {

        driver.findElement(registerButton).click();

    }



    @Step("Register new user")

    public void registerNewUser(String name, String email,String password){

        fillNameField(name);

        fillEmailField(email);

        fillPasswordField(password);

        clickRegisterButton();

    }



    @Step("Check successful registration")

    public void checkSuccessfulRegistration() {

        new WebDriverWait(driver, 5)

                .until(ExpectedConditions.visibilityOfElementLocated(enterText));

        String expected = driver.findElement(enterText).getText();

        Assert.assertEquals(expected, "Вход");

    }



    @Step("Check unsuccessful registration")

    public void checkUnsuccessfulRegistration() {

        new WebDriverWait(driver, 5)

                .until(ExpectedConditions.visibilityOfElementLocated(registerText));

        String expected = driver.findElement(wrongPasswordText).getText();

        Assert.assertEquals(expected, "Некорректный пароль");

    }

}
