package com.pragmatic.SauceDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutOverviewPage {
    private final WebDriver driver;

    @FindBy(id = "finish")
    WebElement finishBtn;

    private final By byCartItems = By.xpath("//div[@class='inventory_item_name']");
    private final By byTotal = By.xpath("//div[@class='summary_total_label']");

    public CheckoutOverviewPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickFinishBtn(){
        finishBtn.click();
    }

    public List<WebElement> getCartItems(){
        return driver.findElements(byCartItems);
    }

    public int getCartItemCount(){
        return driver.findElements(byCartItems).size();
    }

    public String getTotal(){
        return driver.findElement(byTotal).getText();
    }
}
