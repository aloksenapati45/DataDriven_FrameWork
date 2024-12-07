package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	XSSFWorkbook wk;

	// get file using constructor
	public ExcelFileUtil(String filePath) throws Exception {
		FileInputStream io = new FileInputStream(filePath);
		wk = new XSSFWorkbook(io);
	}

	// get all row in a sheet
	public int getRow(String sheetName) {
		return wk.getSheet(sheetName).getLastRowNum();
	}

	// methods for reading data in cell
	public String getCellData(String sheetName, int row, int cell) {
		String data = "";
		if (wk.getSheet(sheetName).getRow(row).getCell(cell).getCellType() == CellType.NUMERIC) {
			int cellData = (int) wk.getSheet(sheetName).getRow(row).getCell(cell).getNumericCellValue();
			data = String.valueOf(cellData);
		} else {
			data = wk.getSheet(sheetName).getRow(row).getCell(cell).getStringCellValue();
		}
		return data;
	}

	// methods for input data in cell
	public void setCellData(String sheetName, int row, int column, String status, String writeexcel) throws Exception {
		XSSFSheet ws = wk.getSheet(sheetName);
		XSSFRow rownum = ws.getRow(row);
		XSSFCell cell = rownum.getCell(column);
		cell.setCellValue(status);
		if (status.equalsIgnoreCase("Pass")) {
			XSSFCellStyle style = wk.createCellStyle();
			XSSFFont font = wk.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		} else if (status.equalsIgnoreCase("FAIL")) {
			XSSFCellStyle style = wk.createCellStyle();
			XSSFFont font = wk.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		} else if (status.equalsIgnoreCase("Blocked")) {
			XSSFCellStyle style = wk.createCellStyle();
			XSSFFont font = wk.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		FileOutputStream fo = new FileOutputStream(writeexcel);
		wk.write(fo);
	}
}
