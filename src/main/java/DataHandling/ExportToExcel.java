package DataHandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportToExcel {
	static boolean status=true;
	public static void ExcelWriter(int row, int column, String value) {
		XSSFWorkbook wb = null;
		XSSFSheet sh = null;
		File file = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		file = new File(System.getProperty("user.dir") + "\\output.xlsx");

		try {
			fis = new FileInputStream(file);
			wb = new XSSFWorkbook(fis);

			if (wb.getSheetIndex("WebTableData") == -1) {

				if (row == 0 && column == 0) {
					sh = wb.createSheet("WebTableData");
				} else {
					sh = wb.getSheet("WebTableData");
				}

				if (column == 0) {
					sh.createRow(row).createCell(column).setCellValue(value);
				} else {
					sh.getRow(row).createCell(column).setCellValue(value);
				}

				fos = new FileOutputStream(file);
				wb.write(fos);

			} else {
				sh = wb.getSheet("WebTableData");
				if (column == 0) {
					sh.createRow(row).createCell(column).setCellValue(value);
				} else {
					sh.getRow(row).createCell(column).setCellValue(value);
				}

				fos = new FileOutputStream(file);
				wb.write(fos);
			}
		}

		catch (Exception e) {
			status=false;
			e.printStackTrace();
		}
	}
}

/*
 * sh=wb.createSheet(); sh=wb.createSheet("nia");
 * sh=wb.getSheet("nia");sh=wb.getSheetAt(0);
 */