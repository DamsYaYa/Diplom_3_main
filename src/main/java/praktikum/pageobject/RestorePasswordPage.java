package praktikum.pageobject;

import io.qameta.allure.Step;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

public class RestorePasswordPage {

    WebDriver driver;

    public RestorePasswordPage(WebDriver driver) {

        this.driver = driver;

    }
    private final By emailField = By.xpath(".//div[./label[text()='Email']]/input[@name='name']");

    private final By recoverButton = By.xpath(".//form/button[text()='Восстановить']");

    private final By loginLink = By.xpath(".//div/p/a[@href = '/login' and text() = 'Войти']");



    @Step("Fill email field")

    public void fillEmailField(String email) {

        driver.findElement(emailField).sendKeys(email);

    }


    @Step("Click Recover button")

    public void clickOnRecoverButton() {

        driver.findElement(recoverButton).click();

    }


    @Step("Click LogIn button")

    public void clickLoginButtonFromRecoverPasswordLink() {

        driver.findElement(loginLink).click();

    }


    @Step("Restore password")

    public void restorePassword(String email) {

        fillEmailField(email);

        clickOnRecoverButton();

    }

}