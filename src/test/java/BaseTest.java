import com.aventstack.extentreports.markuputils.Markup;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    public String baseURL = "https://practice.automationtesting.in/my-account/";
    ExtentHtmlReporter htmlReporter;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass(alwaysRun = true)
    @Parameters({"browser"})
    public void setUpTests(@Optional("chrome") String browser) {

        if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            ChromeOptions ops = new ChromeOptions();
            ops.addArguments("disable-infobars");
            driver = new ChromeDriver(ops);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseURL);
    }

    @BeforeTest
    public void startReport() {
        // initialize the HtmlReporter
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testReport.html");

        //initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        //configuration items to change the look and feel
        htmlReporter.config().setDocumentTitle("Test - practice.automationtesting.in");
        htmlReporter.config().setReportName("Test Demo Report");
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        try {
            driver.close();
            driver.quit();
            System.out.println("========= Tests execution finished =========");
        } catch (WebDriverException ex) {
            System.out.println(ex);
        }
        extent.flush();
    }
    @AfterMethod
    public void getTestsResults(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL,result.getThrowable());
            String photo = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
            test.log(Status.FAIL, (Markup) test.addScreenCaptureFromBase64String(photo));
        }
    }
}
