package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OrderPage extends BasePage {

    public OrderPage(String url, WebDriver driver)
    {
        super(url,driver);
    }

    // Locators
    private By lnkOrders = By.cssSelector("a[href='https://practice.automationtesting.in/my-account/orders/']");
    private By btnGoShop= By.cssSelector(".button.woocommerce-Button");


    /* ===== Set Locators ===== */
    public WebElement set_lnkOrders() { return driver.findElement(this.lnkOrders);}
    public WebElement set_btnGoShop() { return driver.findElement(this.btnGoShop);}
    public WebElement set_lnkCategory(String category) { return driver.findElement(By.cssSelector("[href='https://practice.automationtesting.in/product-category/"+category+"/']"));}


    // ====================== Methods ======================= //
    public void enterOrdersMenu() {
        set_lnkOrders().click();
    }
    public void clickGoShopButton() {
        set_btnGoShop().click();
    }
    public void ChooseCategory(String category) {
        set_lnkCategory(category).click();
    }
    public Boolean validateProductCategory(String category)
    {
        Boolean isCategory = false;
        List<WebElement> Form = driver.findElements(By.cssSelector("h3"));
        for(int i=0;i<Form.size();i++)
        {
            WebElement element = Form.get(i);
            if (category.toLowerCase().equals("javascript")){
                if (element.getText().toLowerCase().contains(category) || element.getText().toLowerCase().contains("js")){
                    isCategory = true;
                }
            }
            else if(element.getText().toLowerCase().contains(category.toLowerCase())){
                isCategory = true;
            }
        }
        return isCategory;
    }
}
