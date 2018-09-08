package scripts.transaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import selenium.webdriver.chrome.options 

public class TransactionEntrySingleAccountScenario {

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
				"test\\resources\\data\\Transaction.xlsx", "Transaction");
		return (retObjArr);

	}

	@BeforeClass
	public void setUp() {

		pom = new TransactionEntryPOM(driver, "chrome");
		/*
		 * SoftPACLoginLogoutPOM loginlogout= new SoftPACLoginLogoutPOM(driver);
		 * loginlogout.login();
		 */
		pom.login();
		pom.transactionEntry();

	}

	@Test(dataProvider = "DP1", priority = 0)
	public void viewPhotoSignature(String memberID, String noofaccounts,
			String accounts, String amounts, String accountNumber,
			String transactionType, String instrumentType, String chequeno,
			String charges, String transactionCode, String amount) {

		try {
			pom.viewPhotoSignature(memberID);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(dataProvider = "DP1", priority = 1)
	public void singleAccountTransaction(String memberID, String noofaccounts,
			String accounts, String amounts, String accountNumber,
			String transactionType, String instrumentType, String chequeno,
			String charges, String transactionCode, String amount) {

		pom.performSingleAccountTransaction(memberID, accountNumber,
				transactionType, instrumentType, chequeno, charges,
				transactionCode, amount);

	}

	@Test(dataProvider = "DP1", priority = 2)
	public void accountNumberCheckAllAutofields(String memberID,
			String noofaccounts, String accounts, String amounts,
			String accountNumber, String transactionType,
			String instrumentType, String chequeno, String charges,
			String transactionCode, String amount) {
		pom.selectAccountNumber(memberID, accountNumber);
	}

	@Test(dataProvider = "DP1", priority = 3)
	public void checkChargesAmountUpdate(String memberID, String noofaccounts,
			String accounts, String amounts, String accountNumber,
			String transactionType, String instrumentType, String chequeno,
			String charges, String transactionCode, String amount) {
		pom.checkChargesAmountUpdate(memberID, noofaccounts, accounts, amounts,
				accountNumber, transactionType, instrumentType, chequeno,
				charges, transactionCode, amount);
	}

	@Test(dataProvider = "DP1", priority = 3)
	public void checkAmountEditableCharges(String memberID,
			String noofaccounts, String accounts, String amounts,
			String accountNumber, String transactionType,
			String instrumentType, String chequeno, String charges,
			String transactionCode, String amount) {
		pom.checkAmountEditableCharges(memberID, accountNumber,
				transactionType, instrumentType, chequeno, charges,
				transactionCode, amount);
	}

	@AfterClass
	public void turnDown() {
		driver = pom.returnDriver();
		pom.logout();
		driver.close();
		/*
		 * SoftPACLoginLogoutPOM loginlogout= new SoftPACLoginLogoutPOM(driver);
		 * loginlogout.logout();
		 */
	}
}
