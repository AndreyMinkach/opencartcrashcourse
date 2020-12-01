package com.opencart.steps;

import com.opencart.pages.HeaderPage;
import org.testng.Assert;

public class HeaderPageBL {

    private HeaderPage headerPage;

    public HeaderPageBL() {
        headerPage = new HeaderPage();
    }

    public HeaderPageBL clickOnMyAccountButton() {
        headerPage.getMyAccountButton().click();
        return this;
    }

    public LoginPageBL clickOnLoginButton() {
        headerPage.getLoginButton().click();
        return new LoginPageBL();
    }

    public LogoutPageBL clickOnLogoutButton() {
        headerPage.getLogoutButton().click();
        return new LogoutPageBL();
    }

    public RegisterPageBL clickOnRegisterButton() {
        headerPage.getRegisterButton().click();
        return new RegisterPageBL();
    }

    public void checkLoginButton() {
        Assert.assertTrue(headerPage.getLoginButton().isDisplayed(), "Account did not log out");
    }
}
