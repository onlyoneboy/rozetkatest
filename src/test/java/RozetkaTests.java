
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class RozetkaTests extends WebDriverSettings {

    public WebElement mobileTarget;
    public List<WebElement> mobileNames;
    public List<WebElement> mobilePrice;


    @Test
    public void firstTest() throws SQLException {
        driver.manage().window().maximize();
        driver.get(HomeRozetka.PAGE_URL);
        HomeRozetka.titleMainGet = driver.getTitle();
        Assert.assertTrue(HomeRozetka.TITLE_MAIN.equals(HomeRozetka.titleMainGet));

        mobileTarget = driver.findElement(By.xpath(HomeRozetka.MOBILE_BUTTON_LOCALE));
        moveToElementPhones();

        WebElement smartphoneTarget = driver.findElement(By.xpath(HomeRozetka.SMARTPHONES_BUTTON_LOCALE));
        smartphoneTarget.click();

        mobileNames = driver.findElements(By.cssSelector(HomeRozetka.MOBILE_LOCATOR));
        /*System.out.println("Количество телефонов: " + mobileNames.size());
        for (WebElement ele: mobileNames) {
            System.out.println(ele.getText());
    }*/
        mobilePrice = driver.findElements(By.cssSelector(HomeRozetka.MOBILE_PRICE));
        /*System.out.println("Количество цен: " + mobilePrice.size());
        for (WebElement pri: mobilePrice) {
            System.out.println(pri.getText());
        }*/

        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < mobileNames.size(); i++) {
            map.put(mobileNames.get(i).getText(), mobilePrice.get(i).getText());
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("Телефон:  " + entry.getKey() + " Цена = " + entry.getValue());
        }
        System.out.println("количество телефонов :" + mobileNames.size() + ". количество цен: " + mobilePrice.size());
        Connect();
        mySqlAdd();
    }

    public void moveToElementPhones() {
        Actions actions = new Actions(driver).moveToElement(mobileTarget);
        actions.moveToElement(mobileTarget);
        actions.pause(20000);
        actions.build().perform();
    }

    public void mySqlAdd() throws SQLException {
        for (int i =0; i < mobileNames.size(); i++  ) {
            String insrtSQL = "INSERT INTO price (named, sale) VALUES ('" + mobileNames.get(i).getText() + "','" + mobilePrice.get(i).getText() + "')";
            PreparedStatement stat =con.prepareStatement(insrtSQL);
            stat.executeUpdate();
        }
    }
}
