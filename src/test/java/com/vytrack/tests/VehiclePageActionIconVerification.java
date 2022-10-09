package com.vytrack.tests;

import com.vytrack.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

// 1. Go to the login page: "https://qa1.vytrack.com/user/login"
// 2. Enter valid user credentials and sign in to the system
// 3. From Home Page -> Click "Fleet" module -> Click "Vehicles" option
// 4. You are on "All Cars" page-> Verify under each car info there are "three dots"
// 5. Verify there are "View", "Edit" and "Delete" icons when you hover over the mouse to the three dots
public class VehiclePageActionIconVerification {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void login(String userName, String password, String userType) {
        driver.get("https://qa1.vytrack.com/user/login");

        WebElement usernameInput = driver.findElement(By.xpath("//input[@id='prependedInput']"));
        usernameInput.sendKeys(userName);
        WebElement passwordInput = driver.findElement(By.cssSelector("input[id='prependedInput2']"));
        passwordInput.sendKeys(password);
        WebElement loginBtn = driver.findElement(By.cssSelector("button[id='_submit']"));
        loginBtn.click();
        System.out.println("Signed in as " + userType);
    }

    public void logout() {
        WebElement userProfileBtn = driver.findElement(By.xpath("//*[@id='user-menu']/a"));
        userProfileBtn.click();
        WebElement logOutBtn = driver.findElement(By.xpath("//a[@href='/user/logout']"));
        logOutBtn.click();
//        OR
        // driver.navigate().to("https://qa1.vytrack.com/user/logout");
        System.out.println("Signing out");
    }

    private void verifyActionItems(int menuPosition) throws InterruptedException {
        WebElement fleetModule = driver.findElement(By.xpath("((//div[@id='main-menu'])//span[@class='title title-level-1'])[" + menuPosition + "]"));
        fleetModule.click();
        WebElement vehiclesOption = driver.findElement(By.xpath("//span[text()='Vehicles']"));
        vehiclesOption.click();
        WebElement threeDots = driver.findElement(By.xpath("(//td[@class='action-cell grid-cell grid-body-cell'])[1]"));
        Actions action = new Actions(driver);
        action.moveToElement(threeDots).perform();
        Thread.sleep(1000);

//        WebElement delete = new LinkedList<>(driver.findElements(By.xpath("//a[@title='Delete']"))).getLast();
        WebElement deleteIcon = driver.findElement(By.xpath("//a[contains(@class,'mode-icon-only')][@title='Delete']"));
        Assert.assertTrue(deleteIcon.isDisplayed(), "Delete Icon is displayed verification: FAIL");
        WebElement viewIcon = driver.findElement(By.xpath("//a[@title='View']"));
        Assert.assertTrue(viewIcon.isDisplayed(), "View Icon is displayed verification: FAIL");
        WebElement editIcon = driver.findElement(By.xpath("//a[@title='Edit']"));
        Assert.assertTrue(editIcon.isDisplayed(), "Edit Icon is displayed verification: FAIL");
        Thread.sleep(500);
    }

    @Test
    public void TruckDriverUserTest() throws InterruptedException {
        login("user38", "UserUser123", "Truck Driver");
        verifyActionItems(1);
        logout();


    }


    @Test
    public void StoreManagerUserTest() throws InterruptedException {
        login("storemanager83", "UserUser123", "Store Manager");
        verifyActionItems(2);
        logout();

    }

    @Test
    public void SalesManagerUserTest() throws InterruptedException {
        login("salesmanager129", "UserUser123", "Sales Manager");
        verifyActionItems(2);
        logout();

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
