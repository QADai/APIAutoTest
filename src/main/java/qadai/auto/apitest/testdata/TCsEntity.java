package qadai.auto.apitest.testdata;

import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.archivers.zip.ScatterStatistics;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.model.Log;

import qadai.auto.apitest.common.utils.ExcelUtils;
import qadai.auto.apitest.common.utils.GenerateDataProvider;
import qadai.auto.apitest.config.GlobalVars;

public class TCsEntity {
	private static int expectLength =0;
	
	public int CaseNo = 0;
	public int Description = 1;
	public int Prority = 2;
	public int NeedRun = 3 ;
	public int Headers = 4;
	public int URI = 5;
	public int PathVar = 6;
	public int Name = 7;
	public int Sex = 8;
	public int ExpectedStatusCode = 9;
	public int ExcpectKey = 10;
	public int ExpectValue = 11;
	
	/*
	 * 从Excel所在表所有数据中提取API需要的数据，生成二维数组
	 */
	@DataProvider (name="TCApiProvider")
	public Object[][] TCApiDataSet(){
		String filePath = GlobalVars.TCPATH;
		
		List<Integer> filterList = new ArrayList<Integer>();
		filterList.add(Name);
		filterList.add(Sex);
		filterList.add(ExpectedStatusCode);
		filterList.add(ExcpectKey);
		filterList.add(ExpectValue);
		
		GenerateDataProvider generateDataProvider = new GenerateDataProvider();
		Object[][] data = generateDataProvider.testDataSet(filePath, "sheet1", filterList);
		
		return data;
	}
	
	public static void main(String[] args) {
		TCsEntity tCsEntity = new TCsEntity();
		Object[][] xx = tCsEntity.TCApiDataSet();
		for(int i = 0; i< xx.length; i++){
			for (int j =0; j<4; j++){
				System.out.println(xx[i][j]);
			}
		}
	}

}
