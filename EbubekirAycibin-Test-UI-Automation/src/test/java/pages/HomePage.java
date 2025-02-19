package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private static class MenuSelectors {
        private static final By menuItems = By.id("navbarDropdownMenuLink");
        private static final By careersLink = By.linkText("Careers");
    }

    private static class UrlSelectors {
        private static final String insiderUrl = "https://useinsider.com/";
    }

    public void goToHomePage() {
        driver.get(UrlSelectors.insiderUrl);
    }

    public void navigateToCareersPage()  {
        List<WebElement> companyMenu = driver.findElements(MenuSelectors.menuItems);
        companyMenu.get(4).click(); // Navigate to the company menu
        driver.findElement(MenuSelectors.careersLink).click(); // Click on "Careers"
    }

    public void checkUrl() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, UrlSelectors.insiderUrl, "URL doğru değil, mevcut URL: " + currentUrl);
    }
}
