import com.opencart.navigation.Navigation;
import com.opencart.steps.MainPageBL;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.opencart.enums.URLs.BASE_URL;

public class UserRegisterTest extends BaseTest {

    @Test
    public void registerUserWithValidParameters() {
        new Navigation().navigateToUrl(BASE_URL.getValue());
        new MainPageBL().getHeaderPageBL()
                .clickOnMyAccountButton()
                .clickOnRegisterButton()
                .registerNewPerson()
                .verifyUserRegistration();
    }

    @AfterMethod
    public void logout() {
        new MainPageBL().logout();
    }
}
