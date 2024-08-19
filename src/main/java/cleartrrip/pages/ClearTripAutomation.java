package cleartrrip.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import org.openqa.selenium.chrome.ChromeOptions;

public class ClearTripAutomation {

    public static WebDriver driver;

    public static void main(String[] args) {


        // Setup WebDriver
        System.setProperty("webdriver.chrome.driver", "/Users/hbrao/GitHub/Selenium/chromedriver-mac-x64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

        driver = new ChromeDriver(options);

        try {

            // Step 1: Launch the URL
            driver.get("https://www.cleartrip.com");

            // Cast WebDriver to JavascriptExecutor
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Use JavaScript to find the SVG element
            WebElement svgElement = (WebElement) js.executeScript(
                    "return document.querySelector('div.d-flex svg path');" // Adjust the selector based on your SVG structure
            );

            if (svgElement != null) {
                System.out.println("Element found!");
                svgElement.click(); // Example action: click on the SVG element

            } else {
                System.out.println("Element not found.");
            }

            driver.manage().window().maximize();

            ClearTripHome clearTripHome = new ClearTripHome();
            clearTripHome.selectDropdownByValue("Round trip");
            clearTripHome.selectFromAndTO("New Delhi", "Mumbai");

            clearTripHome.selectPersonType(1,1,0);
            clearTripHome.selectDate("15","23");

            clearTripHome.searchFlightsButton.click();
            Thread.sleep(4000);
            clearTripHome.getSecondHighestPrice();
            clearTripHome.bookNowButton.click();
            Thread.sleep(4000);
            clearTripHome.getFlightDetails();

            // Step 6: Search for the available flights


            // Wait for results to load (use explicit wait for better handling)
            Thread.sleep(5000);




        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
