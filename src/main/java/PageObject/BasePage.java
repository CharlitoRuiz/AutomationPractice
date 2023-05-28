package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    public String pageURL;
    public WebDriver driver;

    public BasePage(String _pageURL, WebDriver _driver)
    {
        this.pageURL = _pageURL;
        this.driver = _driver;
    }
    public void configBrowser(String baseURL)
    {
        driver.navigate().to(baseURL);
        driver.manage().window().maximize();
    }
    public void waitUntilElementExists(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitUntilURL(String url)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains(url.toLowerCase()));
    }

}

