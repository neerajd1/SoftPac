package scripts;

/**
 * @author Neeraj
 *
 */
import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
import org.openqa.selenium.remote.server.handler.FindElement;

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

	// submitButton
	private By submitButton;

	// view photo link
	private By photoLink = By.xpath("//*[@id='div4']/a");

	//check update placeholders
	
	
	
	By holderName=By.id("holder"),odBalance=By.id("draw"),odInt=By.id("odint"),clearing=By.id("clearing");
	By totalBalance=By.id("balance"),modeOfOperation=By.id("view_details"),token=By.id("token"),particulars=By.id("particular");
	
	String hn,odb,odi,clear,tb,moo,tok,partic ;
	
	// Transaction Entry page visit
	private By transactionLink = By
			.cssSelector("body > div.mm-page > div.submenu > nav > ul > li:nth-child(2) > a"),
			transactionEntry = By
					.cssSelector("body > div.mm-page > div.submenu > nav > ul > li:nth-child(2) > ul > li:nth-child(2) > a"),
			paymentReciept = By
					.cssSelector("body > div.mm-page > div.submenu > nav > ul > li:nth-child(2) > ul > li:nth-child(2) > ul > li:nth-child(1) > a");

	// Global elements needed
	private WebDriver driver;
	private Actions action;

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
		this.action = new Actions(driver);
		
		
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
				"C:\\Users\\AM101_PC2\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();

		return driver = new FirefoxDriver(ffBinary, firefoxProfile);
	}

	public void goToLink() throws InterruptedException {

		driver.manage().window().maximize();
		driver.get(getURL);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

	}

	public void transactionEntry() {

		System.out.println("Entered transactionEntry()");

		Actions builder = new Actions(driver);
		WebElement element = driver.findElement(transactionLink);
		builder.moveToElement(element).build().perform();

		element = driver.findElement(transactionEntry);
		builder.moveToElement(element).build().perform();

		element = driver.findElement(paymentReciept);
		builder.moveToElement(element).click().build().perform();
		
		
		hn=driver.findElement(holderName).getText();
		odb=driver.findElement(odBalance).getText();
		odi=driver.findElement(odInt).getText();
		clear=driver.findElement(clearing).getText();
		tb=driver.findElement(totalBalance).getText();
		moo=driver.findElement(modeOfOperation).getText();
		tok=driver.findElement(token).getText();
		partic=driver.findElement(particulars).getText();
		
		
		

		System.out.println("Executed transactionEntry()");
	}

	public void fillSingleAccountForm(String memberID, String accountNumber,
			String transactionType, String instrumentType,
			String transactionCode, String amount) {

		System.out.println("Single Account transaction form is to be filled");

		/*
		 * driver.findElement(MemberID1).click();
		 * driver.findElement(searchmemberID).sendKeys(Keys.ENTER);
		 */

		try {
			if (driver.findElement(MemberID).getText()
					.contains("-- Select Member ID --")) {
				driver.findElement(MemberID).click();
				List<WebElement> memberIDDropdownElements = driver.findElement(
						memberIdDropdown).findElements(By.xpath(".//li"));
				// List<WebElement> childs =
				// rootWebElement.findElements(By.xpath(".//*"));
				for (WebElement element : memberIDDropdownElements) {
					System.out.println("Member ID " + element.getText());
					if (element.getText().contains(memberID)) {

						element.click();
						// driver.findElement(photoLink).click();
						System.out.println("Click happened");
						break;
					}
				}
				// driver.navigate().back();

			}
		} catch (Exception e) {
			System.out.println("Exception");
		}

		System.out.println("Single Account transaction form is filled");

	}

	public void viewPhotoSignature(String memberID) {

		System.out.println("Entered viewPhotoSignature()");
		/*
		 * driver.findElement(MemberID1).click();
		 * driver.findElement(searchmemberID).sendKeys(Keys.ENTER);
		 */

		System.out.println("Selected value :- "
				+ driver.findElement(MemberID).getText());
		try {
			if (driver.findElement(MemberID).getText()
					.contains("-- Select Member ID --")) {
				driver.findElement(MemberID).click();
				List<WebElement> memberIDDropdownElements = driver.findElement(
						memberIdDropdown).findElements(By.xpath(".//li"));
				// List<WebElement> childs =
				// rootWebElement.findElements(By.xpath(".//*"));
				for (WebElement element : memberIDDropdownElements) {
					System.out.println("Member ID " + element.getText());
					if (element.getText().contains(memberID)) {

						element.click();
						driver.findElement(photoLink).click();

						System.out.println("Click happened");
						break;
					}
				}
				driver.navigate().back();
				//transactionEntry();
			}
		} catch (Exception e) {
			System.out.println("Exception");
		}

		System.out.println("Executed viewPhotoSignature()");
	}

	public void selectAccountNumber(String memberID, String accountNumber) {

		try {
			if (driver.findElement(MemberID).getText()
					.contains("-- Select Member ID --")) {
				driver.findElement(MemberID).click();
				List<WebElement> memberIDDropdownElements = driver.findElement(
						memberIdDropdown).findElements(By.xpath(".//li"));
				// List<WebElement> childs =
				// rootWebElement.findElements(By.xpath(".//*"));
				for (WebElement element : memberIDDropdownElements) {
					System.out.println("Member ID " + element.getText());
					if (element.getText().contains(memberID)) {

						element.click();
						// driver.findElement(photoLink).click();

						System.out.println("Click happened");
						break;
					}
				}
				record();
				if (driver.findElement(this.accountNumber).getText()
						.contains("-- Select Account Number --")) {
					driver.findElement(this.accountNumber).click();
					List<WebElement> accountNumberDropdownElements = driver
							.findElement(accountNumberDropdown).findElements(
									By.xpath(".//li"));
					// List<WebElement> childs =
					// rootWebElement.findElements(By.xpath(".//*"));
					for (WebElement element : accountNumberDropdownElements) {
						System.out.println("Account number "
								+ element.getText());
						if (element.getText().contains(accountNumber)) {

							element.click();
							// driver.findElement(photoLink).click();

							System.out.println("Click happened account number");
							break;
						}
					}
					// driver.navigate().back();
					record();
				}

			} else if (driver.findElement(this.accountNumber).getText()
					.contains("-- Select Account Number --")) {
				driver.findElement(this.accountNumber).click();
				List<WebElement> accountNumberDropdownElements = driver
						.findElement(accountNumberDropdown).findElements(
								By.xpath(".//li"));
				// List<WebElement> childs =
				// rootWebElement.findElements(By.xpath(".//*"));
				for (WebElement element : accountNumberDropdownElements) {
					System.out.println("Account number " + element.getText());
					if (element.getText().contains(accountNumber)) {

						element.click();
						// driver.findElement(photoLink).click();

						System.out.println("Click happened account number");
						break;
					}
				}
				
				
				
			}

		} catch (Exception e) {
			System.out.println("Exception");
		}

	}

	public void record(){
		
		System.out.println("Recording");
		System.out.println(driver.findElement(particulars).getAttribute("placeholder"));
		partic=driver.findElement(particulars).getText();
		hn=driver.findElement(holderName).getText();
		odb=driver.findElement(odBalance).getText();
		odi=driver.findElement(odInt).getText();
		clear=driver.findElement(clearing).getText();
		tb=driver.findElement(totalBalance).getText();
		moo=driver.findElement(modeOfOperation).getText();
		tok=driver.findElement(token).getText();
		
		
		System.out.println("Initial values :- ");
		System.out.println(hn);
		System.out.println(odb);
		System.out.println(odi);
		System.out.println(clear);
		System.out.println(tb);
		System.out.println(moo);
		System.out.println(tok);
		System.out.println(partic);
	}
	
	public void performSingleAccountTransaction(String memberID,
			String accountNumber, String transactionType,
			String instrumentType, String transactionCode, String amount) {
		fillSingleAccountForm(memberID, accountNumber, transactionType,
				instrumentType, transactionCode, amount);

	}

}
