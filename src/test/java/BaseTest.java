import com.opencart.driver.DriverRepository;
import com.opencart.helpers.PropertiesHelper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void setup() {
        PropertiesHelper.loadProperties();
        DriverRepository.downloadWebDriver(PropertiesHelper.getValue("browser.name"));
    }

    @BeforeClass
    public void createDriver() {
        DriverRepository.instanceWebBrowser();

    }

    @AfterClass
    public void closeBrowser() {
        DriverRepository.closeBrowser();
    }
}
