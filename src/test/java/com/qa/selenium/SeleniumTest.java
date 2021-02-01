package com.qa.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SeleniumTest 
{
	private static WebDriver driver;
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	private static ExtentReports extent;
	private static ExtentTest test;
	@BeforeAll
	public static void init()
	{
		extent = new ExtentReports("target/reports/report.html");
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
	}
	
	@AfterEach
	public void postTest() 
	{
		extent.endTest(test);
	}
	
	@AfterAll
	public static void cleanUp() 
	{
		driver.quit();
		extent.close();
	}
	
	// PLAYER TEST
	@Test
	public void testPlayerCreate() throws InterruptedException
	{
		test = extent.startTest("Player Create");
		IndexPage website = PageFactory.initElements(driver, IndexPage.class);
		website.navPlayer();
		Thread.sleep(1500);
		
		website.player.create("Mark", "Maryne", "1");
		Thread.sleep(1500);
		boolean result = website.player.createDisplay.getText().contains("Maryne has been added!");
		assertTrue(result);
		if (result == true)
		{
			test.log(LogStatus.PASS, "Player has been created!");
		}
		else
		{
			test.log(LogStatus.FAIL, "Failed to create player!");
		}
	}
	
	@Test
	public void testViewPlayer() throws InterruptedException 
	{
		test = extent.startTest("View Player");
		IndexPage website = PageFactory.initElements(driver, IndexPage.class);
		website.navPlayer();
		Thread.sleep(1500);

		website.player.readPlayer("2");
		Thread.sleep(1500);
		String expected = "Player ID: 2, Player Name: Noah, Player IGN: 963noah";
		String actual = website.player.viewPlayerDisplay.getText();
		assertEquals(expected, actual);
		if (expected == actual)
		{
			test.log(LogStatus.PASS, "Successfully read the player!");
		}
		else
		{
			test.log(LogStatus.FAIL, "Failed to read player!");
		}
	}

	@Test
	public void testViewAllPlayer() throws InterruptedException 
	{
		test = extent.startTest("View All Player");
		IndexPage website = PageFactory.initElements(driver, IndexPage.class);
		website.navPlayer();
		jse.executeScript("arguments[0].scrollIntoView()", website.player.viewAllBtn); 
		Thread.sleep(1500);
		
		website.player.readAllPlayer();
		Thread.sleep(1500);
		boolean result = website.player.viewAllDisplay.getText().contains("Player ID: 4, Player Name: Aqua, Player IGN: Aqukinn");
		assertTrue(result);
		if (result == true)
		{
			test.log(LogStatus.PASS, "Succesfully read all players!");
		}
		else
		{
			test.log(LogStatus.FAIL, "Failed to read all players!");
		}
	}
	
	@Test
	public void testUpdatePlayer() throws InterruptedException
	{
		test = extent.startTest("Update Player");
		IndexPage website = PageFactory.initElements(driver, IndexPage.class);
		website.navPlayer();
		jse.executeScript("arguments[0].scrollIntoView()", website.player.updateBtn); 
		Thread.sleep(1500);
		
		website.player.updatePlayer("1", "Fubuki", "FBK", "1");
		Thread.sleep(1500);
		String expected = "Player has been successfully updated!";
		String actual = website.player.updateDisplay.getText();
		assertEquals(expected, actual);
		if (expected == actual)
		{
			test.log(LogStatus.PASS, "Successfully updated player!");
		}
		else
		{
			test.log(LogStatus.FAIL, "Failed to update player!");
		}
	}
	
	@Test
	public void testDeletePlayer() throws InterruptedException //Always deletes the newest player created
	{
		test = extent.startTest("Delete Player");
		IndexPage website = PageFactory.initElements(driver, IndexPage.class);
		website.navPlayer();
		jse.executeScript("arguments[0].scrollIntoView()", website.player.deleteBtn); 
		Thread.sleep(1500);
		
		
		website.player.deletePlayer("3");
		Thread.sleep(1500);
		String expected = "Player Deleted!";
		String actual = website.player.deleteDisplay.getText();
		assertEquals(expected, actual);
		if (expected == actual)
		{
			test.log(LogStatus.PASS, "Successfully deleted player!");
		}
		else
		{
			test.log(LogStatus.FAIL, "Failed to delete player!");
		}
	}
	
	// TEAM TEST
	@Test
	public void testTeamCreate() throws InterruptedException
	{
		IndexPage website = PageFactory.initElements(driver, IndexPage.class);
		website.navTeam();
		Thread.sleep(1500);
		
		website.team.create("TMS");
		Thread.sleep(1500);
		assertTrue(website.team.createDisplay.getText().contains("TMS has been created!"));
		
	}
	
	@Test
	public void testViewTeam() throws InterruptedException 
	{
		IndexPage website = PageFactory.initElements(driver, IndexPage.class);
		website.navTeam();

		website.team.readTeam("3");
		Thread.sleep(1500);
		assertTrue(website.team.viewTeamDisplay.getText().contains("Holo"));
	}

	@Test
	public void testViewAllTeam() throws InterruptedException 
	{
		IndexPage website = PageFactory.initElements(driver, IndexPage.class);
		website.navTeam();
		jse.executeScript("arguments[0].scrollIntoView()", website.team.viewAllBtn); 
		Thread.sleep(1500);
		
		website.team.readAllTeam();
		Thread.sleep(1500);
		assertTrue(website.team.viewAllDisplay.getText().contains("Team Name: Holo"));
	}
	
	@Test
	public void testUpdateTeam() throws InterruptedException 
	{
		IndexPage website = PageFactory.initElements(driver, IndexPage.class);
		website.navTeam();
		jse.executeScript("arguments[0].scrollIntoView()", website.team.updateBtn); 
		Thread.sleep(1500);
		
		
		website.team.updateTeam("1", "BAM");
		Thread.sleep(1500);
		
		assertEquals("Team has successfully been updated!", website.team.updateDisplay.getText());
	}
	
	@Test
	public void testDeleteTeam() throws InterruptedException 
	{
		IndexPage website = PageFactory.initElements(driver, IndexPage.class);
		website.navTeam();
		jse.executeScript("arguments[0].scrollIntoView()", website.team.deleteBtn); 
		Thread.sleep(1500);
		
		website.team.deleteTeam("2");
		Thread.sleep(1500);
		assertEquals("Team has been deleted!", website.team.deleteDisplay.getText());
	}
	

}
