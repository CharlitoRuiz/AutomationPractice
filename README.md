# Test Automation with Java
Web test automation example project using IntelliJ IDEA Community, Java, Maven, TestNG, Selenium and Page Object Model (POM)

# Test Subject
https://practice.automationtesting.in/my-account/

# Libraries and Frameworks
Version for some of these can be found in the POM file.

- Selenium - Web automation
- Maven - Build and package management
- TestNG - Test execution and Reporting
- ExtentReports - Generate tests reports
- Gson - Java library that can be used to convert Java Objects into their JSON representation

Steps for execute the tests:

- Open project with IntelliJ
- The tests are in src/test/java/DemoTest folder
- Right click in DemoTest java class and click on "Run DemoTest"
- When the tests finished the folder 'test-output' is created, the report is generate in .html, open it with any browser.  

# Optional
You can run the test in 'headless' mode, just by uncommenting the next line in the baseTest class
```
//ops.addArguments("--headless");
```
