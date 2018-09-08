package scripts.transaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class TransactionEntryMultipleAccountScenario {
	private WebDriver driver;

	TransactionEntryPOM transaction;

	// @SuppressWarnings("deprecation")
	public static String[][] getExcelData(String fileName, String sheetName)
			throws IOException {
		String[][] arrayExcelData = null;
		Workbook wb = null;
		try {
			File file = new File(fileName);
			FileInputStream fs = new FileInputStream(file);
			// .xls
			if (fileName.substring(fileName.indexOf(".")).equals(".xlsx")) {

				// If it is xlsx file then create object of XSSFWorkbook class

				wb = new XSSFWorkbook(fs);
			} else if (fileName.substring(fileName.indexOf(".")).equals(".xls")) {
				// If it is xls file then create object of HSSFWorkbook class
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

	@BeforeClass
	public void beforeClass() throws Exception {
		transaction = new TransactionEntryPOM(driver, "chrome");
		transaction.login();
		transaction.transactionEntry();
		Thread.sleep(2000);
	}

	@DataProvider(name = "DP1")
	public Object[][] createData1() throws IOException {
		Object[][] retObjArr = getExcelData(
				"test\\resources\\data\\Transaction.xlsx",
				"Transaction");
		System.out
				.println("*****************  Excel Sheet Accessed *************************");
		return (retObjArr);
	}

	@Test(dataProvider = "DP1",priority = 1)
	public void TestCaseAddMultipleAccounts(String MemberID, String NoofAccounts, String Account,
			String Amount, String AccountNumber, String TransactionType,
			String InstrumentType, String ChequeNo, String Charges,
			String TransactionCode, String Amount1)
			throws Exception {
		System.out.println("Test case for member is able to add multiple accounts ");
		transaction.TestCaseAddMultipleAccounts(MemberID, NoofAccounts,Account, Amount);
		System.out.println("Test case for member is able to add multiple accounts: PASSED");
	}
	
	
	@Test(dataProvider = "DP1", priority = 2)
	public void checkChargesAmountUpdate(String memberID, String noofaccounts,
			String accounts, String amounts, String accountNumber,
			String transactionType, String instrumentType, String chequeno,
			String charges, String transactionCode, String amount) {
		transaction.checkChargesAmountUpdate(memberID, noofaccounts, accounts, amounts,
				accountNumber, transactionType, instrumentType, chequeno,
				charges, transactionCode, amount);
	}

	
	
	@Test(dataProvider = "DP1",priority = 3)
	public void TestCasePerformMultipleTransaction(String MemberID, String NoofAccounts, String Account,
			String Amount, String AccountNumber, String TransactionType,
			String InstrumentType, String ChequeNo, String Charges,
			String TransactionCode, String Amount1) throws Exception {
		System.out.println("Started");
		transaction.TestCasePerformMultipleTransaction(MemberID, NoofAccounts,
				Account, Amount, AccountNumber, TransactionType,
				InstrumentType, ChequeNo, Charges, TransactionCode, Amount1);
		System.out.println("PASSED");
	}

	
	

	@AfterClass
	public void afterClass() throws Exception {
		driver = transaction.returnDriver();
		transaction.logout();
		driver.close();

	}

}
