package com.pragmatic.SauceDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigationAndLogoutPage {

    private final WebDriver driver;

    @FindBy(id = "react-burger-menu-btn")
    WebElement burgerMenu;

    @FindBy(id = "logout_sidebar_link")
    WebElement logOut;

    @FindBy(id = "inventory_sidebar_link")
    WebElement allItems;

    @FindBy(id = "about_sidebar_link")
    WebElement about;

    private final By byMenuItem = By.xpath("//div[@class='bm-menu-wrap']");

    public NavigationAndLogoutPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void burgerMenuClick(){
        burgerMenu.click();
    }

    public boolean getMenu(){
        return driver.findElement(byMenuItem).isDisplayed();
    }

    public void clickLogout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(logOut));
        logOut.click();
    }

    public void clickAllItems(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(allItems));
        allItems.click();
    }

    public void clickAbout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(about));
        about.click();
    }

}
