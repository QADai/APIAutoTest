package qadai.auto.apitest.common.utils;

import java.util.List;

import qadai.auto.apitest.config.GlobalVars;

public class GenerateDataProvider {
	public Object[][] testDataSet(String excelPath, String sheetName, List filterList){
		Object[][] newDataSet;
		
		ExcelUtils excelUtils = new ExcelUtils();
		Object[][]  fullDataSet = excelUtils.readExcel(excelPath, sheetName);
		int fullDataSetRows = fullDataSet.length;
		
		int newDataRows=0;
		int newDataCols = filterList.size();
		//获取NEEDRunColumn是Y的数值
		for(int i = 0 ; i<fullDataSetRows; i++){
			if (fullDataSet[i][GlobalVars.NEEDRUNColumn].equals("Y")){	
				newDataRows++;
			}
		}
		
		newDataSet = new String[newDataRows][] ;		
		
		int returnRow=0;
		for(int i = 0; i<fullDataSetRows; i++){
			newDataSet[returnRow] = new String[newDataCols];
			if (fullDataSet[i][GlobalVars.NEEDRUNColumn].equals("Y")){				
				for(int j = 0; j<newDataCols; j++){
					if (fullDataSet[i][(int) filterList.get(j)] != null){
						newDataSet[returnRow][j] = fullDataSet[i][(int) filterList.get(j)];
					}					
				}	
				returnRow++;
			}	
		}
		return newDataSet;				
	}

}
