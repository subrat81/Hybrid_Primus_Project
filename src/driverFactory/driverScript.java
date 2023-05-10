package driverFactory;

import org.testng.annotations.Test;

import commonFunctions.FunctionLibrary;
import constants.AppUtil;
import utillity.ExcelFileUtil;

public class driverScript extends AppUtil {
String innerpath="D:\\test\\SampleWork\\Hybrid_Primus_Project\\TestInput\\Primussheet.xlsx";
String outerpath="D:\\test\\SampleWork\\Hybrid_Primus_Project\\TestOutput\\PrimusResult.xlsx";
String TCSheet="MasterTestcases";
String TSSheet="TestSteps";
@Test
public void primusTest()throws Throwable
{
	boolean res=false;
	String TCres="";
	ExcelFileUtil xl=new ExcelFileUtil(innerpath);
	int TCCount=xl.rowCount(TCSheet);
	int TsCount=xl.rowCount(TSSheet);
	for(int i=1;i<=TCCount;i++)
	{
		String TCid=xl.getCelldata(TCSheet, i, 0);
		String execution=xl.getCelldata(TCSheet, i, 2);
		if(execution.equalsIgnoreCase("Y"))
		{
			for(int j=1;j<=TsCount;j++)
			{
				String TSid=xl.getCelldata(TSSheet, j, 0);
				if(TCid.equalsIgnoreCase(TSid))
				{
					String keyword=xl.getCelldata(TSSheet, j, 4);
					if(keyword.equalsIgnoreCase("AdminLogin"))
					{
						String para1=xl.getCelldata(TSSheet, j, 5);
						String para2=xl.getCelldata(TSSheet, j, 6);
						res=FunctionLibrary.verifyLogin(para1, para2);
					}
					else if(keyword.equalsIgnoreCase("NewBranch"))
					{
						String para1 =xl.getCelldata(TSSheet, j, 5);
						String para2 =xl.getCelldata(TSSheet, j, 6);
						String para3 =xl.getCelldata(TSSheet, j, 7);
						String para4 =xl.getCelldata(TSSheet, j, 8);
						String para5 =xl.getCelldata(TSSheet, j, 9);
						String para6 =xl.getCelldata(TSSheet, j, 10);
						String para7 =xl.getCelldata(TSSheet, j, 11);
						String para8 =xl.getCelldata(TSSheet, j, 12);
						String para9 =xl.getCelldata(TSSheet, j, 13);
						FunctionLibrary.clickBranch();
						res=FunctionLibrary.verifyBranchCreation(para1, para2, para3, para4, para5, para6, para7, para8, para9);
						
					}
					else if(keyword.equalsIgnoreCase("BranchUpdate"))
					{
						String para1=xl.getCelldata(TSSheet, j, 5);
						String para2=xl.getCelldata(TSSheet, j, 6);
						String para3=xl.getCelldata(TSSheet, j, 10);
						FunctionLibrary.clickBranch();
						res=FunctionLibrary.verifyBranchUpdate(para1, para2, para3);
					}
					else if(keyword.equalsIgnoreCase("AdminLogout"))
					{
						res=FunctionLibrary.verifyLogout();
					}
					String Tsres="";
					if(res)
					{
						Tsres="Pass";
						xl.setCelldata(TSSheet, j, 3, Tsres, outerpath);
					}
					else
					{
						Tsres="Fail";
						xl.setCelldata(TSSheet, j, 3, Tsres, outerpath);
					}
					TCres=Tsres;
					
				}
			}
			xl.setCelldata(TCSheet, i, 3, TCres, outerpath);
		}
		else
		{
			TCres="Blocked";
			xl.setCelldata(TCSheet, i, 3, TCres, outerpath);
		}
	}
	
}
}
