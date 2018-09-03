package scripts;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class LoginPOM {

	String getURL = "http://205.147.102.59:8080/SoftPac/login";

	By usernameText = By.id("user_name"), passwordText = By.id("password"),
			submit = By.className("submit");

	String username = "beta1", password = "beta@123";

	WebDriver driver;

	public LoginPOM(WebDriver driver, String browser) {

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
	}

	public WebDriver login() {

		try {
			driver = goToLink();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		driver.findElement(usernameText).sendKeys(username);
		
		driver.findElement(passwordText).sendKeys(password);
		
		driver.findElement(submit).click();
		
		return driver;

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

	public WebDriver goToLink() throws InterruptedException {

		driver.manage().window().maximize();
		driver.get(getURL);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		return driver;
	}

}
