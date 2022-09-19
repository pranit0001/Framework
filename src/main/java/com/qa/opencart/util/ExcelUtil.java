package com.qa.opencart.util;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

//public class ExcelUtil {
//
//	public static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/OpenCartTestData.xlsx";
//	private static Workbook book;
//	private static Sheet sheet;
//
//	public static Object[][] getTestData(String sheetName) {
//	 Object data [][]= null;
//		try {
//			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
//			book = WorkbookFactory.create(ip);
//			book.getSheet(sheetName);
//			data= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
//			// it will give last row no ie. in our sheet is 4 and then it will give column
//			// no.by going to 0th row and last cellnum.
//			// ultimately it gives data[row no.] [column no]
//
//			// storing the data in the Object Array
//			for (int i = 0; i < sheet.getLastRowNum(); i++) {
//				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
//					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
//				}
////			for (int i = 0; i < sheet.getLastRowNum(); i++) {
////				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
////					// for the sheet its start from 1 but here in the code in java its start from 0;
////					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
////					// we wrote for row i+1 because first row of the firstname lastname and all is
////					// useless , and we write < not <= cause for rows index start from o and first row is useless
////					//for column index is start from 0.
////				}
//			}
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (InvalidFormatException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return data ;
//		
//	}

public class ExcelUtil {

	private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/OpenCartTestData.xlsx";
	private static Workbook book;
	private static Sheet sheet;

	public static Object[][] getTestData(String sheetName) {

		Object data[][] = null;

		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);

			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}
}
