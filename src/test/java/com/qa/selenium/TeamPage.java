package com.qa.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TeamPage 
{
	
	private static WebDriver driver;
		//CREATE Elements
		@FindBy(id = "teamName")
		private WebElement teamNameForm;
		@FindBy(id = "createBtn")
		private WebElement createBtn;
		
		//READ
		@FindBy(id = "viewTeamID")
		private WebElement viewPlayerIDForm;
		@FindBy(id = "viewTeamBtn")
		private WebElement viewTeamBtn;
		@FindBy(id = "viewAllBtn")
		public WebElement viewAllBtn;
		
		//UPDATE
		@FindBy(id = "updateID")
		private WebElement updateIDForm;
		@FindBy(id = "updateName")
		private WebElement updateNameForm;
		@FindBy(id = "updateBtn")
		public WebElement updateBtn;
		
		//DELETE
		@FindBy(id = "deleteID")
		private WebElement deleteIDForm;
		@FindBy(id = "deleteBtn")
		public WebElement deleteBtn;
		
		
		//DISPLAYS
		public WebElement createDisplay;
		public WebElement viewTeamDisplay;
		public WebElement viewAllDisplay;
		public WebElement updateDisplay;
		public WebElement deleteDisplay;
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		
		
		public void create(String teamName)
		{
			teamNameForm.sendKeys(teamName);
			createBtn.click();
		}
		
		public void readTeam(String id)
		{
			viewPlayerIDForm.sendKeys(id);
			viewTeamBtn.click();
		}
		
		public void readAllTeam()
		{
			viewAllBtn.click();
		}
		
		public void updateTeam(String teamID, String teamName)
		{
			updateIDForm.sendKeys(teamID);
			updateNameForm.sendKeys(teamName);
			updateBtn.click();
		}
		
		public void deleteTeam(String id)
		{
			deleteIDForm.sendKeys(id);
			deleteBtn.click();
		}
}
