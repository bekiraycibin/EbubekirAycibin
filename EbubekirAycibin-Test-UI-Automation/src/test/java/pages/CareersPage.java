package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class CareersPage {

    private final WebDriver driver;

    private static final String EXPECTED_URL = "https://useinsider.com/careers";

    public CareersPage(WebDriver driver) {
        this.driver = driver;
    }

    private By seeAllTeamsButton() {
        return By.xpath("//a[@class='btn btn-outline-secondary rounded text-medium mt-5 mx-auto py-3 loadmore']");
    }

    private By locationsTitle() {
        return By.xpath("//h3[@class='category-title-media ml-0']");
    }

    private By lifeAtInsiderTitle() {
        return By.xpath("//h2[@class='elementor-heading-title elementor-size-default']");
    }

    public void verifyUrl() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotNull(currentUrl, "URL is null!");
        Assert.assertTrue(currentUrl.contains(EXPECTED_URL), "URL is incorrect, current URL: " + currentUrl);
    }

    public void checkAllTeamsButton() {
        WebElement buttonElement = driver.findElement(seeAllTeamsButton());
        String actualButtonText = buttonElement.getText();
        String expectedButtonText = "See all teams";
        Assert.assertEquals(actualButtonText, expectedButtonText, "Button text is incorrect! Current text: " + actualButtonText);
    }

    public void checkLocationsBlock() {
        WebElement titleElement = driver.findElement(locationsTitle());
        String actualTitle = titleElement.getText();
        String expectedTitle = "Our Locations";
        Assert.assertEquals(actualTitle, expectedTitle, "Title is incorrect! Current title: " + actualTitle);
    }

    public void checkLifeAtInsiderTitle() {
        List<WebElement> titleElements = driver.findElements(lifeAtInsiderTitle());
        boolean isTitleFound = false;

        for (WebElement titleElement : titleElements) {
            String actualTitle = titleElement.getText();
            String expectedTitle = "Life at Insider";

            if (actualTitle.contains(expectedTitle)) {
                isTitleFound = true;
                break;
            }
        }

        Assert.assertTrue(isTitleFound, "'Life at Insider' text not found in any title.");
    }
}
