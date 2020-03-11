package testcases;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageobjects.*;

import Utilities.SuiteBase;
import aes.reports.Reports;

import javafx.scene.control.Alert;

public class TC1_cleartrip_flight_book extends SuiteBase {
	aes.common.SoftAssert sa1 = new aes.common.SoftAssert();

	@Test
	public void TC1_cleartrip_flight_book() throws InterruptedException, IOException {

		try {

			Reports.intializeLoger(this.getClass().getName());
			Reports.addLogs("info", "Staffing is opening fine");
			aes.common.SoftAssert sa = new aes.common.SoftAssert();
			Home_page HP = new Home_page();

			waitForElementVisible("xpath", HP.round_trip_);
			waitForElementClickable("xpath", HP.round_trip_);

			Reports.addLogs("info", "click on round trip ");
			findElement("xpath", HP.round_trip_).click();
			findElement("xpath", HP.From_textbox).sendKeys(Param.getProperty("From"));
			findElement("xpath", HP.To_textbox).sendKeys(Param.getProperty("To"));

			findElement("xpath", HP.fromdate_picker).click();
			findElement("xpath", HP.depart_date).click();
			waitForElementVisible("xpath", HP.return_date);
			findElement("xpath", HP.return_date).click();
			findElement("xpath", HP.search_flight_BTN).click();

			// This will print the logtime in console |function is created in
			// suitebase
			logtime();

			waitForElementVisible("xpath", HP.oneStop_xpath);
			waitForElementClickable("xpath", HP.oneStop_xpath);
			findElement("xpath", HP.oneStop_xpath).click();
			findElement("xpath", HP.zeroStop).click();
			jsClick(findElement("xapth", HP.Departure_time_earlyM));
			waitForElementClickable("xpath", HP.oneStop_xpath);
			findElement("xpath", "//div[@data-block-type='tripDuration']").click();
			findElement("xpath", "//div[@data-block-type='layoverDuration']").click();
			waitForElementClickable("xpath", HP.trip_duration_right_slider);
			WebElement slider = findElement("xpath", HP.trip_duration_right_slider);
			WebElement slider2 = findElement("xpath", HP.layover_right_slider);
			Actions action = new Actions(driver);

			mouseOver(slider);
			action.click(slider).build().perform();

			Thread.sleep(1000);
			for (int i = 0; i < 20; i++) {
				action.sendKeys(Keys.ARROW_LEFT).build().perform();
				Thread.sleep(200);
			}
			action.click(slider2).build().perform();
			for (int i = 0; i < 20; i++) {
				action.sendKeys(Keys.ARROW_LEFT).build().perform();
				Thread.sleep(200);
			}

			findElement("xpath", HP.Duration_click_return).click();
			findElement("xpath", HP.Book_button).click();
			
			
			waitForElementClickable("xpath", "//p[contains(@class,'pageTitle')]");
			sa.assertEquals(findElement("xpath", HP.title_booking_page).getText().trim(), "Book in four simple steps", "Booking not successful");

			sa.assertAll();
		} catch (Exception ex) {
			sa1.fail();
			Reports.addLogs("info", ex.toString());
			sa1.assertAll();
		}
	}
}
