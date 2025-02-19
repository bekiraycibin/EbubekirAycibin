package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LeverApplicationPage {
    private final WebDriver driver;

    public LeverApplicationPage(WebDriver driver) {
        this.driver = driver;
    }

    private static class PositionSelectors {
        private static final By positionListItemWrapper = By.xpath("//div[contains(@class,'position-list-item-wrapper')]");
    }

    private static class LocationSelectors {
        private static final By locationFilter = By.id("select2-filter-by-location-container");
    }

    private static class RoleSelectors {
        private static final By viewRoleLinks = By.xpath("//a[contains(text(),'View Role')]");
    }

    private void getPositionListItemWrapper() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(PositionSelectors.positionListItemWrapper));
    }

    private WebElement getLocationFilter() {
        return driver.findElement(LocationSelectors.locationFilter);
    }

    private WebElement getViewRoleLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(RoleSelectors.viewRoleLinks)).get(2);
    }

    public void redirectToLeverApplicationForm() throws InterruptedException {
        Thread.sleep(500);
        getPositionListItemWrapper();
        getLocationFilter().click();
        getViewRoleLink().click();
        Thread.sleep(500);
    }

    public void checkURLToRedirectToLeverApplicationForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String originalWindow = driver.getWindowHandle();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotNull(currentUrl, "URL null geldi");
        Assert.assertTrue(currentUrl.contains("lever.co"), "URL doğru değil, mevcut URL: " + currentUrl);
    }
}
