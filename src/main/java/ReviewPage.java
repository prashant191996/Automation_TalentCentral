import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.Select;


import java.time.Duration;


public class ReviewPage {


    @Test
    public void test() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.get("https://talentcentral-2a.eu.shl.zone/admin/");
        driver.findElement(By.linkText("OK")).click();
        driver.findElement(By.id("username")).sendKeys("test09.test@yopmail.com");
        driver.findElement(By.id("password")).sendKeys("Test@1924");
        driver.findElement(By.id("proceed")).click();

        WebElement companySelect = driver.findElement(By.xpath("//select[@name='j_company']"));
        Select chooseCompany = new Select(companySelect);
        chooseCompany.selectByValue("1");

        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();

        driver.findElement(By.linkText("Review")).click();
        driver.findElement(By.linkText("Projects")).click();

        //WebElement searchBox = driver.findElement(By.id("ui-collapse-628"));

        String className = driver.findElement(By.xpath("//i[@data-icon-hide='shl-icon_arrow_small_down']")).getAttribute("class");

        if (className.contains("shl-icon_arrow_small_down")) {
            driver.findElement(By.id("nameID")).sendKeys("OPQ redesign automation");
            driver.findElement(By.id("genericFilterSearchBtn")).click();

        } else {
            driver.findElement(By.xpath("//a[@id='ui-collapse-847']")).click();
            driver.findElement(By.id("nameID")).sendKeys("OPQ redesign automation");
            driver.findElement(By.id("genericFilterSearchBtn")).click();
        }
        /**
         * for handling server pop up
         try {
         WebDriverWait wait = new WebDriverWait(driver, 10);
         if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
         System.out.println("Alert not Displayed");
         } else {
         driver.switchTo().alert().accept();
         }
         } catch (Exception e) {
         throw new RuntimeException(e);
         }
         **/
        //driver.findElement(By.linkText("OPQ redesign automation")).click();
        clickOnElement(driver.findElement(By.linkText("OPQ redesign automation")), driver);


    }
    /*create new JavascriptExecutor for click operation on line number 42*/
    public static void clickOnElement (WebElement element, WebDriver driver) throws InterruptedException {

        try {
            element.click();
            Thread.sleep(5000);
        } catch (Exception e) {

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
            Thread.sleep(5000);
        }
    }
}
