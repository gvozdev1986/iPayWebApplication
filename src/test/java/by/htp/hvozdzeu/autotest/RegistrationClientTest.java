package by.htp.hvozdzeu.autotest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RegistrationClientTest {

    private static WebDriver driver;
    private static List<String[]> bdContent = new ArrayList<>();

    @BeforeClass
    public static void setup() {
//        System.setProperty("webdriver.chrome.driver", "D:\\RESOURCES\\chromedriver_win32\\chromedriver.exe");
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.get("http://localhost/");
    }

    @Test
    public void userRegistration() {
//        WebElement registrationViewBtn = driver.findElement(By.id("registrationViewBtn"));
//        registrationViewBtn.click();
//
//        WebElement inputLogin = driver.findElement(By.id("login"));
//        inputLogin.sendKeys("qwerty152");
//
//        WebElement inputPassword = driver.findElement(By.id("pswd"));
//        inputPassword.sendKeys("qwerty152");
//
//        WebElement insertVerifyPassword = driver.findElement(By.id("pswd_verify"));
//        insertVerifyPassword.sendKeys("qwerty152");
//
//        WebElement insertLastName = driver.findElement(By.id("last_name"));
//        insertLastName.sendKeys("Гвоздев");
//
//        WebElement insertFirstName = driver.findElement(By.id("first_name"));
//        insertFirstName.sendKeys("Александр");
//
//        WebElement insertMiddleName = driver.findElement(By.id("patronymic"));
//        insertMiddleName.sendKeys("Николаевич");
//
//        WebElement insertDateBirth = driver.findElement(By.id("date_birth"));
//        insertDateBirth.sendKeys("1986");
//        insertDateBirth.sendKeys(Keys.RIGHT);
//        insertDateBirth.sendKeys("04");
//        insertDateBirth.sendKeys("25");
//
//        WebElement insertHomeName = driver.findElement(By.id("home_phone"));
//        insertHomeName.sendKeys("8-029-147-36-24");
//
//        WebElement insertHomePhone = driver.findElement(By.id("mobile_phone"));
//        insertHomePhone.sendKeys("+375291473624");
//
//        WebElement insertEmail = driver.findElement(By.id("email"));
//        insertEmail.sendKeys("aliaksandr.hvozdzeu@gmail.com");
//
//        WebElement insertAddress = driver.findElement(By.id("address"));
//        insertAddress.sendKeys("Калиновского дом 31 квартира 46");
//
//        WebElement agreeCheckBox = driver.findElement(By.id("invalidCheck"));
//        agreeCheckBox.click();
//
//        WebElement saveViewBtn = driver.findElement(By.id("save_btn"));
//        saveViewBtn.click();
    }

    @AfterClass
    public static void tearDown() {
       // driver.quit();
    }
}
