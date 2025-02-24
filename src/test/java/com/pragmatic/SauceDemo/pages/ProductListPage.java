package com.pragmatic.SauceDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductListPage {

    private final WebDriver driver;

    @FindBy(css = "[data-test='title']")
    WebElement title;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    WebElement sauceLabsBackPackCartBtn;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    WebElement sauceLabsBikeLightCartBtn;

    private final By byClickCart = By.xpath("//a[@class='shopping_cart_link']");
    private final By bySauceLabsBackPack = By.xpath("//div[normalize-space()='Sauce Labs Backpack']");

    private final By byCartBtn = By.xpath("//button[@class='btn btn_primary btn_small btn_inventory ']");

    private final By byCartIconNum = By.xpath("//span[@class='shopping_cart_badge']");
    private final By byRemoveBtn = By.xpath("//button[@class='btn btn_secondary btn_small btn_inventory ']");

    private final By byAllProducts = By.xpath("//div[@class='inventory_item_description']");

    private final By bySelectFilter = By.xpath("//select[@class='product_sort_container']");
    private final By byProductList = By.xpath("//div[@class = 'inventory_item']");
    private final By byPriceList = By.xpath("//div[@class = 'inventory_item_price']");


    public ProductListPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle(){
        return title.getText();
    }

    public void addSauceLabsBackPack(){
        sauceLabsBackPackCartBtn.click();
    }

    public void addSauceLabsBikeLight(){
        sauceLabsBikeLightCartBtn.click();
    }

    public void clickCart(){
        driver.findElement(byClickCart).click();
    }

    public void goToSauceLabsBackPack(){
        driver.findElement(bySauceLabsBackPack).click();
    }
    public List<WebElement> clickCartButtons(){
        return driver.findElements(byCartBtn);
    }

    public String getCartNum(){
        return driver.findElement(byCartIconNum).getText();
    }

    public List<WebElement> clickRemoveBtn(){
        return driver.findElements(byRemoveBtn);
    }

    public List<WebElement> getAllProductDetails(){
        return driver.findElements(byAllProducts);
    }

    public ProductListPage selectFilter(String value){
        WebElement filter = driver.findElement(bySelectFilter);
        Select select = new Select(filter);
        select.selectByValue(value);
        return this;
    }

    public List<WebElement> getProductDetails(){
        return driver.findElements(byProductList);
    }
    public List<WebElement> getPriceList(){
        return driver.findElements(byPriceList);
    }
}
