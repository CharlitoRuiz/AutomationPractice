package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AddressPage extends BasePage {

    public AddressPage(String url, WebDriver driver)
    {
        super(url,driver);
    }

    // Locators
    private By inputFirstName= By.id("billing_first_name");
    private By inputLastName= By.id("billing_last_name");
    private By inputCompanyName= By.id("billing_company");
    private By inputEmail= By.id("billing_email");
    private By inputPhone= By.id("billing_phone");
    private By selectCountry= By.id("s2id_billing_country");
    private By inputCountry= By.id("s2id_autogen1_search");
    private By inputAddress= By.id("billing_address_1");
    private By inputCity= By.id("billing_city");
    private By selectState= By.id("s2id_billing_state");
    private By inputState= By.id("billing_state");
    private By inputUSState= By.id("s2id_autogen4096_search");
    private By inputZIP= By.id("billing_postcode");
    private By btnSaveAddress= By.cssSelector(".button[type='submit']");

    /* Messages */
    private By msgAddressChanged = By.cssSelector(".woocommerce-message");

    /* Menu links */
    private By lnkAddresses = By.cssSelector("a[href='https://practice.automationtesting.in/my-account/edit-address/']");
    private By lnkEditBillingAddress = By.cssSelector("a.edit[href='https://practice.automationtesting.in/my-account/edit-address/billing']");


    /* ===== Set Locators ===== */
    public WebElement set_inputFirstName() { return driver.findElement(this.inputFirstName);}
    public WebElement set_inputLastName() { return driver.findElement(this.inputLastName);}
    public WebElement set_inputCompanyName() { return driver.findElement(this.inputCompanyName);}
    public WebElement set_inputEmail() { return driver.findElement(this.inputEmail);}
    public WebElement set_inputPhone() { return driver.findElement(this.inputPhone);}
    public WebElement set_selectCountry() { return driver.findElement(this.selectCountry);}
    public WebElement set_inputCountry() { return driver.findElement(this.inputCountry);}

    public WebElement set_inputAddress() { return driver.findElement(this.inputAddress);}
    public WebElement set_inputCity() { return driver.findElement(this.inputCity);}
    public WebElement set_selectState() { return driver.findElement(this.selectState);}
    public WebElement set_inputState() { return driver.findElement(this.inputState);}
    public WebElement set_inputUSState() { return driver.findElement(this.inputUSState);}
    public WebElement set_inputZIP() { return driver.findElement(this.inputZIP);}

    public WebElement set_btnSaveAddress() { return driver.findElement(this.btnSaveAddress);}

    /* ===== Set Messages ===== */
    public WebElement displayMsgAddressChanged() { return driver.findElement(this.msgAddressChanged);}


    /* ===== Set menu links ===== */
    public WebElement set_lnkAddresses() { return driver.findElement(this.lnkAddresses);}
    public WebElement set_lnkEditBillingAddress() { return driver.findElement(this.lnkEditBillingAddress);}


    // ====================== Methods ======================= //
    public void enterAddressesMenu(){
        set_lnkAddresses().click();
        set_lnkEditBillingAddress().click();
    }
    public void enterPersonalInfo(String firstName, String lastName, String companyName, String email, String phone) {
        set_inputFirstName().sendKeys(firstName);
        set_inputLastName().sendKeys(lastName);
        set_inputCompanyName().sendKeys(companyName);
        set_inputEmail().sendKeys(email);
        set_inputPhone().sendKeys(phone);
    }
    public void enterCountry(String country) {
        set_selectCountry().click();
        waitUntilElementExists(set_inputCountry());
        set_inputCountry().sendKeys(country + Keys.TAB);
    }
    public void enterAddress(String address, String city, String state, String zipCode) {
        set_inputAddress().sendKeys(address);
        set_inputCity().sendKeys(city);
        set_inputState().sendKeys(state);
        set_inputZIP().sendKeys(zipCode);
    }

    public void selectUSState(String usState) {
        set_selectState().click();
        waitUntilElementExists(set_inputUSState());
        set_inputUSState().sendKeys(usState + Keys.TAB);
    }
    public void clearAllInputText()
    {
        List<WebElement> Form = driver.findElements(By.cssSelector(".input-text"));
        for(int i=0;i<Form.size();i++)
        {
            WebElement element = Form.get(i);
            element.sendKeys(Keys.CONTROL + "a");
            element.sendKeys(Keys.DELETE);
        }
    }
    public void clickSaveAddressButton(){
        set_btnSaveAddress().click();
    }
}
