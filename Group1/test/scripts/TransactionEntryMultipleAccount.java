package scripts;

/**
 * @author Neeraj
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TransactionEntryMultipleAccount {

	WebDriver driver;
	TransactionEntryPOM pom;

	// @SuppressWarnings("deprecation")
	public static String[][] getExcelData(String fileName, String sheetName)
			throws IOException {

		String[][] arrayExcelData = null;
		Workbook wb = null;
		try {
			File file = new File(fileName);
			FileInputStream fs = new FileInputStream(file);
			if (fileName.substring(fileName.indexOf(".")).equals(".xlsx")) {
				wb = new XSSFWorkbook(fs);
			} else if (fileName.substring(fileName.indexOf(".")).equals(".xls")) {
				wb = new HSSFWorkbook(fs);
			}
			Sheet sh = wb.getSheet(sheetName);

			int totalNoOfRows = sh.getPhysicalNumberOfRows();
			int totalNoOfCols = sh.getRow(0).getPhysicalNumberOfCells();

			System.out.println("totalNoOfRows=" + totalNoOfRows + ","
					+ " totalNoOfCols=" + totalNoOfCols);
			arrayExcelData = new String[totalNoOfRows - 1][totalNoOfCols];
			for (int i = 1; i <= totalNoOfRows - 1; i++) {
				for (int j = 0; j <= totalNoOfCols - 1; j++) {
					sh.getRow(i).getCell(j).setCellType(1);
					arrayExcelData[i - 1][j] = sh.getRow(i).getCell(j)
							.getStringCellValue().toString();
				}
			}
		} catch (Exception e) {
			System.out.println("error in getExcelData()");
		}
		return arrayExcelData;

	}

	@DataProvider(name = "DP1")
	public Object[][] createData1() throws IOException {

		Object[][] retObjArr = getExcelData(
				"test\\resources\\data\\Transaction.xlsx", "SingleAccount");
		return (retObjArr);

	}

	@BeforeClass
	public void setUp() {

		pom = new TransactionEntryPOM(driver, "chrome");
		pom.login();
		pom.transactionEntry();

	}

	@Test(dataProvider = "DP1", priority = 1)
	public void multipleAccountTransaction(String memberID,String NoofAccounts, String Account, String Amount,  String accountNumber,
			String transactionType, String instrumentType, String chequeno,
			String charges, String transactionCode, String amount) {

		try {
			pom.performMultipleAccountsTransaction(memberID,NoofAccounts,Account, Amount, accountNumber, transactionType,
					instrumentType, chequeno, charges, transactionCode, amount);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@AfterClass
	public void turnDown() {
		driver = pom.returnDriver();
		pom.logout();
		driver.close();
	}

}
