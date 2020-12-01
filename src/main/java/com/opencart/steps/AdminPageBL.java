package com.opencart.steps;

import com.opencart.enums.AdminNavigationMenuButtons;
import com.opencart.helpers.PropertiesHelper;
import com.opencart.navigation.Navigation;
import com.opencart.pages.AdminPage;
import org.testng.Assert;

public class AdminPageBL {
    private AdminPage adminPage;

    public AdminPageBL() {
        adminPage = new AdminPage();
    }


    public AdminPageBL loginAdmin() {
        inputAdminUsername(PropertiesHelper.getValue("admin.login"));
        inputAdminPassword(PropertiesHelper.getValue("admin.password"));
        clickOnLoginButton();
        return new AdminPageBL();
    }

    public HeaderPageBL unlockUser(String userEmail) {
        clickOnMenuButton(AdminNavigationMenuButtons.CUSTOMERS, "Customers");
        inputUsernameToUnlock(userEmail);
        clickOnFilterButton();
        clickOnActionDropdownButton();
        clickOnUnlockAccountButton();
        verifyUnlockAccount();
        new Navigation().closeCurrentTab();
        new Navigation().switchToTab(0);
        return new HeaderPageBL();
    }

    private void inputUsernameToUnlock(String customerEmail) {
        adminPage.customerEmailInput.clear();
        adminPage.customerEmailInput.sendKeys(customerEmail);
    }

    public void inputAdminUsername(String username) {
        adminPage.adminNameInput.clear();
        adminPage.adminNameInput.sendKeys(username);
    }

    public void inputAdminPassword(String password) {
        adminPage.adminPasswordInput.clear();
        adminPage.adminPasswordInput.sendKeys(password);
    }

    public void clickOnMenuButton(AdminNavigationMenuButtons button, String submenuName) {
        adminPage.clickOnMenuButton(button).click();
        adminPage.clickOnSubmenuButton(button, submenuName).click();
    }

    public void clickOnLoginButton() {
        adminPage.loginButton.click();
    }

    public void clickOnActionDropdownButton() {
        adminPage.actionDropdownButton.click();
    }

    public void clickOnUnlockAccountButton() {
        adminPage.unlockAccountButton.click();
    }

    public void clickOnFilterButton() {
        adminPage.filterButton.click();
    }

    public void verifyUnlockAccount() {
        String expectedMessage = "Success: You have modified customers!";
        Assert.assertTrue(adminPage.successAlert.getText().contains(expectedMessage), "Customer has not modified");
    }
}
