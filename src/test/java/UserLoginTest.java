import com.opencart.navigation.Navigation;
import com.opencart.steps.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static com.opencart.enums.URLs.BASE_URL;

public class UserLoginTest extends BaseTest {

    @BeforeMethod
    public void registerUser() {
        new MainPageBL().registerNewUser();
    }

    @Test
    public void changeUserPassword() {
        new Navigation().navigateToUrl(BASE_URL.getValue());
        MainPageBL mainPageBL = new MainPageBL();
        mainPageBL.getHeaderPageBL()
                .clickOnMyAccountButton()
                .clickOnLoginButton()
                .clickOnForgottenPassword()
                .changePasswordWithURL()
                .verifyChangePassword();
        mainPageBL.getHeaderPageBL()
                .clickOnMyAccountButton()
                .clickOnLoginButton()
                .verifyLoginWithNewPassword();
    }

    @AfterMethod
    public void logoutOfUserProfile() {
        new MainPageBL().logout();
    }
}
