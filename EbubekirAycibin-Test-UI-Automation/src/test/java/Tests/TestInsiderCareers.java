package Tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.CareersPage;
import pages.HomePage;
import pages.JobListingsPage;
import pages.LeverApplicationPage;
import utilities.WebDriverFactory;
import utilities.ScreenshotUtility;

public class TestInsiderCareers {
    WebDriver driver;
    HomePage homePage;
    CareersPage careersPage;
    JobListingsPage jobListingsPage;
    LeverApplicationPage leverApplicationPage;

    @BeforeMethod
    @Parameters("browser") // Bu parametre TestNG XML dosyasından geliyor
    public void setUp(String browser) {
        driver = WebDriverFactory.getDriver(browser); // Burada 'browser' parametresi kullanılır
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        careersPage = new CareersPage(driver);
        jobListingsPage = new JobListingsPage(driver);
        leverApplicationPage = new LeverApplicationPage(driver);
    }

    @Test
    public void testHomePageIsOpened() {

        homePage.goToHomePage();

        homePage.checkUrl();

    }

    @Test
    public void testCareersPageAndBlocks() throws InterruptedException {

        homePage.goToHomePage();
        homePage.navigateToCareersPage();
        careersPage.verifyUrl();
        careersPage.checkLifeAtInsiderTitle();
        careersPage.checkLocationsBlock();
        careersPage.checkAllTeamsButton();

    }

    @Test
    public void testJobListings()  {
            jobListingsPage.goToQualityAssurancePage();
        jobListingsPage.clickSeeAllQAJobs();
        jobListingsPage.selectLocation();
            jobListingsPage.isJobListDisplayed();
            jobListingsPage.areJobDetailsCorrect();

    }

    @Test
    public void testRedirectToLeverApplication() throws InterruptedException {
        jobListingsPage.goToQualityAssurancePage();
        jobListingsPage.clickSeeAllQAJobs();
        jobListingsPage.selectLocation();
            jobListingsPage.isJobListDisplayed();
            leverApplicationPage.redirectToLeverApplicationForm();
            leverApplicationPage.checkURLToRedirectToLeverApplicationForm();

    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {

            if (ITestResult.FAILURE == result.getStatus()) {

                ScreenshotUtility.takeScreenshot(driver, result.getMethod().getMethodName());
            }
            driver.quit();
        }
    }
}