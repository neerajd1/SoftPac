package scripts;

/**
 * @author Neeraj
 *
 */
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.FindElement;
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
	private By chequeno=By.id("chequeno");

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

	// voucher
	//
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
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

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
			String transactionType, String instrumentType,String chequeno, 
			String transactionCode, String amount) {

		System.out.println("Single Account transaction form is to be filled");
		record(0);
		System.out.println(memberID);
		System.out.println(accountNumber);
		System.out.println(transactionType);
		System.out.println(instrumentType);
		System.out.println(transactionCode);
		System.out.println(amount);

		try {
			driver.findElement(this.MemberID).click();

			List<WebElement> memberIDDropdownElements = driver.findElement(
					memberIdDropdown).findElements(By.xpath(".//li"));
			for (WebElement element : memberIDDropdownElements) {
				System.out.println("Member ID " + element.getText());
				if (element.getText().contains(memberID)) {

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
				if (element.getText().contains(accountNumber)) {

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
				if (element.getText().contains(transactionType)) {
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
				if (element.getText().contains(instrumentType)) {
					element.click();
					System.out.println("Click happened");
					break;
				}
			}

			if(driver.findElement(this.instrumentType).getText().equalsIgnoreCase("cheque")){
				
				Thread.sleep(3000);
				
				WebDriverWait wait = new WebDriverWait(driver, 3000);
				WebElement we = wait.until(ExpectedConditions
						.visibilityOfElementLocated(this.chequeno));
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
			driver.findElement(this.amount).sendKeys(amount);
			record(1);

		} catch (Exception e) {
			System.out.println("exception");
		}

		// assertUpdatedValues();

		System.out.println("Single Account transaction form is filled");

	}

	public void viewPhotoSignature(String memberID) throws InterruptedException {

		System.out.println("Entered viewPhotoSignature()");

		try {
			driver.findElement(this.MemberID).click();
			List<WebElement> memberIDDropdownElements = driver.findElement(
					memberIdDropdown).findElements(By.xpath(".//li"));
			for (WebElement element : memberIDDropdownElements) {
				System.out.println("Member ID " + element.getText());
				if (element.getText().contains(memberID)) {
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
			if(verify!=null)
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

			record(0);

			driver.findElement(this.MemberID).click();

			List<WebElement> memberIDDropdownElements = driver.findElement(
					memberIdDropdown).findElements(By.xpath(".//li"));
			for (WebElement element : memberIDDropdownElements) {
				System.out.println("Member ID " + element.getText());
				if (element.getText().contains(memberID)) {

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
				if (element.getText().contains(accountNumber)) {

					element.click();
					record(1);
					System.out.println("Click happened account number");
					break;
				}
			}

			record(1);

		} catch (Exception e) {
			System.out.println("Exception");
		}

		assertUpdatedValues();
		System.out.println("Executed selectAccountNumber()");

	}

	public void record(int i) {

		System.out.println("Recording");
		System.out.println(driver.findElement(particulars).getAttribute(
				"placeholder"));

		if (driver.findElement(particulars).getAttribute("value") != null)
			partic[i] = driver.findElement(particulars).getAttribute("value");
		else
			partic[i] = "is";

	
		if (driver.findElement(holderName).getAttribute("value") != null)
			hn[i] = driver.findElement(holderName).getAttribute("value");
		else
			hn[i] = "is";
		odb[i] = driver.findElement(odBalance).getAttribute("value");
		odi[i] = driver.findElement(odInt).getAttribute("value");

		if (driver.findElement(odBalance).getAttribute("value") != null)
			odb[i] = driver.findElement(odBalance).getAttribute("value");
		else
			odb[i] = "is";

		if (driver.findElement(odInt).getAttribute("value") != null)
			odi[i] = driver.findElement(odInt).getAttribute("value");
		else
			odi[i] = "is";
		if (driver.findElement(clearing).getAttribute("value") != null)
			clear[i] = driver.findElement(clearing).getAttribute("value");
		else
			clear[i] = "is";

		if (driver.findElement(totalBalance).getAttribute("value") != null)
			tb[i] = driver.findElement(totalBalance).getAttribute("value");
		else
			tb[i] = "is";

		if (driver.findElement(modeOfOperation).getAttribute("value") != null)
			moo[i] = driver.findElement(particulars).getAttribute("value");
		else
			moo[i] = "is";

		if (driver.findElement(token).getAttribute("value") != null)
			tok[i] = driver.findElement(token).getAttribute("value");
		else
			tok[i] = "is";

		
		System.out.println("Recording output :- ");
		System.out.println(partic[i]);
		System.out.println(hn[i]);
		System.out.println(odb[i]);
		System.out.println(odi[i]);
		System.out.println(clear[i]);
		System.out.println(tb[i]);
		System.out.println(moo[i]);
		System.out.println(tok[i]);
		
		System.out.println("Recording over");
		
		
	}

	public void assertUpdatedValues() {
		try {
			
			if(!hn[0].equalsIgnoreCase(hn[1]))
				System.out.println("hn updated");
			if(!odb[0].equalsIgnoreCase(odb[1]))
				System.out.println("odb updated");
			if(!odi[0].equalsIgnoreCase(odi[1]))
				System.out.println("odi updated");
			if(!clear[0].equalsIgnoreCase(clear[1]))
				System.out.println("clear updated");
			if(!clear[0].equalsIgnoreCase(clear[1]))
				System.out.println("hn updated");
			if(!tb[0].equalsIgnoreCase(tb[1]))
				System.out.println("tb updated");
			if(!moo[0].equalsIgnoreCase(moo[1]))
				System.out.println("moo updated");
			if(!tok[0].equalsIgnoreCase(tok[1]))
				System.out.println("tok updated");
			
			//System.out.println(hn[0] + " equals " + hn[1] + " ?");
			assertNotEquals(hn[0], hn[1]);
			System.out.println(odb[0] + " equals " + odb[1] + " ?");
			assertNotEquals(odb[0], odb[1]);
			System.out.println(odi[0] + " equals " + odi[1] + " ?");
			assertNotEquals(odi[0], odi[1]);
			System.out.println(clear[0] + " equals " + clear[1] + " ?");
			assertNotEquals(clear[0], clear[1]);
			System.out.println(tb[0] + " equals " + tb[1] + " ?");
			assertNotEquals(tb[0], tb[1]);
			System.out.println(moo[0] + " equals " + moo[1] + " ?");
			assertNotEquals(moo[0], moo[1]);
			System.out.println(tok[0] + " equals " + tok[1] + " ?");
			assertNotEquals(tok[0], tok[1]);
		} catch (Exception e) {
			System.out.println("Exception");
		}

	}

	public void particularsCheck() {
		System.out.println(partic[0] + " equals " + partic[1] + " ?");
		assertNotEquals(partic[0], partic[1]);
	}

	public void performSingleAccountTransaction(String memberID, String accountNumber,
			String transactionType, String instrumentType,String chequeno, 
			String transactionCode, String amount) {
		System.out.println("Entered performSingleAccountTransaction()");
		fillSingleAccountForm(memberID, accountNumber, transactionType,
				instrumentType,chequeno, transactionCode, amount );
		particularsCheck();
		driver.findElement(submitButton).click();

		System.out.println("Executed performSingleAccountTransaction()");
		
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		WebElement we = wait.until(ExpectedConditions
				.visibilityOfElementLocated(voucherlink));
		assertEquals(we.getText(), "Click Here To Generate Voucher For Previous Transaction");
		if(we.getText().contains("Generate Voucher")){
			System.out.println(we.getText());
			we.click();	
		}
		else{
			System.out.println(we.getText());
		}
		
		

	}



}
