package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Excel {
	
	
	public static HashMap<Integer, ArrayList<String>> getSheetData(String sheetName) throws Exception{
		
		File file = new File("C:\\Data\\Java-Selenium\\HybridFramework\\src\\testData\\Data.xls");
		FileInputStream io = new FileInputStream(file);
		
		HSSFWorkbook workbook = new HSSFWorkbook(io);
		HSSFSheet sheet = workbook.getSheet(sheetName);
		int allRows = sheet.getLastRowNum();
		System.out.println("Total Rows: " + allRows);
		
		HashMap<Integer, ArrayList<String>> sheetData = new HashMap<Integer, ArrayList<String>>();
		
		for(int i=0; i<=allRows; i++) {
			ArrayList<String> rowData = new ArrayList<String>();
			HSSFRow row = sheet.getRow(i);
			short lastCell = row.getLastCellNum();
			for(int j=0; j<lastCell; j++) {
				HSSFCell cell = row.getCell(j);
				//System.out.print(cell.getStringCellValue() + " ");
				rowData.add(cell.getStringCellValue());
			}
			//System.out.println(rowData);
			sheetData.put(i, rowData);
			
		}
		//System.out.println(sheetData);
		io.close();
		return sheetData;
	}
	
	
	

}
