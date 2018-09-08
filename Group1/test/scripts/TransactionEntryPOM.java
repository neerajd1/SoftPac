package scripts.transaction;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransactionEntryPOM {

	// URL
	private String getURL = "http://205.147.102.59:8080/SoftPac/login";

	// login
	private By usernameText = By.id("user_name"), passwordText = By
			.id("password"), submit = By.className("submit"), logoutLink = By
			.className("logout");
	private String username = "beta1", password = "beta@123";

	// fillForm
	private By MemberID = By.cssSelector("#member_chosen > a"),
			memberIdDropdown = By
					.cssSelector("#member_chosen > div:nth-child(2) > ul");
	private By accountNumber = By.cssSelector("#account_chosen > a"),
			accountNumberDropdown = By
					.cssSelector("#account_chosen > div:nth-child(2) > ul");
	private By transactionType = By.cssSelector("#transaction_chosen > a"),
			transactionTypeDropdown = By
					.cssSelector("#transaction_chosen > div > ul");

	private By instrumentType = By.cssSelector("#instrumenttype_chosen > a"),
			instrumentTypeDropdown = By
					.cssSelector("#instrumenttype_chosen > div > ul");
	private By transactionCode = By.cssSelector("#txncodeee_chosen > a"),
			transactionCodeDropdown = By
					.cssSelector("#txncodeee_chosen > div > ul");
	private By amount = By.id("amount");
	private By chequeno = By.id("chequeno");
	private By charges = By.cssSelector("#charges_chosen > a"),
			chargesDropdown = By.cssSelector("#charges_chosen > div > ul");

	// submitButton
	private By submitButton = By.id("submit");

	// view photo link
	private By photoLink = By.xpath("//*[@id='div4']/a");

	// check update placeholders

	private By holderName = By.id("holder"), odBalance = By.id("draw"),
			odInt = By.id("odint"), clearing = By.id("clearing");
	private By totalBalance = By.id("balance"), modeOfOperation = By
			.id("view_details"), token = By.id("token"), particulars = By
			.id("particular");

	private String hn[] = new String[2], odb[] = new String[2],
			odi[] = new String[2], clear[] = new String[2],
			tb[] = new String[2], moo[] = new String[2], tok[] = new String[2],
			partic[] = new String[2];

	// Transaction Entry page visit
	private By transactionLink = By
			.cssSelector("body > div.mm-page > div.submenu > nav > ul > li:nth-child(2) > a"),
			transactionEntry = By
					.cssSelector("body > div.mm-page > div.submenu > nav > ul > li:nth-child(2) > ul > li:nth-child(2) > a"),
			paymentReciept = By
					.cssSelector("body > div.mm-page > div.submenu > nav > ul > li:nth-child(2) > ul > li:nth-child(2) > ul > li:nth-child(1) > a");
	// Multiple accounts
	By noofaccounts = By.cssSelector("#multiple_no");
	By noofaccountsshow = By.cssSelector("#hide_multiple");
	By accountshowclose = By.cssSelector("#close");
	By accountdonebutton = By.cssSelector("#done");

	private By voucherlink = By.className("voucher");

	// Global elements needed
	private WebDriver driver;
	private Actions builder;

	// Constructor
	public TransactionEntryPOM(WebDriver driver, String browser) {
		switch (browser.toUpperCase()) {

		case "FIREFOX":
			driver = setFirefox();
			break;
		case "CHROME":
			driver = setChrome();
			break;
		default:
			break;

		}
		this.driver = driver;
		this.builder = new Actions(driver);
		// counterScreenshot = 0;

	}

	// Functions

	public void login() {

		try {

			goToLink();

		} catch (InterruptedException e) {
		}

		driver.findElement(usernameText).sendKeys(username);
		driver.findElement(passwordText).sendKeys(password);
		driver.findElement(submit).click();

	}

	public void logout() {
		driver.findElement(logoutLink).click();
	}

	public WebDriver setChrome() {
		System.setProperty("webdriver.chrome.driver",
				"test\\resources\\drivers\\chromedriver.exe");

		return driver = new ChromeDriver();
	}

	public WebDriver setFirefox() {
		File pathToBinary = new File(
				"C:\\Users\\AM101_PC4\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();

		return driver = new FirefoxDriver(ffBinary, firefoxProfile);
	}

	public WebDriver returnDriver() {
		return driver;
	}

	public void goToLink() throws InterruptedException {

		driver.manage().window().maximize();
		driver.get(getURL);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);

	}

	public void transactionEntry() {

		System.out.println("Entered transactionEntry()");

		WebElement element = driver.findElement(transactionLink);
		builder.moveToElement(element).build().perform();

		element = driver.findElement(transactionEntry);
		builder.moveToElement(element).build().perform();

		element = driver.findElement(paymentReciept);
		builder.moveToElement(element).click().build().perform();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		System.out.println("Executed transactionEntry()");
	}

	public void fillSingleAccountForm(String memberID, String accountNumber,
			String transactionType, String instrumentType, String chequeno,
			String charges, String transactionCode, String amount) {

		System.out.println("Single Account transaction form is to be filled");
		record(0);
		System.out.println(memberID);
		System.out.println(accountNumber);
		System.out.println(transactionType);
		System.out.println(instrumentType);
		System.out.println(transactionCode);
		System.out.println(amount);

		try {
			assertNotEquals(memberID, "");
			driver.findElement(this.MemberID).click();

			List<WebElement> memberIDDropdownElements = driver.findElement(
					memberIdDropdown).findElements(By.xpath(".//li"));
			for (WebElement element : memberIDDropdownElements) {
				System.out.println("Member ID " + element.getText());
				if (element.getText().equals(memberID)) {
					assertEquals(element.getText(), memberID);
					element.click();
					// driver.findElement(photoLink).click();
					System.out.println("Click happened");
					break;
				}
			}

			driver.findElement(this.accountNumber).click();
			List<WebElement> accountNumberDropdownElements = driver
					.findElement(accountNumberDropdown).findElements(
							By.xpath(".//li"));

			for (WebElement element : accountNumberDropdownElements) {
				System.out.println("Account number " + element.getText());
				if (element.getText().equals(accountNumber)) {
					assertEquals(element.getText(), accountNumber);
					element.click();
					// record(1);
					System.out.println("Click happened account number");
					break;
				}
			}
			driver.findElement(this.transactionType).click();

			List<WebElement> transactionTypeDropdownElements = driver
					.findElement(transactionTypeDropdown).findElements(
							By.xpath(".//li"));
			for (WebElement element : transactionTypeDropdownElements) {
				System.out.println("Transaction Type " + element.getText());
				if (element.getText().equals(transactionType)) {
					element.click();
					System.out.println("Click happened");
					break;
				}
			}

			driver.findElement(this.instrumentType).click();
			List<WebElement> instrumentTypeDropdownElements = driver
					.findElement(instrumentTypeDropdown).findElements(
							By.xpath(".//li"));
			for (WebElement element : instrumentTypeDropdownElements) {
				System.out.println("Instrument Type " + element.getText());
				if (element.getText().equals(instrumentType)) {
					element.click();
					System.out.println("Click happened");
					break;
				}
			}
			if (driver.findElement(this.instrumentType).getText()
					.equalsIgnoreCase("cheque")) {

				Thread.sleep(3000);

				WebDriverWait wait = new WebDriverWait(driver, 3000);
				WebElement we = wait.until(ExpectedConditions
						.visibilityOfElementLocated(this.chequeno));
				we.clear();
				we.sendKeys(chequeno);
				Thread.sleep(3000);
			}

			driver.findElement(this.transactionCode).click();
			List<WebElement> transactionCodeDropdownElements = driver
					.findElement(transactionCodeDropdown).findElements(
							By.xpath(".//li"));
			for (WebElement element : transactionCodeDropdownElements) {
				System.out.println("Transaction Code " + element.getText());
				if (element.getText().contains(transactionCode)) {
					element.click();
					System.out.println("Click happened");
					break;
				}
			}

			driver.findElement(this.amount).clear();

			if (!instrumentType.equals("Charges")) {
				driver.findElement(this.amount).sendKeys(amount);
			} else {
				driver.findElement(this.charges).click();
				List<WebElement> chargesDropdownElements = driver.findElement(
						chargesDropdown).findElements(By.xpath(".//li"));
				for (WebElement element : chargesDropdownElements) {
					System.out.println("Transaction Code " + element.getText());
					if (element.getText().contains(charges)) {
						element.click();
						System.out.println("Click happened");
						break;
					}
				}
			}
			record(1);

		} catch (Exception e) {
			System.out.println("exception");
		}

		// assertUpdatedValues();

		System.out.println("Single Account transaction form is filled");

	}

	public void checkChargesAmountUpdate(String memberID, String noofaccounts,
			String accounts, String amounts, String accountNumber,
			String transactionType, String instrumentType, String chequeno,
			String charges, String transactionCode, String amount) {

		fillSingleAccountForm(memberID, accountNumber, transactionType,
				instrumentType, chequeno, charges, transactionCode, amount);
		String expected = driver.findElement(this.amount).getText();
		driver.findElement(this.amount).sendKeys(amount);
		String actual = driver.findElement(this.amount).getText();
		if (!actual.equals(expected)) {
			System.out.println("Amount field is editable");
		}
		assertEquals(actual, expected);

	}

	public void viewPhotoSignature(String memberID) throws InterruptedException {

		System.out.println("Entered viewPhotoSignature()");
		try {
			assertNotEquals("", memberID);
			driver.findElement(this.MemberID).click();
			List<WebElement> memberIDDropdownElements = driver.findElement(
					memberIdDropdown).findElements(By.xpath(".//li"));
			for (WebElement element : memberIDDropdownElements) {
				System.out.println("Member ID " + element.getText());
				if (element.getText().equals(memberID)) {
					assertEquals(element.getText(), memberID);
					element.click();
					// driver.findElement(photoLink).click();
					System.out.println("Click happened");
					break;
				}
			}

		} catch (Exception e) {
			System.out.println("Exception");
		}

		driver.findElement(photoLink).click();

		String transcationWindowHandler = driver.getWindowHandle();
		String viewsignatureWindowHandler;
		Set<String> handles = driver.getWindowHandles();
		handles.remove(transcationWindowHandler);
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			viewsignatureWindowHandler = iterator.next();
			driver.switchTo().window(viewsignatureWindowHandler);
			System.out.println(viewsignatureWindowHandler);
			String verify = driver
					.findElement(
							By.cssSelector("body > section > article > div > div > img"))
					.getAttribute("src");
			System.out.println(verify);
			if (verify != null)
				// assertEquals("http://205.147.102.59:8080/SoftPac/resources/mytheme/images/0",verify);
				assertNotEquals(
						"http://205.147.102.59:8080/SoftPac/resources/mytheme/images/0",
						verify);
			System.out.println("Photo uploaded successfully");
			Thread.sleep(3000);
		}
		driver.close();
		driver.switchTo().window(transcationWindowHandler);
		Thread.sleep(2000);
		System.out.println("Executed viewPhotoSignature()");
	}

	public void selectAccountNumber(String memberID, String accountNumber) {
		System.out.println("Entered selectAccountNumber()");
		try {
			assertNotEquals(memberID, "");
			driver.findElement(this.MemberID).click();

			List<WebElement> memberIDDropdownElements = driver.findElement(
					memberIdDropdown).findElements(By.xpath(".//li"));
			for (WebElement element : memberIDDropdownElements) {
				System.out.println("Member ID " + element.getText());
				if (element.getText().equals(memberID)) {
					assertEquals(element.getText(), memberID);
					element.click();
					// driver.findElement(photoLink).click();
					System.out.println("Click happened");
					break;
				}
			}
			record(0);
			driver.findElement(this.accountNumber).click();
			List<WebElement> accountNumberDropdownElements = driver
					.findElement(accountNumberDropdown).findElements(
							By.xpath(".//li"));

			for (WebElement element : accountNumberDropdownElements) {
				System.out.println("Account number " + element.getText());
				if (element.getText().equals(accountNumber)) {
					assertEquals(element.getText(), accountNumber);
					element.click();
					record(1);
					System.out.println("Click happened account number");
					break;
				}
			}

			// record(1);

		} catch (Exception e) {
			System.out.println("Exception");
		}

		assertEquals(assertUpdatedValues(), 8);
		System.out.println("Executed selectAccountNumber()");

	}

	public void record(int i) {

		System.out.println("Recording");
		System.out.println(driver.findElement(particulars).getAttribute(
				"placeholder"));

		partic[i] = driver.findElement(particulars).getAttribute("value");
		hn[i] = driver.findElement(holderName).getAttribute("value");
		odb[i] = driver.findElement(odBalance).getAttribute("value");
		odi[i] = driver.findElement(odInt).getAttribute("value");
		clear[i] = driver.findElement(clearing).getAttribute("value");
		tb[i] = driver.findElement(totalBalance).getAttribute("value");
		moo[i] = driver.findElement(modeOfOperation).getAttribute("value");
		tok[i] = driver.findElement(token).getAttribute("value");

		System.out.println("Recording over");

	}

	public int assertUpdatedValues() {
		int counter = 0;

		try {

			if (!hn[0].equalsIgnoreCase(hn[1])) {
				counter++;
				System.out.println("hn updated");
			}
			if (!odb[0].equalsIgnoreCase(odb[1])) {
				counter++;
				System.out.println("odb updated");
			}
			if (!odi[0].equalsIgnoreCase(odi[1])) {
				counter++;
				System.out.println("odi updated");
			}
			if (!clear[0].equalsIgnoreCase(clear[1])) {
				counter++;
				System.out.println("clear updated");
			}
			if (!clear[0].equalsIgnoreCase(clear[1])) {
				counter++;
				System.out.println("hn updated");
			}
			if (!tb[0].equalsIgnoreCase(tb[1])) {
				counter++;
				System.out.println("tb updated");
			}
			if (!moo[0].equalsIgnoreCase(moo[1])) {
				counter++;
				System.out.println("moo updated");
			}
			if (!tok[0].equalsIgnoreCase(tok[1])) {
				counter++;
				System.out.println("tok updated");
			}
			System.out.println("Assert counter" + counter);
		} catch (Exception e) {
			System.out.println("Exception");
		}
		return counter;
	}

	public void particularsCheck() {
		System.out.println(partic[0] + " equals " + partic[1] + " ?");
		assertNotEquals(partic[0], partic[1]);
	}

	public void checkAmountEditableCharges(String memberID,
			String accountNumber, String transactionType,
			String instrumentType, String chequeno, String charges,
			String transactionCode, String amount) {

		fillSingleAccountForm(memberID, accountNumber, transactionType,
				instrumentType, chequeno, charges, transactionCode, amount);
		String expected = driver.findElement(this.amount).getText();
		String actual;
		if (instrumentType.equals("Charges")) {

			driver.findElement(this.charges).click();
			List<WebElement> chargesDropdownElements = driver.findElement(
					chargesDropdown).findElements(By.xpath(".//li"));
			for (WebElement element : chargesDropdownElements) {
				System.out.println("Transaction Code " + element.getText());
				if (element.getText().contains(charges)) {
					element.click();
					System.out.println("Click happened");
					break;
				}
			}
			driver.findElement(this.amount).sendKeys(amount);
			actual = driver.findElement(this.amount).getText();
			System.out.println("Assert Charges");
			assertEquals(actual, expected);
		}

	}

	public void performSingleAccountTransaction(String memberID,
			String accountNumber, String transactionType,
			String instrumentType, String chequeno, String charges,
			String transactionCode, String amount) {
		System.out.println("Entered performSingleAccountTransaction()");
		fillSingleAccountForm(memberID, accountNumber, transactionType,
				instrumentType, chequeno, charges, transactionCode, amount);
		particularsCheck();
		driver.findElement(submitButton).click();

		System.out.println("Executed performSingleAccountTransaction()");

		if (driver.getTitle().equals("JBoss Web/7.0.13.Final - Error report"))
			driver.navigate().back();

		else {

			System.out.println("Transaction voucher check started");

			WebDriverWait wait = new WebDriverWait(driver, 3000);
			WebElement we = wait.until(ExpectedConditions
					.visibilityOfElementLocated(voucherlink));
			assertEquals(we.getText(),
					"Click Here To Generate Voucher For Previous Transaction");
			if (we.getText().contains("Generate Voucher")) {
				System.out.println(we.getText());
				we.click();
			} else {

				System.out.println("Transaction failed");
				System.out.println(we.getText());
			}
			System.out.println("Transaction voucher check executed");
		}

	}

	public void TestCaseAddMultipleAccounts(String MemberID,
			String NoofAccounts, String Account, String Amount)
			throws Exception {

		// enter Member ID
		Thread.sleep(2000);
		driver.findElement(this.MemberID).click();
		List<WebElement> memberIDDropdownElements = driver.findElement(
				memberIdDropdown).findElements(By.xpath(".//li"));
		for (WebElement element : memberIDDropdownElements) {
			if (element.getText().equals(MemberID)) {
				assertEquals(element.getText(), MemberID);
				element.click();
				break;
			}
		}

		// PerformAddMultipleAccounts

		assertNotEquals(NoofAccounts, "");
		driver.findElement(noofaccounts).clear();
		driver.findElement(noofaccounts).sendKeys(NoofAccounts);
		int max = Integer.parseInt(NoofAccounts);
		Thread.sleep(2000);
		int amountsum = 0;
		driver.findElement(noofaccountsshow).click();
		Thread.sleep(2000);
		if (max == 0) {
			driver.findElement(accountshowclose).click();
			Thread.sleep(2000);
		} else {
			String[] array1 = Account.split("#", max);
			String[] array2 = Amount.split("#", max);

			for (int i = 0; i < max; i++) {
				if (array1[i].equals("") || array2[i].equals("")) {
					driver.findElement(accountshowclose).click();
					assertEquals(array1[i], "");
					assertEquals(array2[i], "");
				} else {
					amountsum += Integer.parseInt(array2[i]);
					driver.findElement(
							By.cssSelector("#select_" + (i + 2)
									+ "_chosen > a > span")).click();
					driver.findElement(
							By.cssSelector("#select_"
									+ (i + 2)
									+ "_chosen > div > div > input[type='text']"))
							.sendKeys(array1[i] + Keys.ENTER);
					driver.findElement(By.cssSelector("#input_" + (i + 2) + ""))
							.sendKeys(array2[i]);
					Thread.sleep(3000);
				}
			}
			driver.findElement(accountdonebutton).click();
			Thread.sleep(2000);
		}
		int amount1 = Integer.parseInt(driver.findElement(amount).getAttribute(
				"value"));
		System.out.println("amount field: " + amount1);
		assertEquals(amountsum, amount1);
		System.out.println("PASS");

	}

	public void TestCasePerformMultipleTransaction(String MemberID,
			String NoofAccounts, String Account, String Amount,
			String AccountNo, String TransactionType, String InstrumentType,
			String ChequeNo, String Charges, String TransactionCode,
			String Amount1) throws Exception {

		assertNotEquals(MemberID, "");

		// enter Member ID
		Thread.sleep(2000);
		driver.findElement(this.MemberID).click();
		List<WebElement> memberIDDropdownElements = driver.findElement(
				memberIdDropdown).findElements(By.xpath(".//li"));
		for (WebElement element : memberIDDropdownElements) {
			if (element.getText().equals(MemberID)) {
				assertEquals(element.getText(), MemberID);
				element.click();
				break;
			}
		}

		// PerformAddMultipleAccounts

		assertNotEquals(NoofAccounts, "");
		driver.findElement(noofaccounts).clear();
		driver.findElement(noofaccounts).sendKeys(NoofAccounts);
		int max = Integer.parseInt(NoofAccounts);
		int amountsum = 0;
		Thread.sleep(2000);
		driver.findElement(noofaccountsshow).click();
		Thread.sleep(2000);
		if (max == 0) {
			driver.findElement(accountshowclose).click();
			Thread.sleep(2000);
		} else {
			String[] array1 = Account.split("#", max);
			String[] array2 = Amount.split("#", max);
			for (int i = 0; i < max; i++) {

				if (array1[i].equals("") || array2[i].equals("")) {
					driver.findElement(accountshowclose).click();
					assertEquals(array1[i], "");
					assertEquals(array2[i], "");
				} else {

					amountsum += Integer.parseInt(array2[i]);

					driver.findElement(
							By.cssSelector("#select_" + (i + 2)
									+ "_chosen > a > span")).click();
					driver.findElement(
							By.cssSelector("#select_"
									+ (i + 2)
									+ "_chosen > div > div > input[type='text']"))
							.sendKeys(array1[i] + Keys.ENTER);
					driver.findElement(By.cssSelector("#input_" + (i + 2) + ""))
							.sendKeys(array2[i]);
					Thread.sleep(3000);
				}
			}
			driver.findElement(accountdonebutton).click();
			Thread.sleep(2000);
		}
		int amount1 = Integer.parseInt(driver.findElement(amount).getAttribute(
				"value"));
		System.out.println("amount field: " + amount1);
		assertEquals(amountsum, amount1);
		System.out.println("PASS");
		amountsum = 0;

		// performAddAccountNo
		assertNotEquals(AccountNo, "");
		Thread.sleep(2000);
		driver.findElement(accountNumber).click();
		List<WebElement> accountNumberDropdownElements = driver.findElement(
				accountNumberDropdown).findElements(By.xpath(".//li"));
		for (WebElement element : accountNumberDropdownElements) {
			if (element.getText().equals(AccountNo)) {
				assertEquals(element.getText(), AccountNo);
				element.click();
				// record(1);
				break;
			}
		}

		// performTransactionType

		driver.findElement(transactionType).click();
		List<WebElement> transactionTypeDropdownElements = driver.findElement(
				transactionTypeDropdown).findElements(By.xpath(".//li"));
		for (WebElement element : transactionTypeDropdownElements) {
			if (element.getText().equals(TransactionType)) {
				element.click();
				break;
			}
		}

		// performInstrumentType

		driver.findElement(instrumentType).click();
		List<WebElement> instrumentTypeDropdownElements = driver.findElement(
				instrumentTypeDropdown).findElements(By.xpath(".//li"));
		for (WebElement element : instrumentTypeDropdownElements) {
			if (element.getText().equals(InstrumentType)) {
				element.click();
				break;
			}
		}
		if (driver.findElement(instrumentType).getText()
				.equalsIgnoreCase("cheque")) {
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 3000);
			WebElement we = wait.until(ExpectedConditions
					.visibilityOfElementLocated(this.chequeno));
			we.sendKeys(ChequeNo);
			Thread.sleep(3000);
		}

		// performTransactionCode

		driver.findElement(transactionCode).click();
		List<WebElement> transactionCodeDropdownElements = driver.findElement(
				transactionCodeDropdown).findElements(By.xpath(".//li"));
		for (WebElement element : transactionCodeDropdownElements) {
			if (element.getText().equals(TransactionCode)) {
				element.click();
				break;
			}
		}

		// performSubmitandGenerateVoucher

		if (driver.getTitle().equals("JBoss Web/7.0.13.Final - Error report"))
			driver.navigate().back();

		else {

			System.out.println("Transaction voucher check started");

			WebDriverWait wait = new WebDriverWait(driver, 3000);
			WebElement we = wait.until(ExpectedConditions
					.visibilityOfElementLocated(voucherlink));
			assertEquals(we.getText(),
					"Click Here To Generate Voucher For Previous Transaction");
			if (we.getText().contains("Generate Voucher")) {
				System.out.println(we.getText());
				we.click();
			} else {

				System.out.println("Transaction failed");
				System.out.println(we.getText());
			}
			System.out.println("Transaction voucher check executed");
		}
		/*
		 * ArrayList<String> tabs2 = new ArrayList<String>
		 * (driver.getWindowHandles()); driver.switchTo().window(tabs2.get(1));
		 * Thread.sleep(5000); driver.close();
		 * driver.switchTo().window(tabs2.get(0)); Thread.sleep(2000);
		 */

	}// end of test case
}
