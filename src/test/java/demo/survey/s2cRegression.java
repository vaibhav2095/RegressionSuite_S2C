package demo.survey;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class s2cRegression {
	ManageOperations operation;
	ManageBrowser browser;
	ManageOR objects;
	ModifyOperations modify;

	@BeforeSuite
	public void initialiseAll() throws IOException {
		operation = new ManageOperations();
		browser = new ManageBrowser();
		objects = new ManageOR();
		modify = new ModifyOperations();
	}

	@BeforeMethod
	public void launch() throws InterruptedException {
		operation.launchURL("https://survey2connect.com/S/2d3c462a");
		browser.pause(2000);
	}

	@Test
	public void checkTitle() {
		String title = "Test Survey";
		System.out.println("here");
		System.out.println(operation.getTitle());
		Assert.assertEquals(operation.getTitle(), title, "Title is okay");
	}

	@DataProvider(name = "PositiveValidation")
	public Object[][] getDataforQuestions1() {
		return new Object[][] { { "8", "", "Jaipur" }, { "4", "not satisfied", "Delhi" } };
	}

	@Test(dataProvider = "PositiveValidation")
	public void submitFormPositive(String a1, String a2, String a3) throws InterruptedException {
		String progress = "";
		String xpath = objects.getProperty("ans1");
		String updatedXpath = modify.getDynamicXPath(xpath, a1);
		operation.click(updatedXpath);
		if (!a2.equalsIgnoreCase("")) {
			operation.setText(objects.getProperty("ans2"), a2);
			operation.click(objects.getProperty("ok"));
			progress = operation.getText(objects.getProperty("progress"));
			Assert.assertTrue(progress.contains("67"), "Progress recorded successfully");
		}
		xpath = objects.getProperty("ans3");
		updatedXpath = modify.getDynamicXPath(xpath, "Jaipur");
		operation.click(updatedXpath);
		progress = operation.getText(objects.getProperty("progress"));
		Assert.assertTrue(progress.contains("100"), "Progress recorded successfully");
		operation.click(objects.getProperty("done"));
		String message = operation.getText(objects.getProperty("thanks"));
		String success = "Thank you for taking the survey.";
		Assert.assertEquals(success, message, "Form has been submitted successfully");
	}

	@DataProvider(name = "NegativeValidation")
	public Object[][] getDataforQuestions2() {
		return new Object[][] { { "", "", "" }, { "4", "", "" }, { "4", "not satisfied", "" } };
	}

	@Test(dataProvider = "NegativeValidation")
	public void submitFormNegative(String a1, String a2, String a3) throws InterruptedException {
		String errorMsg = "This question requires an answer";
		if (a1.equalsIgnoreCase("")) {
			operation.click(objects.getProperty("next"));
		} else if (a2.equalsIgnoreCase("")) {
			String xpath = objects.getProperty("ans1");
			String updatedXpath = modify.getDynamicXPath(xpath, a1);
			operation.click(updatedXpath);
			operation.click(objects.getProperty("next"));
		} else if (a3.equalsIgnoreCase("")) {
			String xpath = objects.getProperty("ans1");
			String updatedXpath = modify.getDynamicXPath(xpath, a1);
			operation.click(updatedXpath);
			operation.setText(objects.getProperty("ans2"), a2);
			operation.click(objects.getProperty("ok"));
			operation.click(objects.getProperty("done"));
		}

		String text = operation.getText(objects.getProperty("error"));
		Assert.assertEquals(text, errorMsg, "Error message validated successfully");

	}

	@Test
	public void checkPreviousButtton() throws InterruptedException {
		String xpath = objects.getProperty("ans1");
		String updatedXpath = modify.getDynamicXPath(xpath, "2");
		operation.click(updatedXpath);
		operation.click(objects.getProperty("prev"));
		operation.isElementDisplayed(updatedXpath);
	}

	@AfterSuite
	public void teardown() {
		browser.teardown();
	}

}
