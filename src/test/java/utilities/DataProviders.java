package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "SignupData")
	public String[][] getsignupData() throws IOException {
		String path = ".\\testData\\DeltaAppUsers.xlsx";// taking excel file from testData
		ExcelUtility excelUtil = new ExcelUtility(path);// creating an object for XLUtility

		int totalrows = excelUtil.getRowCount("Sheet1");
		int totalcols = excelUtil.getCellCount("Sheet1", 1);

		String SignupDataArray[][] = new String[totalrows][totalcols];// created for two dimension array which can store the sign up data

		for (int i = 1; i <= totalrows; i++) // 1 //read the data from excel storing in two dimensional array
		{
			for (int j = 0; j < totalcols; j++)
			{
				SignupDataArray[i - 1][j] = excelUtil.getCellData("Sheet1", i, j); // storing Excel data into arra
			}
		}
		return SignupDataArray;// returning two dimension array
	}
}