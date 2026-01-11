package utils;

import base.BaseClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;

public class ScreenshotUtil {

    public static String capture(String testName) {
        try {
            File src = ((TakesScreenshot) BaseClass.getDriver())
                    .getScreenshotAs(OutputType.FILE);

            String path = "screenshots/" + testName + ".png";
            File dest = new File(path);
            dest.getParentFile().mkdirs();

            Files.copy(src.toPath(), dest.toPath());
            return path;

        } catch (Exception e) {
            return null;
        }
    }
}
