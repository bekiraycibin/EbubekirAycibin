package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtility {

@AfterTest
    public static void takeScreenshot(WebDriver driver, String testName) {
        File screenshotDir = new File("screenshots");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();  // Klasör yoksa oluşturur
        }
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        String destinationPath = "screenshots/" + testName + ".png";
        try {
            FileUtils.copyFile(sourceFile, new File(destinationPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
