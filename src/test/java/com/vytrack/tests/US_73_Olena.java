package com.vytrack.tests;

import com.vytrack.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class US_73_Olena {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        //1. Set up browser
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //3. Go to : https://qa1.vytrack.com/user/login
        driver.get("https://qa1.vytrack.com/user/login");
    }

    @Test
    public void userStory_73() throws InterruptedException {
        //2.User must be logged in
        WebElement userName = driver.findElement(By.xpath("//input[@id='prependedInput']"));
        userName.sendKeys("storemanager83");
        Thread.sleep(2000);

        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='prependedInput2']"));
        passwordInput.sendKeys("UserUser123");
        Thread.sleep(2000);

        WebElement loginBtn = driver.findElement(By.xpath("//button[@id='_submit']"));
        Thread.sleep(2000);
        loginBtn.click();



        Thread.sleep(2000);
        WebElement activitiesDropDown = driver.findElement(By.xpath("//*[@id='main-menu']/ul/li[5]/a/span"));

        Actions action= new Actions(driver);
        Thread.sleep(2000);
        action.moveToElement(activitiesDropDown).perform();


        Thread.sleep(2000);
        WebElement calendarEvents = driver.findElement(By.xpath("//*[@id='main-menu']/ul/li[5]/div/div/ul/li[4]/a"));
        calendarEvents.click();



        Thread.sleep(2000);
        WebElement createCalendarBtn = driver.findElement(By.xpath("//div[@class='pull-right title-buttons-container']/div/a"));
        createCalendarBtn.click();

        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Thread.sleep(2000);
        WebElement iFrame = driver.findElement(By.tagName("iframe"));
        //(By.xpath("//iframe[@id='oro_calendar_event_form_description-uid-634dfcb26370f_ifr']"));
        Thread.sleep(2000);
        driver.switchTo().frame(iFrame);


        Thread.sleep(2000);
        WebElement typeBox = driver.findElement(By.xpath("//body[@id='tinymce']/p"));
        Thread.sleep(2000);
        typeBox.sendKeys("Scrum Daily Meeting");


        Thread.sleep(2000);
        String expectedMessage = "Scrum Daily Meeting";
        String actualMessage = typeBox.getText();
        Assert.assertEquals(actualMessage,expectedMessage);




    }
    @AfterMethod
    public void quitMethod(){
        driver.quit();
    }
}
