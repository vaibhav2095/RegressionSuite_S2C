package demo.survey;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManageBrowser {
	static WebDriver driver = null;

	public WebDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}

	public WebDriver getIEDriver() {
		return driver;
	}

	public void teardown() {
		driver.quit();
	}

	public void pause(int milliseconds) throws InterruptedException {
		Thread.sleep(milliseconds);
	}
	
	public void waitExplicit(String locator) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	}

}
