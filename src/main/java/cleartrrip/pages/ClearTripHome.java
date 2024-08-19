package cleartrrip.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class ClearTripHome extends ClearTripAutomation {

    public ClearTripHome() {
        super();
        PageFactory.initElements(driver,this);


    }

    @FindBy(xpath="//input[@placeholder='Where from?']")
    WebElement whereFrom;

    @FindBy(xpath="//input[@placeholder='Where to?']")
    WebElement whereTo;

    @FindBy(xpath="//span[text()='One way']//parent::div")
    WebElement tripDropdown;

    @FindBy(xpath="//div[@class='flex flex-middle p-relative homeCalender']/button")
    WebElement calendar;

    @FindBy(xpath="//span[contains(text(), 'Adult')]")
    WebElement typePerson;

    @FindBy(xpath="//span[text()='Search flights']")
    WebElement searchFlightsButton;

    @FindBy(xpath="//div[@class='sticky__parent']//span[text()='Book now']")
    WebElement bookNowButton;






    public void selectDropdownByValue(String tripValue) throws InterruptedException {
        driver.findElement(By.xpath("//span[text()='One way']//parent::div")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//ul/li//span[text()='"+tripValue+"']")).click();
    }

    public void selectFromAndTO(String from, String to) throws InterruptedException {
        whereFrom.click();
        whereFrom.sendKeys(from);
        Thread.sleep(4000);
        driver.findElement(By.xpath("//ul[@class='airportList']/li[1]")).click();

       // whereFrom.sendKeys(Keys.ARROW_DOWN +""+ Keys.ENTER);
       /*Actions act = new Actions(driver);
        act.sendKeys(Keys.DOWN).perform();
        act.sendKeys(Keys.ENTER).perform();*/

        whereTo.click();
        whereTo.sendKeys(to);
        Thread.sleep(4000);
        driver.findElement(By.xpath("//ul[@class='airportList']/li[1]")).click();
        //whereFrom.sendKeys(Keys.ARROW_DOWN +""+ Keys.ENTER);

        /*act.sendKeys(Keys.DOWN).perform();
        act.sendKeys(Keys.ENTER).perform();*/
    }

    public void selectDate(String fromDate, String toDate) throws InterruptedException {
        calendar.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='DayPicker-Body']//div[text()='"+fromDate+"']")).click();
        driver.findElement(By.xpath("//div[@class='DayPicker-Body']//div[text()='"+toDate+"']")).click();
    }

    public void selectPersonType(int adultCount, int childrenCount, int seniorCitizen) {
        typePerson.click();

        if(adultCount>1) {
            for(int i=1;i<=adultCount;i++)
            driver.findElement(
                            By.xpath("//span[text()='Adults']/parent::div/following-sibling::ul/li[3]"))
                    .click();
        }

        if(childrenCount>0) {
            for(int i=0;i<childrenCount;i++)
                driver.findElement(
                                By.xpath("//span[text()='Adults']/parent::div/following-sibling::ul/li[3]"))
                        .click();
        }

        if(seniorCitizen>0) {
            for(int i=1;i<=seniorCitizen;i++)
                driver.findElement(
                                By.xpath("//span[text()='Adults']/parent::div/following-sibling::ul/li[3]"))
                        .click();
        }
    }

    public void getSecondHighestPrice(){
        WebElement secondHighOnwardPrice = driver.findElement(By.xpath("//div[@class='sticky__parent'][2]//div[@class='col'][1]/div/div[3]/a"));
        secondHighOnwardPrice.click();
        driver.findElement(By.xpath("//div[@data-test-attrib='onward-view']/div/div[2]")).click();
        WebElement secondHighReturnPrice = driver.findElement(By.xpath("//div[@class='sticky__parent'][2]//div[@class='col'][2]/div/div[3]/a"));
secondHighReturnPrice.click();
        driver.findElement(By.xpath("//div[@data-test-attrib='return-view']/div/div[2]")).click();
        bookNowButton.click();
    }

    public void getFlightDetails(){
        String locator = "//div[@class='itinerary--details-grid details-grid__container']";
        List<WebElement> iternaryList = driver.findElements(By.xpath(locator));
        for(int i=0;i<iternaryList.size();i++){
            System.out.print("Flight details "+driver.findElement(By.xpath(locator+"["+i+"]")).getText());

        }
    }




}
