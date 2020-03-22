package demo.survey;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManageOperations {
	ManageBrowser browser = new ManageBrowser();
	WebDriver driver = browser.getChromeDriver();

	public void launchURL(String url) {
		driver.get(url);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void click(String locator) throws InterruptedException {
		browser.waitExplicit(locator);
		driver.findElement(By.xpath(locator)).click();

	}

	public String getText(String locator) throws InterruptedException {
		browser.waitExplicit(locator);
		return driver.findElement(By.xpath(locator)).getText();
	}

	public void setText(String locator, String value) throws InterruptedException {
		browser.waitExplicit(locator);
		driver.findElement(By.xpath(locator)).sendKeys(value);
	}

	public boolean isElementDisplayed(String locator) {
		return driver.findElement(By.xpath(locator)).isDisplayed();
	}
}
