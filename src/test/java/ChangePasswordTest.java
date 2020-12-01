import com.opencart.helpers.DBHelper;
import com.opencart.navigation.Navigation;
import com.opencart.steps.*;
import com.opencart.util.RandomEmailUtil;
import org.testng.annotations.*;

import static com.opencart.enums.URLs.ADMIN_PAGE_URL;
import static com.opencart.enums.URLs.BASE_URL;

public class ChangePasswordTest extends BaseTest {

    private String randomEmail;

    @BeforeMethod
    public void registerUserWithRandomEmail() {
        new Navigation().navigateToUrl(BASE_URL.getValue());
        new MainPageBL().registerNewUser();
        randomEmail = RandomEmailUtil.email;
    }

    @Test
    public void unlockUserWithDB() {
        new Navigation().navigateToUrl(BASE_URL.getValue());
        new LoginPageBL().lockUser(randomEmail)
                .changePassword(randomEmail);
        DBHelper.unlockUser(randomEmail);
        new MainPageBL().getHeaderPageBL()
                .clickOnMyAccountButton()
                .clickOnLoginButton()
                .loginUser(randomEmail, ForgottenPageBL.newPassword)
                .verifyUserLogin();
    }

    @Test
    public void unlockUserWithAdminPanel() {
        Navigation navigation = new Navigation();
        navigation.navigateToUrl(BASE_URL.getValue());
        new LoginPageBL().lockUser(randomEmail)
                .changePassword(randomEmail);
        navigation.openNewTab(ADMIN_PAGE_URL.getValue());
        new AdminPageBL().loginAdmin()
                .unlockUser(randomEmail)
                .clickOnMyAccountButton()
                .clickOnLoginButton()
                .loginUser(randomEmail, ForgottenPageBL.newPassword)
                .verifyUserLogin();
    }

    @AfterMethod
    public void logout() {
        new MainPageBL().logout();
    }
}
