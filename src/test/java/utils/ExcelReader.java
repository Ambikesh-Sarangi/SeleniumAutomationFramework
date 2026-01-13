package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

	public static List<String[]> getSheetData(String filePath, String sheetName) {
		List<String[]> data = new ArrayList<>();
		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(fis)) {
			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Worksheet " + sheetName + " does not exists");
			}

			for (Row row : sheet) {
				if (row.getRowNum() == 0) {
					continue;
				}
				List<String> rowData = new ArrayList<>();
				for (Cell cell : row) {
					rowData.add(getCellValue(cell));
				}
				data.add(rowData.toArray(new String[0]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	private static String getCellValue(Cell cell) {

		if (cell == null) {
			return "";
		}

		switch (cell.getCellType()) {

		case STRING:
			return cell.getStringCellValue().trim();

		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return new DataFormatter().formatCellValue(cell);
			}
			return new DataFormatter().formatCellValue(cell);

		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());

		case BLANK:
			return "";

		default:
			return "";
		}
	}

	
	public static List<String[]> getSheetData(InputStream is, String sheetName) {
		return readSheet(is, sheetName);
	}

	
	private static List<String[]> readSheet(InputStream is, String sheetName) {
		List<String[]> data = new ArrayList<>();
		try (Workbook workbook = new XSSFWorkbook(is)) {
			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Worksheet " + sheetName + " does not exist");
			}
			for (Row row : sheet) {
				
				if (row.getRowNum() == 0) {
					continue;
				}
				List<String> rowData = new ArrayList<>();
				for (Cell cell : row) {
					rowData.add(getCellValue(cell));
				}
				data.add(rowData.toArray(new String[0]));
			}
		} catch (Exception e) {
			throw new RuntimeException("Error reading sheet: " + sheetName, e);
		}
		return data;
	}
}
