package com.pragmatic.SauceDemo.steps;

import com.pragmatic.SauceDemo.pages.LoginPage;
import com.pragmatic.SauceDemo.pages.ProductListPage;
import com.pragmatic.SauceDemo.pages.ProductPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class ProductSteps {
    WebDriver driver;
    @Before
    public void before(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }
    @After
    public void after(Scenario scenario){
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "name");
        }
        driver.quit();
    }
    @Given("user has logged with valid credentials")
    public void userHasLoggedWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername("standard_user");
        loginPage.typePassword("secret_sauce");
        loginPage.clickLoginBtn();
    }

    @When("user has accessed the product")
    public void userHasAccessedTheProduct() {
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.goToSauceLabsBackPack();
    }

    @Then("user should be directed to the product page")
    public void userShouldBeDirectedToTheProductPage() {
        ProductPage productPage = new ProductPage(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(productPage.getProductName(),"Sauce Labs Backpack", "Product name is not matching");
        softAssert.assertEquals(productPage.getProductPrice(),"$29.99", "Price is not matching");
        softAssert.assertAll();
    }

    @When("click add to cart button in each product")
    public void clickAddToCartButtonInEachProduct() {
        ProductListPage productListPage = new ProductListPage(driver);
        for (WebElement addToCartButton: productListPage.clickCartButtons()){
            addToCartButton.click();
        }
    }

    @Then("cart item count should need to increase")
    public void cartItemCountShouldNeedToIncrease() {
        ProductListPage productListPage = new ProductListPage(driver);
        Assert.assertEquals(productListPage.getCartNum(),"6" ,"Cart Item count is not matching");
    }

    @And("click remove button in each product")
    public void clickRemoveButtonInEachProduct() {
        ProductListPage productListPage = new ProductListPage(driver);
        for(WebElement removeBtn: productListPage.clickRemoveBtn()){
            removeBtn.click();
        }
    }

    @Then("all buttons should need to display with AddToCart")
    public void allButtonsShouldNeedToDisplayWithAddToCart() {
        ProductListPage productListPage = new ProductListPage(driver);
        for (WebElement product: productListPage.getAllProductDetails()){
            Assert.assertTrue(product.getText().contains("Add to cart"),"validation is not working");
        }
    }

    @When("select the product filter {string}")
    public void selectTheProductFiler(String filter) {
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.selectFilter(filter);
    }

    @Then("user should able to see filtered AToZ products")
    public void userShouldAbleToSeeFilteredAToZProducts() {
        ProductListPage productListPage = new ProductListPage(driver);
        String previousProduct = "";
        for (WebElement product: productListPage.getProductDetails()){
            String currentProduct = product.getText();
            Assert.assertTrue(currentProduct.compareTo(previousProduct)>=0,"Products are not sorted in ascending order (A to Z). Current: "
                    +currentProduct+ "Previous product: " +previousProduct);
            previousProduct=currentProduct;
        }
    }

    @When("select the product filter to {string}")
    public void selectTheProductFilerTo(String filter) {
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.selectFilter("za");
    }


    @Then("user should able to see filtered ZToA products")
    public void userShouldAbleToSeeFilteredZToAProducts() {
        ProductListPage productListPage = new ProductListPage(driver);
        String previousProduct = productListPage.getProductDetails().get(0).getText();
        for (WebElement product: productListPage.getProductDetails()){
            String currentProduct = product.getText();
            Assert.assertTrue(currentProduct.compareTo(previousProduct)<=0, "Products are not sorted in descending order (Z to A). Current: "
                    +currentProduct+ "Previous product: " +previousProduct);
            previousProduct = currentProduct;
        }
    }

    @When("select the product filter in to {string}")
    public void selectTheProductFilerInTo(String filter) {
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.selectFilter("hilo");
    }

    @Then("user should able to see filtered HighToLow products")
    public void userShouldAbleToSeeFilteredHighToLowProducts() {
        ProductListPage productListPage = new ProductListPage(driver);
        double previousValue = Double.MAX_VALUE;
        for (WebElement price: productListPage.getPriceList()){
            String priceText = price.getText().replace("$","").trim();
            double currentValue = Double.parseDouble(priceText);
            Assert.assertTrue(currentValue<=previousValue, "Products are not sorted in descending order (high to low). Current Price: " +currentValue+
                    "previous: " +previousValue);
            previousValue = currentValue;
        }
    }

    @When("select the product filter in to the {string}")
    public void selectTheProductFilterInToThe(String filter) {
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.selectFilter("lohi");
    }

    @Then("user should able to see filtered LowToHigh products")
    public void userShouldAbleToSeeFilteredLowToHighProducts() {
        ProductListPage productListPage = new ProductListPage(driver);
        double previousValue = 0.0;
        for (WebElement price : productListPage.getPriceList()) {
            String priceText = price.getText().replace("$", "").trim();
            double currentValue = Double.parseDouble(priceText);
            Assert.assertTrue(currentValue >= previousValue, "Products are not sorted in ascending order (low to high) Current Price: " + currentValue +
                    "previous: " + previousValue);
            previousValue = currentValue;
        }
    }
}
