package qadai.auto.apitest.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;

import qadai.auto.apitest.log.LogUtils;


public class ExcelUtils {
	private static Logger log = LogUtils.className(ExcelUtils.class);
	@SuppressWarnings("ignore")
	public ExcelUtils() {
		// TODO Auto-generated constructor stub
	}
	@SuppressWarnings("deprecation")
	public String[][] readExcel(String tcPath, String sheetName){
	        File file = new File(tcPath);
	        InputStream inputStream = null;
	        Workbook workbook = null;
	        String[][] cases = null;
	        try {
	            inputStream = new FileInputStream(file);
	            workbook = WorkbookFactory.create(inputStream);
	            inputStream.close();
	            //工作表对象
	            Sheet sheet = workbook.getSheet(sheetName);
	            //总行数
	            int rowLength = sheet.getLastRowNum()+1;
	            //工作表的列
	            Row row = sheet.getRow(0);
	            //总列数
	            int colLength = row.getLastCellNum();
	            //得到指定的单元格
	            Cell cell = row.getCell(0);
	            log.info("行数：" + rowLength + ",列数：" + colLength);
	            
	            cases = new String[rowLength-1][colLength];
	            
	            int x =0;
	            for (int i = 1; i < rowLength; i++) {
	            	row = sheet.getRow(i);
//	            	if (row.getCell(GlobalVars.NEEDRUNColumn).getStringCellValue().equals("Y")){	            		                
		                for (int j = 0; j < colLength; j++) {
		                    cell = row.getCell(j);	                    
	                    	//Excel数据Cell有不同的类型，当我们试图从一个数字类型的Cell读取出一个字符串时就有可能报异常
		                    //将所有的需要读的Cell表格设置为String格式
			                    if (cell != null){
			                        cell.setCellType(CellType.STRING);
			                    	cases[x][j] = cell.getStringCellValue();
			                    }
			                    else {
			                    	cases[x][j] = "null值";
								}
		                    }  
		                x++;
	                }
//			     }
	        	} catch (Exception e) {
	        		e.printStackTrace();
	        }
			 return cases;
	    }

}
