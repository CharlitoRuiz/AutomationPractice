package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    public LoginPage(String url, WebDriver driver)
    {
        super(url,driver);
    }
    // Locators
    private By username=By.id("username");
    private By password= By.id("password");
    private By btnLogin= By.name("login");
    private By mainPageLogo= By.id("site-logo");

    /* Messages */
    private By loginTitle = By.cssSelector(".u-column1 h2");
    private By msgFailed = By.cssSelector(".woocommerce-error li");
    private By accountContent = By.cssSelector(".woocommerce-MyAccount-content strong");

    /* Menu links */
    private By lnkMyAccount= By.cssSelector("a[href='https://practice.automationtesting.in/my-account/']");
    private By lnkLogOut= By.cssSelector(".woocommerce-MyAccount-navigation-link--customer-logout a");


    /* ===== Set Locators ===== */
    public WebElement set_username() { return driver.findElement(this.username);}
    public WebElement set_password() { return driver.findElement(this.password);}
    public WebElement set_btnLogin() { return driver.findElement(this.btnLogin);}
    public WebElement mainPageLogo() { return driver.findElement(this.mainPageLogo);}

    /* ===== Set Messages ===== */
    public WebElement displayLoginTitle() { return driver.findElement(this.loginTitle);}
    public WebElement displayFailMessage() { return driver.findElement(this.msgFailed);}
    public WebElement displayAccountContent() { return driver.findElement(this.accountContent);}


    /* ===== Set menu links ===== */
    public WebElement set_lnkMyAccount() { return driver.findElement(this.lnkMyAccount);}
    public WebElement set_lnkLogOut() { return driver.findElement(this.lnkLogOut);}

    // ====================== Methods ======================= //
    public void enterCredentials(String username, String password) {
        set_username().sendKeys(username);
        set_password().sendKeys(password);
    }
    public void clickLoginButton(){
        set_btnLogin().click();
    }
    public void clickLogOutButton() {
        set_lnkLogOut().click();
    }
    public void enterMyAccountOption() {
        set_lnkMyAccount().click();
    }
}
