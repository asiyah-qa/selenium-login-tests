package org.asiyahqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest {

    // URL of the login page
    static final String URL = "https://o2.openmrs.org/openmrs/referenceapplication/login.page";
    // WebDriver instance
    static WebDriver driver = new SafariDriver();

    // Main method to execute test cases
    public static void main(String[] args) {
    // updated to trigger GitHub refresh

        // Set default wait time for elements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Open browser in full-screen mode
        driver.manage().window().maximize();

        // Run Test Case 1 to Test Case 7
        testLoginValid();
        testLoginWithoutLocation();
        testLoginWithWrongUsername();
        testLoginWithWrongPassword();
        testLoginEmptyUsernameAndPassword();
        testLoginWithoutLocationAndPassword();
        testLoginEmptyPassword();

        // Close the browser after tests
        driver.quit();
    }

    // Test Case 1: Successful Login with valid Username, Password, and Location
    public static void testLoginValid() {
        try {
            driver.get(URL);
            driver.findElement(By.id("username")).sendKeys("admin");
            driver.findElement(By.id("password")).sendKeys("Admin123");

            // Wait and click on the location dropdown
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement locationDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("Inpatient Ward")));
            locationDropdown.click();

            // Wait and click on the Login button
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='loginButton']")));
            loginButton.click();
            System.out.println("Test Case 1: Login Success - Passed");
        } catch (Exception e) {
            System.out.println("Test Case 1: Login Success - Failed");
            e.printStackTrace();
        }
    }

    // Test Case 2: Login Without Selecting a Location
    public static void testLoginWithoutLocation() {
        try {
            driver.get(URL);
            driver.findElement(By.id("username")).sendKeys("admin");
            driver.findElement(By.id("password")).sendKeys("Admin123");

            // Wait for the login button to be clickable and click it
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='loginButton']")));
            loginButton.click();  // Attempt to log in without selecting a location

            // Wait for the error message related to location
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='sessionLocationError' and contains(text(), 'You must choose a location!')]")));
            System.out.println("Test Case 2: Login Without Location - Passed");
        } catch (Exception e) {
            System.out.println("Test Case 2: Login Without Location - Failed");
            e.printStackTrace();
        }
    }

    // Test Case 3: Login with Incorrect Username
    public static void testLoginWithWrongUsername() {
        try {
            driver.get(URL);
            driver.findElement(By.id("username")).sendKeys("wrongUser");
            driver.findElement(By.id("password")).sendKeys("Admin123");

            // Select location and click Login button
            WebElement locationDropdown = driver.findElement(By.id("Inpatient Ward"));
            locationDropdown.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='loginButton']")));
            loginButton.click();

            // Wait for the error message to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Invalid username/password. Please try again.')]")));
            System.out.println("Test Case 3: Login with Incorrect Username - Passed");
        } catch (Exception e) {
            System.out.println("Test Case 3: Login with Incorrect Username - Failed");
            e.printStackTrace();
        }
    }

    // Test Case 4: Login with Incorrect Password
    public static void testLoginWithWrongPassword() {
        try {
            driver.get(URL);
            driver.findElement(By.id("username")).sendKeys("admin");
            driver.findElement(By.id("password")).sendKeys("wrongPassword");

            // Select location and click Login button
            WebElement locationDropdown = driver.findElement(By.id("Inpatient Ward"));
            locationDropdown.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='loginButton']")));
            loginButton.click();

            // Wait for the error message to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Invalid username/password. Please try again.')]")));
            System.out.println("Test Case 4: Login with Incorrect Password - Passed");
        } catch (Exception e) {
            System.out.println("Test Case 4: Login with Incorrect Password - Failed");
            e.printStackTrace();
        }
    }

    // Test Case 5: Login with Empty Username and Password
    public static void testLoginEmptyUsernameAndPassword() {
        try {
            driver.get(URL);
            driver.findElement(By.id("username")).sendKeys("");
            driver.findElement(By.id("password")).sendKeys("");

            // Select location and click Login button
            WebElement locationDropdown = driver.findElement(By.id("Inpatient Ward"));
            locationDropdown.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='loginButton']")));
            loginButton.click();

            // Wait for the error message
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Invalid username/password. Please try again.')]")));
            System.out.println("Test Case 5: Login with Empty Username and Password - Passed");
        } catch (Exception e) {
            System.out.println("Test Case 5: Login with Empty Username and Password - Failed");
            e.printStackTrace();
        }
    }

    // Test Case 6: Login Without Location and Password
    public static void testLoginWithoutLocationAndPassword() {
        try {
            driver.get(URL);
            driver.findElement(By.id("username")).sendKeys("admin");
            driver.findElement(By.id("password")).sendKeys("");  // Empty password

            // Do not select location, click Login button
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased wait time
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='loginButton']")));
            loginButton.click();

            // Wait for the error message (increased wait time and corrected XPath if necessary)
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='sessionLocationError' and contains(text(), 'You must choose a location!')]")));

            System.out.println("Test Case 6: Login Without Location and Password - Passed");
        } catch (Exception e) {
            System.out.println("Test Case 6: Login Without Location and Password - Failed");
            e.printStackTrace();
        }
    }

    // Test Case 7: Login with Empty Password
    public static void testLoginEmptyPassword() {
        try {
            driver.get(URL);
            driver.findElement(By.id("username")).sendKeys("admin");
            driver.findElement(By.id("password")).sendKeys("");

            // Select location and click Login button
            WebElement locationDropdown = driver.findElement(By.id("Inpatient Ward"));
            locationDropdown.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='loginButton']")));
            loginButton.click();

            // Wait for error message
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Invalid username/password. Please try again.')]")));
            System.out.println("Test Case 7: Login with Empty Password - Passed");
        } catch (Exception e) {
            System.out.println("Test Case 7: Login with Empty Password - Failed");
            e.printStackTrace();
        }
    }

}