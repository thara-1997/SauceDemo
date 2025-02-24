package com.pragmatic.SauceDemo.test;

import com.pragmatic.SauceDemo.base.BaseTest;
import com.pragmatic.SauceDemo.pages.CartPage;
import com.pragmatic.SauceDemo.pages.ProductListPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class CartTest extends BaseTest {

   @Test
    public void testVerifyCorrectProductDisplaying(){
       ProductListPage productListPage = new ProductListPage(driver);
       productListPage.addSauceLabsBackPack();

       CartPage cartPage = new CartPage(driver);
       Assert.assertEquals(cartPage.getItemDescription(),"carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
               "Item Description is not matching");
   }

    public void addProductsToCart(){
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.addSauceLabsBackPack();
        productListPage.addSauceLabsBikeLight();
        productListPage.clickCart();
    }

   @Test
   public void testVerifyMultipleProductsInCart() {
       addProductsToCart();

       CartPage cartPage = new CartPage(driver);
       List<String> expectedCartItems = Arrays.asList("Sauce Labs Backpack","Sauce Labs Bike Light");
       for (int i =0; i<cartPage.getCartItemsCount(); i++){
           String cartItemName = cartPage.getCartItems().get(i).getText();
           String expectedProductName = expectedCartItems.get(i);
           Assert.assertEquals(cartItemName,expectedProductName,"Cart Items are not matching");
       }
   }

   @Test
   public void testVerifyRemoveCartItemFunctionality(){
       addProductsToCart();

       CartPage cartPage =  new CartPage(driver);
       cartPage.clickRemoveSauceLabs();
       Assert.assertEquals(cartPage.getCartItemsCount(),1,"Cart Item count is not matching");
   }

   @Test
    public void testVerifyContinueShoppingButtonFunctionality(){
       addProductsToCart();

       CartPage cartPage = new CartPage(driver);
       cartPage.clickContinueShopping();
       Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/inventory.html"));
   }

   @Test
    public void testVerifyCheckoutFunctionality(){
       addProductsToCart();

       CartPage cartPage = new CartPage(driver);
       cartPage.clickCheckout();
       Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/checkout-step-one.html"));
   }
}
