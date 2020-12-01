package com.opencart.steps;

import com.opencart.navigation.Navigation;

import static com.opencart.enums.URLs.BASE_URL;

public class MainPageBL {

    public HeaderPageBL getHeaderPageBL() {
        return new HeaderPageBL();
    }

    public void registerNewUser() {
        new Navigation().navigateToUrl(BASE_URL.getValue());
        getHeaderPageBL()
                .clickOnMyAccountButton()
                .clickOnRegisterButton()
                .registerNewPerson()
                .verifyUserRegistration();
        logout();
    }

    public void logout() {
        getHeaderPageBL()
                .clickOnMyAccountButton()
                .clickOnLogoutButton()
                .clickContinue();
        verifyLogout();
    }

    private void verifyLogout() {
        getHeaderPageBL()
                .clickOnMyAccountButton()
                .checkLoginButton();
    }
}
