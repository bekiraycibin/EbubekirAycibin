package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class JobListingsPage {
    private final WebDriver driver;

    public JobListingsPage(WebDriver driver) {
        this.driver = driver;
    }

    private By locationDropdownArrow() {
        return By.className("select2-selection__arrow");
    }

    private By istanbulOption() {
        return By.xpath("//li[contains(@class, 'select2-results__option') and contains(text(), 'Istanbul, Turkiye')]");
    }

    private By jobsList() {
        return By.id("jobs-list");
    }

    private By departmentElements() {
        return By.xpath("//span[contains(@class, 'position-department')]");
    }

    private By locationElements() {
        return By.xpath("//div[contains(@class, 'position-location')]");
    }

    private By careersLink() {
        return By.xpath("//a[@href='https://useinsider.com/careers/open-positions/?department=qualityassurance']");
    }

    private By filterByDepartmentContainer() {
        return By.id("select2-filter-by-department-container");
    }

    public void goToQualityAssurancePage() {
        driver.get("https://useinsider.com/careers/quality-assurance/");
    }

    public void clickSeeAllQAJobs() {
        driver.findElement(careersLink()).click();
    }

    public void selectLocation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeToBe(
                filterByDepartmentContainer(), "title", "Quality Assurance"
        ));

        driver.findElement(locationDropdownArrow()).click();
        driver.findElement(istanbulOption()).click();
    }

    public void isJobListDisplayed() {
        WebElement jobsListElement = driver.findElement(jobsList());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", jobsListElement);
        jobsListElement.findElements(By.xpath("./*"));
    }

    public void areJobDetailsCorrect() {
        List<WebElement> departments = driver.findElements(departmentElements());
        List<WebElement> locations = driver.findElements(locationElements());

        boolean departmentsCorrect = departments.stream().allMatch(e -> e.getText().trim().equals("Quality Assurance"));
        boolean locationsCorrect = locations.stream().allMatch(e -> e.getText().trim().equals("Istanbul, Turkiye"));

        if (departmentsCorrect && locationsCorrect) {
            System.out.println("All job details are correct.");
        } else {
            System.out.println("Job details are incorrect.");
        }
    }
}
