package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class TestVWOLogin {

    // 2 Test cases

    // One Negative - Invalid Username, password -> Error
    // One Positive - Valid Username, password -> Dashboard Page

    ChromeOptions options;
    WebDriver driver;

    @BeforeSuite
    /*public void setUp() {
     *//*  options = new ChromeOptions();
       //options.addArguments("--start-maximized");
       options.addArguments("--remote-allow-origins=*");
       driver = new ChromeDriver(options);*//*
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
       EdgeOptions edgeOptions = new EdgeOptions();
       //edgeOptions.setBinary("");
       //edgeOptions.addArguments("--start maximized");
       edgeOptions.addArguments("--incognito");
       //dgeOptions.addArguments("--window-size=500,300");
       edgeOptions.addArguments("--start maximized");
       //edgeOptions.setHeadless(true);

       driver = new EdgeDriver(edgeOptions);*/

    public void openBrowser() {
        // Create Session via the API and Session ID is generated
        driver = new EdgeDriver();
        driver.get("https://app.vwo.com/#/login");


    }


    @Test(priority = 1, groups = {"negative", "sanity", "reg"})
    @Severity(SeverityLevel.BLOCKER)
    @Description("TC#1 - Verify that with Invalid username and Valid password, Login is not successfull !!")
    public void testInvalidLogin() throws InterruptedException {

        //driver.get("https://app.vwo.com/#/login");
        driver.manage().window().maximize();
        WebElement username = driver.findElement(By.id("login-username"));
        username.sendKeys("93npu2yyb0@esiix.co");
        WebElement password = driver.findElement(By.id("login-password"));

        password.sendKeys("Wingify@123");
        driver.findElement(By.id("js-login-btn")).click();


        WebElement errorMessage = driver.findElement(By.className("notification-box-description"));
        // 3 seconds
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(errorMessage));

        String errorString = driver.findElement(By.className("notification-box-description")).getText();
        Assert.assertEquals(errorMessage.getText(), "Your email, password, IP address or location did not match");


    }

    @Test(enabled = true, priority = 2, groups = {"positive", "sanity", "stage"})
    @Description("Verify that with Valid username and Valid password, Login is successfull !!")
    public void testValidLogin() throws InterruptedException {

        // ID, Name, ClassName -> CssSelector, Xpath - Firefox or Chrome
        // xpath. CSS Selector -> Chrome, will not work Firefox.


        driver.get("https://app.vwo.com/#/login");
        WebElement username = driver.findElement(By.name("username"));
        username.clear();

        driver.findElement(By.id("login-username")).sendKeys("contact+atb5x@thetestingacademy.com");
        WebElement password = driver.findElement(By.id("login-password"));
        password.clear();

        driver.findElement(By.id("login-password")).sendKeys("ATBx@1234");
        //List<WebElement> button_submit=driver.findElements(By.xpath("//button[contains(@class, \"btn--positive\")]/span[text()=\"Sign in\"]"));
        //button_submit.get(0).click();
        List<WebElement> button_submit = driver.findElements(By.xpath("//button[contains(@class,\"btn--positive\")]/span[text()=\"Sign in\"]"));
        button_submit.get(0).click();

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[normalize-space()='Dashboard']")));
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".page-heading")));
        //wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("h1.page-heading")));


        // Assertion
        // Expected Result == Actual Result
        //WebElement name_on_dashboard_page = driver.wait(2000);

        Assert.assertEquals(driver.getTitle(), "Dashboard");
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getCurrentUrl(), "https://app.vwo.com/#/dashboard");
        System.out.println(driver.getCurrentUrl());

        WebElement name_on_dashboard_page_ele = driver.findElement(By.xpath("//span[@data-qa=\"lufexuloga\"]"));
        System.out.println(name_on_dashboard_page_ele.getText());

        Assert.assertEquals(name_on_dashboard_page_ele.getText(), "Aman");


        //Assert.assertEquals(name_on_dashboard_page.getText(),"Aman");


        //driver.get("https://app.vwo.com/logout");


    }

    @AfterSuite
    public void tearDown() {

        //driver.quit();
    }


}

////C:/Users/g.shrinivas.ganiga/IdeaProjects/