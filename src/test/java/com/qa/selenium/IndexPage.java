package com.qa.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage 
{
	@FindBy(xpath = "/html/body/nav/a")
	private WebElement indexLink;
	
	@FindBy(xpath = "/html/body/div/div/div/div/a[1]")
	private WebElement playerBtn;
	
	@FindBy(xpath = "/html/body/div/div/div/div/a[2]")
	private WebElement teamBtn;
	
	public PlayerPage player;
	public TeamPage team;
	
	public IndexPage(WebDriver driver) 
	{
		driver.get("http://localhost:8080/index.html");
		player = PageFactory.initElements(driver, PlayerPage.class);
		team = PageFactory.initElements(driver, TeamPage.class);
	}
	
	public void navIndex() {
		indexLink.click();
	}
	
	public void navPlayer() {
		playerBtn.click();
	}
	
	public void navTeam() {
		teamBtn.click();
	}
}
