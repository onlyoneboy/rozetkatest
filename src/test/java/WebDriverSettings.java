import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;



public class WebDriverSettings extends MySQLConnect {
    public ChromeDriver driver;

    @Before
    public void setUp()  {
        System.setProperty(HomeRozetka.DRIVER_PROPERTHY, HomeRozetka.PATH_DRIVER);
        driver = new ChromeDriver();
        //Connect();

    }

    @After
    public void closeInstance() {
        driver.quit();

    }

}
