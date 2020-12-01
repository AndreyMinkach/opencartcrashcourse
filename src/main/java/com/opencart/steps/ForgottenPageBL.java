package com.opencart.steps;

import com.opencart.datamodel.LoginModel;
import com.opencart.datamodel.RegisterModel;
import com.opencart.navigation.Navigation;
import com.opencart.pages.ForgottenPasswordPage;
import com.opencart.pages.SuccessResetPasswordPage;
import com.opencart.repository.LoginModelRepository;
import com.opencart.util.RandomEmailUtil;
import org.testng.Assert;

public class  ForgottenPageBL {

    public static String newPassword;
    private ForgottenPasswordPage forgottenPasswordPage;
    private SuccessResetPasswordPage successResetPasswordPage;
    LoginModel loginModel = LoginModelRepository.getLoginModel();

    public ForgottenPageBL() {
        forgottenPasswordPage = new ForgottenPasswordPage();
    }

    public ForgottenPageBL changePasswordWithURL() {
        changePasswordWithURL(loginModel.getEmail());
        return this;
    }

    public ForgottenPageBL changePasswordWithURL(String email) {
        inputEmail(email);
        clickOnContinueButton();
        RandomEmailUtil.email = email;
        new Navigation().navigateToUrl(RandomEmailUtil.getChangePasswordURL());
        ForgottenPageBL.newPassword = loginModel.getPassword();
        inputNewPassword(ForgottenPageBL.newPassword);
        clickOnContinueButton();

        successResetPasswordPage = new SuccessResetPasswordPage();
        return this;
    }

    private void inputNewPassword(String password) {
        forgottenPasswordPage.getNewPasswordInput().clear();
        forgottenPasswordPage.getNewPasswordInput().sendKeys(password);
        forgottenPasswordPage.getNewPasswordConfirmInput().clear();
        forgottenPasswordPage.getNewPasswordConfirmInput().sendKeys(password);
    }

    private void inputEmail(String email) {
        forgottenPasswordPage.getForgottenEmailInput().clear();
        forgottenPasswordPage.getForgottenEmailInput().sendKeys(email);
    }

    private void clickOnContinueButton() {
        forgottenPasswordPage.getContinueButton().click();
    }

    public void verifyChangePassword() {
        String expectedMessage = "Success: Your password has been successfully updated.";
        Assert.assertEquals(successResetPasswordPage.getSuccessAlert().getText(), expectedMessage, "Incorrect page title");
    }
}
