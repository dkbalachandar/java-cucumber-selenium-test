package yelp.test.definitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class YelpStepDefinition {

    protected WebDriver driver;

    @Before
    public void setup() {
        driver = new FirefoxDriver();
        //Loading the driver
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
    }

    @Given("^a user goes to Yelp$")
    public void goToYelp() {
        //Get the Yelp home page
        driver.get("https://www.yelp.com/dallas");
    }

    @When("^Search for (.*?)$")
    public void doSearch(String searchTerm) throws InterruptedException {
        WebElement searchDescElement = driver.findElement(By.id("find_desc"));
        searchDescElement.sendKeys(searchTerm);

        WebElement submit = driver.findElement(By.id("header-search-submit"));
        submit.submit();
    }

    @Then("^See the List of (.*?) Restaurants$")
    public void verifyContents(String restaurant) {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertEquals(true, isContentAvailable(restaurant));
    }

    @Then("^See the List of (.*?) Restaurants and confirm results contains (.*?)$")
    public void verifyContentsWithLandmark(String restaurant, String landMark) {
        //Create an Explicit wait to make sure that the page is loaded and driver updates the page data.
        WebDriverWait myWait = new WebDriverWait(driver, 10);
        myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("yelp_main_body")));
        assertEquals(true, isContentAvailable(restaurant, landMark));
    }

    public boolean isContentAvailable(String... contents) {
        WebElement searchResultsElement = driver.findElement(By.id("yelp_main_body"));
        boolean result = false;
        for (String content : contents) {
            result = searchResultsElement.getText().contains(content);
        }
        return result;
    }

    @Then("^See No Results found error message")
    public void verifyContents() {
        //Create an Explicit wait to make sure that the page is loaded and driver updates the page data.
        WebDriverWait myWait = new WebDriverWait(driver, 10);
        myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("yelp_main_body")));
        assertEquals(true, driver.getPageSource().contains("No Results"));
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

}
