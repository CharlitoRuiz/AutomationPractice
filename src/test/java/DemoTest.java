import DataProviders.AddressProvider;
import DataProviders.LoginProvider;
import DataProviders.OrderProvider;
import POJO.AddressData;
import POJO.LoginData;
import POJO.OrderData;
import PageObject.*;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DemoTest extends BaseTest{

    @Test(priority=1, dataProvider = "getLoginCredentials", dataProviderClass = LoginProvider.class)
    @Parameters({"username, password"})
    public void SignIn(LoginData _loginData)throws InterruptedException {
        test = extent.createTest("Valid Login", "Log-in with valid username and password.");
        LoginPage loginpage= new LoginPage(baseURL,driver);

        /* Variables */
        String userName = _loginData.getUsername();
        String password = _loginData.getPassword();

        loginpage.waitUntilElementExists(loginpage.displayLoginTitle());
        Assert.assertEquals(loginpage.displayLoginTitle().getText(),"Login",
                "The page did not load correctly");
        test.log(Status.PASS,"The page load correctly : Test Failed");

        loginpage.enterCredentials(userName, password);
        test.log(Status.PASS,"Enter the valid credentials in the page");
        loginpage.clickLoginButton();
        test.log(Status.PASS,"Click on Login button");
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("/my-account/"),
                "The login page did not enter correctly : Test Failed");

    }

    @Test(priority=2, dataProvider = "getLoginCredentials", dataProviderClass = LoginProvider.class)
    @Parameters({"username"})
    public void ValidEnterSite(LoginData _loginData)throws InterruptedException  {
        test = extent.createTest("Validate correct session", "Validate welcome message");
        LoginPage loginpage= new LoginPage(baseURL,driver);
        BasePage basepage= new BasePage(baseURL,driver);

        /* Variables */
        int position = _loginData.getUsername().indexOf("@");
        String loginName = _loginData.getUsername().substring(0, position);

        // Validation
        basepage.waitUntilElementExists(loginpage.displayAccountContent());
        Assert.assertEquals(loginpage.displayAccountContent().isDisplayed(),true);
        test.log(Status.PASS,"The page show the dashboard page");
        Assert.assertTrue(loginpage.displayAccountContent().getText().toLowerCase().equals(loginName),
                "The login name is not correct : Test Failed");
        test.log(Status.PASS,"The page show the correct login name");
    }

    @Test(priority=3, dataProvider = "getAddressInfo", dataProviderClass = AddressProvider.class)
    @Parameters({"firstName, lastName, companyName, email, phone, country, address, city, state, zip"})
    public void EditBillingAddress(AddressData _addressData) throws InterruptedException {
        test = extent.createTest("Add / Edit billing address", "Add or edit my account billing address");
        AddressPage addresspage= new AddressPage(baseURL,driver);
        BasePage basepage= new BasePage(baseURL,driver);

        /* Variables */
        String firstName = _addressData.getFirstName(), lastName = _addressData.getLastName(), companyName = _addressData.getCompanyName(),
                email = _addressData.getEmail(), phone = _addressData.getPhone(), country = _addressData.getCountry(),
                address = _addressData.getAddress(), city = _addressData.getCity(), state = _addressData.getState(),
                usState = _addressData.getUSState(), zip = _addressData.getZip();

        addresspage.enterAddressesMenu();
        test.log(Status.PASS,"Enter to Addresses menu and Edit billing address");
        Assert.assertEquals(driver.getCurrentUrl().toLowerCase(),
                "https://practice.automationtesting.in/my-account/edit-address/billing/",
                "The URL is not match : Test Failed");

        addresspage.clearAllInputText();
        addresspage.enterPersonalInfo(firstName, lastName, companyName, email, phone);
        addresspage.enterCountry(country);
        if (country.equals("United States")){
            addresspage.selectUSState(usState);
        }
        addresspage.enterAddress(address, city,state, zip);
        test.log(Status.PASS,"Enter the Billing's information in the page");
        addresspage.clickSaveAddressButton();
        test.log(Status.PASS,"Click on Save Address button");

        // Validation
        basepage.waitUntilElementExists(addresspage.displayMsgAddressChanged());
        Assert.assertEquals(addresspage.displayMsgAddressChanged().isDisplayed(),true,
                "The address is not changed : Test Failed");
        test.log(Status.PASS,"The page show the message 'Address changed successfully'");
    }

    @Test(priority=4, dataProvider = "getProductCategory", dataProviderClass = OrderProvider.class)
    @Parameters({"category"})
    public void FilterProductsByCategory(OrderData _OrderData) throws InterruptedException {
        test = extent.createTest("Filter by category", "Filter the books by category");
        OrderPage orderpage= new OrderPage(baseURL,driver);
        BasePage basepage= new BasePage(baseURL,driver);

        /* Variables */
        String category = _OrderData.getCategory();

        orderpage.enterOrdersMenu();
        test.log(Status.PASS,"Enter to Orders menu");
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("my-account/orders/"),
                "The URL is not correct : Test Failed");

        orderpage.clickGoShopButton();
        test.log(Status.PASS,"Click in Go Shop button");
        orderpage.ChooseCategory(category.toLowerCase());
        test.log(Status.PASS,"Choose filter by category: " + category.toUpperCase());

        // Validation
        basepage.waitUntilURL(category);
        Assert.assertTrue(orderpage.validateProductCategory(category),
                "The filter of category is not correct : Test Failed");
        test.log(Status.PASS,"The page show the books by correct category");
    }

    @Test(priority=5)
    public void LogOut() throws InterruptedException {
        test = extent.createTest("Logout account", "Logout the account of the page");
        LoginPage loginpage= new LoginPage(baseURL,driver);

        loginpage.enterMyAccountOption();
        loginpage.waitUntilElementExists(loginpage.displayAccountContent());
        Assert.assertEquals(loginpage.displayAccountContent().isDisplayed(),true);
        test.log(Status.PASS,"The page show the dashboard page");

        loginpage.clickLogOutButton();
        test.log(Status.PASS,"Click on Logout link");
        loginpage.waitUntilElementExists(loginpage.mainPageLogo());
        Assert.assertEquals(loginpage.mainPageLogo().isDisplayed(),true,
                "Error in logout process : Test Failed");
        test.log(Status.PASS,"Session ended correctly");
    }

    @Test(priority=6, dataProvider = "getLoginCredentials", dataProviderClass = LoginProvider.class)
    @Parameters({"invalidUsername, invalidPassword"})
    public void EnterIncorrectLogin(LoginData _loginData) throws InterruptedException {
        test = extent.createTest("Invalid Login", "Log-in with invalid username and password.");
        LoginPage loginpage= new LoginPage(baseURL,driver);

        loginpage.enterCredentials(_loginData.getInvalidUsername(), _loginData.getInvalidPass());
        test.log(Status.PASS,"Enter the invalid credentials in the page");
        loginpage.clickLoginButton();
        test.log(Status.PASS,"Click on Login button");

        loginpage.waitUntilElementExists(loginpage.displayFailMessage());
        Assert.assertEquals(loginpage.displayFailMessage().isDisplayed(), true,
                "The login not validate credentials correctly");
        test.log(Status.PASS,"The page show the fail message");
    }
}
