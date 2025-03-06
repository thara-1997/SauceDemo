package com.pragmatic.SauceDemo.test;

import com.pragmatic.SauceDemo.base.BaseTest;
import com.pragmatic.SauceDemo.pages.ProductListPage;
import com.pragmatic.SauceDemo.pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.text.Format;

public class ProductListTest extends BaseTest {

    @Test
    public void testVerifyProductPage(){
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.goToSauceLabsBackPack();

        ProductPage productPage = new ProductPage(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(productPage.getProductName(),"Sauce Labs Backpack", "Product name is not matching");
        softAssert.assertEquals(productPage.getProductPrice(),"$29.99", "Price is not matching");
        softAssert.assertAll();
    }

    @Test
    public void testClickAddToCartButtonForEachProduct(){
        ProductListPage productListPage = new ProductListPage(driver);
        for (WebElement addToCartButton: productListPage.clickCartButtons()){
            addToCartButton.click();
        }
        Assert.assertEquals(productListPage.getCartNum(),"6" ,"Cart Item count is not matching");
    }

    @Test
    public void testRemoveButtonFunctionality(){
        ProductListPage productListPage = new ProductListPage(driver);
        for (WebElement addToCartButton: productListPage.clickCartButtons()){
            addToCartButton.click();
        }

        for(WebElement removeBtn: productListPage.clickRemoveBtn()){
            removeBtn.click();
        }

        for (WebElement product: productListPage.getAllProductDetails()){
            Assert.assertTrue(product.getText().contains("Add to cart"),"validation is not working");
        }
    }

   @Test
   public void testFilterProductsAtoZ(){
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.selectFilter("az");

        String previousProduct = "";
        for (WebElement product: productListPage.getProductDetails()){
            String currentProduct = product.getText();
            Assert.assertTrue(currentProduct.compareTo(previousProduct)>=0,"Products are not sorted in ascending order (A to Z). Current: "
            +currentProduct+ "Previous product: " +previousProduct);
            previousProduct=currentProduct;
        }
   }

   @Test
    public void testFilterProductsZtoA(){
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.selectFilter("za");

        String previousProduct = productListPage.getProductDetails().get(0).getText();
        for (WebElement product: productListPage.getProductDetails()){
            String currentProduct = product.getText();
            Assert.assertTrue(currentProduct.compareTo(previousProduct)<=0, "Products are not sorted in descending order (Z to A). Current: "
            +currentProduct+ "Previous product: " +previousProduct);
            previousProduct = currentProduct;
        }

   }

    @Test
    public void testFilterPriceLowToHigh() {
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.selectFilter("lohi");

        double previousValue = 0.0;
        for (WebElement price : productListPage.getPriceList()) {
            String priceText = price.getText().replace("$", "").trim();
            double currentValue = Double.parseDouble(priceText);
            Assert.assertTrue(currentValue >= previousValue, "Products are not sorted in ascending order (low to high) Current Price: " + currentValue +
                    "previous: " + previousValue);
            previousValue = currentValue;
        }
    }
    @Test
    public void testFilterPriceHighToLow(){
            ProductListPage productListPage = new ProductListPage(driver);
            productListPage.selectFilter("hilo");

            double previousValue = Double.MAX_VALUE;
            for (WebElement price: productListPage.getPriceList()){
                String priceText = price.getText().replace("$","").trim();
                double currentValue = Double.parseDouble(priceText);
                Assert.assertTrue(currentValue<=previousValue, "Products are not sorted in descending order (high to low). Current Price: " +currentValue+
                        "previous: " +previousValue);
                previousValue = currentValue;
            }
        }


}
