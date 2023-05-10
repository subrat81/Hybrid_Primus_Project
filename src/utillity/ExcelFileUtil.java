package utillity;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
Workbook wb;
public ExcelFileUtil(String excelpath) throws Throwable
{
	FileInputStream fi=new FileInputStream(excelpath);
	wb=WorkbookFactory.create(fi);
}
public int rowCount(String sheetname)
{
	return wb.getSheet(sheetname).getLastRowNum();
}
public int cellCount(String sheetname)
{
	return wb.getSheet(sheetname).getRow(0).getLastCellNum();
}
public String getCelldata(String sheetname,int row,int col)
{
	String data="";
	if(wb.getSheet(sheetname).getRow(row).getCell(col).getCellType()==Cell.CELL_TYPE_NUMERIC)
	{
		int celldata=(int) wb.getSheet(sheetname).getRow(row).getCell(col).getNumericCellValue();
		data=String.valueOf(celldata);
	}
	else
	{
		data=wb.getSheet(sheetname).getRow(row).getCell(col).getStringCellValue();
	}
	return data;
}
public void setCelldata(String sheetname,int row,int col,String status,String writeexcel) throws Throwable
{
	Sheet ws=wb.getSheet(sheetname);
	Row rw=ws.getRow(row);
	Cell cell=rw.createCell(col);
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("Pass"))
	{
		CellStyle style=wb.createCellStyle();
		Font font=wb.createFont();
		font.setColor(IndexedColors.GREEN.getIndex());
		font.setBold(true);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rw.getCell(col).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("Fail"))
	{
		CellStyle style=wb.createCellStyle();
		Font font=wb.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		font.setBoldweight(font.getBoldweight());
		style.setFont(font);
		rw.getCell(col).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("Blocked"))
	{
		CellStyle style=wb.createCellStyle();
		Font font=wb.createFont();
		font.setColor(IndexedColors.BLUE.getIndex());
		font.setBold(true);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rw.getCell(col).setCellStyle(style);
	}
	FileOutputStream fo=new FileOutputStream(writeexcel);
	wb.write(fo);
	
	
}
}
