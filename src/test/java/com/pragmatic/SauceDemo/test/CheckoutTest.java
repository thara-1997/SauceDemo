package com.pragmatic.SauceDemo.test;

import com.pragmatic.SauceDemo.base.BaseTest;
import com.pragmatic.SauceDemo.pages.*;
import com.pragmatic.SauceDemo.util.DataDrivenProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class CheckoutTest extends BaseTest {

    public void addProducts(){
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.addSauceLabsBackPack();
        productListPage.addSauceLabsBikeLight();
        productListPage.clickCart();

    }
    public void clickCheckoutBtn(){
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
    }

    public void enterCheckoutDetails(){
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.typeFirstName("firstname").typeLastName("lastName").typePostalCode("postalCode").clickContinueBtn();
    }

    @Test(dataProvider = "information-details", dataProviderClass = DataDrivenProvider.class)
    public void testVerifyInvalidCheckoutInformationFunctionality(String firstname, String lastName, String postalCode, String expectedMessage) {
        addProducts();
        clickCheckoutBtn();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.typeFirstName(firstname).typeLastName(lastName).typePostalCode(postalCode).clickContinueBtn();
        Assert.assertEquals(checkoutPage.getErrorMsg(),expectedMessage,"Error message is incorrect");
    }

    @Test
    public void testVerifyValidCheckoutInformationFunctionality() {
        addProducts();
        clickCheckoutBtn();
        enterCheckoutDetails();

        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/checkout-step-two.html"));
    }

    @Test
    public void estVerifyOrderSummaryPage(){
        addProducts();
        clickCheckoutBtn();
        enterCheckoutDetails();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        List<String> expectedCartItems = Arrays.asList("Sauce Labs Backpack","Sauce Labs Bike Light");
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i< checkoutOverviewPage.getCartItemCount(); i++){
            String cartItemName = checkoutOverviewPage.getCartItems().get(i).getText();
            String expectedProductName = expectedCartItems.get(i);
            softAssert.assertEquals(cartItemName, expectedProductName, "Cart Items are not matching");
        }

        softAssert.assertEquals(checkoutOverviewPage.getTotal(),"Total: $43.18", "Total Value is not matching");
        softAssert.assertAll();
    }

    @Test
    public void testVerifyFinishButtonFunctionality(){
        addProducts();
        clickCheckoutBtn();
        enterCheckoutDetails();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutOverviewPage.clickFinishBtn();

        FinishMessagePage finishMessagePage = new FinishMessagePage(driver);
        Assert.assertEquals(finishMessagePage.getFinishMsg(), "Thank you for your order!", "Thank you for your order!");
    }

    @Test
    public void testVerifyCancelButtonFunctionality(){
        addProducts();
        clickCheckoutBtn();

        CheckoutPage checkoutPage =  new CheckoutPage(driver);
        checkoutPage.clickCancelBtn();
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/cart.html"));
    }

}
