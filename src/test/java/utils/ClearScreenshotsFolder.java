package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClearScreenshotsFolder {
	
	public static void cleanScreenshotFolder() {

	    Path screenshotDir = Paths.get(System.getProperty("user.dir"), "screenshots");

	    try {
	        if (Files.exists(screenshotDir)) {
	            Files.walk(screenshotDir)
	                 .filter(Files::isRegularFile)
	                 .forEach(path -> {
	                     try {
	                         Files.delete(path);
	                     } catch (IOException ignored) {}
	                 });
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


}
