package dataprovider;

import java.io.InputStream;
import java.util.List;

import org.testng.annotations.DataProvider;

import config.ConfigReader;
import utils.ExcelReader;

public class DataProviderUtil {
	
	private static final String FILE_PATH = System.getProperty("user.dir")+"/src/test/resources/testdata/TestData.xlsx";
	private static final String RESOURCE_PATH = "testdata/TestData.xlsx";

		
		@DataProvider(name = "validLoginData")
		public static Object[][] validLoginData() {
			return getSheetData("validLoginData");
		}
		
		@DataProvider(name = "invalidLoginData")
		public static Object[][] invalidLoginData() {
			return getSheetData("invalidLoginData");
		}
		
		private static Object[][] getSheetData(String sheetName) {
			boolean headless = Boolean.parseBoolean(ConfigReader.getProp("headless"));
			List<String[]> sheetData;
			if(headless) {
				InputStream is = DataProviderUtil.class.getClassLoader().getResourceAsStream(RESOURCE_PATH);
	            if (is == null) {
	                throw new RuntimeException("TestData.xlsx not found in src/test/resources/testdata");
	            }
	            sheetData = ExcelReader.getSheetData(is, sheetName);
			} else {
				sheetData = ExcelReader.getSheetData(FILE_PATH, sheetName);
			}
			Object[][] data = new Object[sheetData.size()][sheetData.get(0).length];
	        for (int i = 0; i < sheetData.size(); i++) {
	            data[i] = sheetData.get(i);
	        }
	        return data;
		}

}
