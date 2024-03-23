package tests;

import org.example.drivers.Driver;
import org.example.utils.PropertyManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.List;

public class DemoqaWebtablesTest {
    public static WebDriver driver;
    Driver webDriver = new Driver();

    @BeforeSuite
    public void before() throws MalformedURLException {
        driver = webDriver.initializeDriver();
        driver.get("https://demoqa.com/webtables");
    }
    @Test(priority = 1)
    public void clickAddButton(){
        WebElement addButton = driver.findElement(By.xpath("//button[@id='addNewRecordButton']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",addButton);
        addButton.click();
        WebElement formModal = driver.findElement(By.xpath("//div[@id='registration-form-modal']"));
        Assert.assertEquals(formModal.getText(),"Registration Form");
    }
    @Test(priority = 2)
    public void addNewSubmit(){
        WebElement firstName = driver.findElement(By.xpath("//input[@id='firstName']"));
        WebElement lastName = driver.findElement(By.xpath("//input[@id='lastName']"));
        WebElement userEmail = driver.findElement(By.xpath("//input[@id='userEmail']"));
        WebElement age = driver.findElement(By.xpath("//input[@id='age']"));
        WebElement salary = driver.findElement(By.xpath("//input[@id='salary']"));
        WebElement department = driver.findElement(By.xpath("//input[@id='department']"));
        firstName.sendKeys("Simge");
        lastName.sendKeys("Kurtuldu");
        userEmail.sendKeys("simge@gmail.com");
        age.sendKeys("26");
        salary.sendKeys("10000");
        department.sendKeys("Test");
        WebElement submitButton = driver.findElement(By.xpath("//button[@id='submit']"));
        submitButton.click();
        WebElement addButton = driver.findElement(By.xpath("//button[@id='addNewRecordButton']"));
        Assert.assertTrue(addButton.isDisplayed());
    }
    @Test(priority = 3)
    public void EditSubmit(){
        WebElement editButton = driver.findElement(By.xpath("//span[@id='edit-record-4']"));
        editButton.click();
        WebElement firstText = driver.findElement(By.xpath("//input[@id='firstName']"));
        firstText.clear();
        firstText.sendKeys("Ahmet");
        driver.findElement(By.xpath("//button[@id='submit']")).click();
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='rt-tr-group']"));
        String[] lines = elements.get(3).getText().split("\\n");
        String firstLine = lines[0];
        Assert.assertEquals(firstLine,"Ahmet");
    }
    @AfterSuite
    public void after() throws MalformedURLException{
        driver.quit();
    }
}
