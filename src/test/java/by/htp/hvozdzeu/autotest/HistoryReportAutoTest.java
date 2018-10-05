package by.htp.hvozdzeu.autotest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class HistoryReportAutoTest {

    private WebDriver driver;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testUntitledTestCase() throws Exception {
        driver.get("http://localhost/");
        driver.findElement(By.id("dropdownMenuButton")).click();
        driver.findElement(By.id("urlLocale")).click();
        driver.findElement(By.id("logOutBtn")).click();
        driver.findElement(By.id("dropdownMenuButton")).click();
        driver.findElement(By.id("urlLocale")).click();
        driver.findElement(By.id("dropdownMenuButton")).click();
        driver.findElement(By.id("urlLocale")).click();
        driver.findElement(By.id("loginBtn")).click();
        driver.findElement(By.id("loginInput")).click();
        driver.findElement(By.id("loginInput")).clear();
        driver.findElement(By.id("loginInput")).sendKeys("client");
        driver.findElement(By.id("pswInput")).clear();
        driver.findElement(By.id("pswInput")).sendKeys("client2525");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='iPay.GitHub.com'])[1]/preceding::i[1]")).click();
        driver.findElement(By.id("creditCardView")).click();
        driver.findElement(By.linkText("Card history")).click();
        driver.findElement(By.id("paymentHistoryCardNumber")).click();
        new Select(driver.findElement(By.id("paymentHistoryCardNumber"))).selectByVisibleText("4321 4321 4321 4321");
        driver.findElement(By.id("paymentHistoryCardNumber")).click();
        driver.findElement(By.id("paymentHistoryDateFrom")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Sa'])[1]/following::td[2]")).click();
        driver.findElement(By.id("paymentHistoryDateTo")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Sa'])[1]/following::td[32]")).click();
        driver.findElement(By.id("search-Btn")).click();
        driver.findElement(By.id("next_btn")).click();
        driver.findElement(By.id("dropdownMenuButton")).click();
        driver.findElement(By.id("urlLocale")).click();
        driver.findElement(By.id("logOutBtn")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

}
