package com.pragmatic.SauceDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

    private final WebDriver driver;

    private final By byItemDescription = By.xpath("//div[@class='inventory_item_desc']");
    private final By byCartItems = By.xpath("//div[@class='inventory_item_name']");

    @FindBy(id = "remove-sauce-labs-backpack")
    WebElement removeBackPack;

    @FindBy(id = "continue-shopping")
    WebElement continueBtn;

    @FindBy(id = "checkout" )
    WebElement checkoutBtn;

    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

   public String getItemDescription(){
        return driver.findElement(byItemDescription).getText();
   }

   public List<WebElement> getCartItems(){
        return driver.findElements(byCartItems);
   }

   public void clickRemoveSauceLabs(){
        removeBackPack.click();
   }

   public int getCartItemsCount(){
        return driver.findElements(byCartItems).size();
   }

   public void clickContinueShopping(){
        continueBtn.click();
   }

   public void clickCheckout(){
        checkoutBtn.click();
   }
}
