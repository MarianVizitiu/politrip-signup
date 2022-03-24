package com.politrip.tests;

import com.github.javafaker.Faker;
import com.politrip.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class politripSignUpTest {


    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void test1() throws InterruptedException {
        driver.get("https://politrip.com/account/sign-up");
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]")).click();


        WebElement SignUp = driver.findElement(By.xpath("/html/body/app-root/app-access-account/app-page-template/div/app-sign-up/app-sign-in-container/div/div[1]/div[1]/span[2]"));
        System.out.println("First page title: "+SignUp.getText());



        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String emailAddress = faker.internet().emailAddress();
        String password = faker.internet().password();
        String pL = faker.toString().toUpperCase();


        WebElement firstNameButton = driver.findElement(By.id("first-name"));
        firstNameButton.sendKeys(firstName);
        Thread.sleep(1000);

        WebElement lastNameButton = driver.findElement(By.xpath("//*[@id=\"last-name\"]"));
        lastNameButton.sendKeys(lastName);
        Thread.sleep(1000);

        WebElement emailButton = driver.findElement(By.id("email"));
        emailButton.sendKeys(emailAddress);
        Thread.sleep(1000);

        WebElement passwordButton = driver.findElement(By.id("sign-up-password-input"));
        passwordButton.sendKeys(password+pL);
        Thread.sleep(1000);

        WebElement confirmPasswordButton = driver.findElement(By.id("sign-up-confirm-password-input"));
        confirmPasswordButton.sendKeys(password+pL);
        Thread.sleep(3000);



        WebElement heardInputButton = driver.findElement(By.id("sign-up-heard-input"));

        JavascriptExecutor jse = (JavascriptExecutor) driver;

        jse.executeScript("arguments[0].scrollIntoView(true);", heardInputButton);


        Select stateDropdown = new Select(heardInputButton);

        stateDropdown.selectByIndex(1);
        Thread.sleep(2000);

        WebElement signUpButton = driver.findElement(By.id(" qa_loader-button"));

        Thread.sleep(1000);
        signUpButton.click();

        System.out.println("Title: " + driver.getTitle());
        Thread.sleep(1000);

        WebElement signUBox = driver.findElement(By.cssSelector("div.content-wrapper>div>div>div>div.checkbox-container>app-checkbox>div>div.form-group>input[id='terms-checkbox']"));
        Assert.assertTrue(signUBox.isSelected(),"checking if sign up checkbox is checked");

        System.out.println("Before clicking ");

        /*WebElement signUpCheckBox = driver.findElement(By.xpath("//div[@class=\"checkbox-label-text\" and contains(.,\"agree\")]"));
        signUpCheckBox.click(); */   //uncheck the box



        Thread.sleep(1000);
        System.out.println("After clicking ");


        WebElement participantOption = driver.findElement(By.cssSelector("div.content-wrapper>div>div>div>div[id=\"qa_signup-participant\"]"));

        Assert.assertTrue(participantOption.isEnabled(), "checking if participant button is active");
        System.out.println("Checking is done");
        participantOption.click();

        Thread.sleep(1000);
        String baseUrl = "https://politrip.com/account/sign-up/type-select";

        Assert.assertEquals(driver.getCurrentUrl(),baseUrl, "checking if landed on homepage URL");


        Thread.sleep(3000);

    }

}

