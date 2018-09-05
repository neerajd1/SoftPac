package scripts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Login {

	WebDriver driver;
	LoginPOM pom = null;

	@BeforeTest
	public void setUp() {

		pom = new LoginPOM(driver, "chrome");

	}

	@Test
	public void login() {
		/*driver= */pom.login();

	}

	@Test
	public void logout(){
		
		/*driver=*/ pom.logout();
		
	}
	
	
	@AfterTest
	public void breakdown() {
		
		//driver.close();

	}

}
