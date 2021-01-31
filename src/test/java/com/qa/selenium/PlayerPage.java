package com.qa.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PlayerPage 
{
	
	
	//CREATE Elements
	@FindBy(id = "playerName")
	private WebElement playerNameForm;
	@FindBy(id = "playerIGN")
	private WebElement playerIGNForm;
	@FindBy(id = "teamID")
	private WebElement teamIDForm;
	@FindBy(id = "createBtn")
	private WebElement createBtn;
	
	//READ
	@FindBy(id = "viewPlayerID")
	private WebElement viewPlayerIDForm;
	@FindBy(id = "viewPlayerBtn")
	private WebElement viewPlayerBtn;
	@FindBy(id = "viewAllBtn")
	public WebElement viewAllBtn;
	
	//UPDATE
	@FindBy(id = "updateID")
	private WebElement updateIDForm;
	@FindBy(id = "updateName")
	private WebElement updateNameForm;
	@FindBy(id = "updateIGN")
	private WebElement updateIGNForm;
	@FindBy(id = "updateTeamID")
	private WebElement updateTeamIDForm;
	@FindBy(id = "updateBtn")
	public WebElement updateBtn;
	
	//DELETE
	@FindBy(id = "deleteID")
	private WebElement deleteIDForm;
	@FindBy(xpath = "/html/body/div/div[5]/div[2]/div/button")
	public WebElement deleteBtn;
	
	
	//DISPLAYS
	public WebElement createDisplay;
	public WebElement viewPlayerDisplay;
	public WebElement viewAllDisplay;
	public WebElement updateDisplay;
	public WebElement deleteDisplay;
	
	
	public void create(String playerName, String playerIGN, String teamID)
	{
		playerNameForm.sendKeys(playerName);
		playerIGNForm.sendKeys(playerIGN);
		teamIDForm.sendKeys(teamID);
		createBtn.click();
	}
	
	public void readPlayer(String id)
	{
		viewPlayerIDForm.sendKeys(id);
		viewPlayerBtn.click();
	}
	
	public void readAllPlayer()
	{
		viewAllBtn.click();
	}
	
	public void updatePlayer(String playerID, String playerName, String playerIGN, String teamID) throws InterruptedException
	{
		updateIDForm.sendKeys(playerID);
		updateNameForm.sendKeys(playerName);
		updateIGNForm.sendKeys(playerIGN);
		updateTeamIDForm.sendKeys(teamID);
		Thread.sleep(500);
		updateBtn.click();
	}
	
	public void deletePlayer(String id) throws InterruptedException
	{
		deleteIDForm.sendKeys(id);
		Thread.sleep(500);
		deleteBtn.click();
	}
}
