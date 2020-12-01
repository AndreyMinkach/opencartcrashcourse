package com.opencart.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


@Getter
public class HeaderPage extends BasePage {

    @FindBy(xpath = ".//*[@title='My Account']")
    private WebElement myAccountButton;

    @FindBy(xpath = ".//*[contains(@href, 'login')]")
    private WebElement loginButton;

    @FindBy(xpath = ".//*[contains(@href, 'account/logout')]")
    private WebElement logoutButton;

    @FindBy(xpath = ".//*[contains(@href,'register')]")
    private WebElement registerButton;

    public WebElement getMyAccountButton() {
        wait.until(ExpectedConditions.visibilityOf(myAccountButton));
        return myAccountButton;
    }
}
