package com.pragmatic.SauceDemo.util;

import org.testng.annotations.DataProvider;

public class DataDrivenProvider {

    @DataProvider(name ="login-credentials")
    public Object[][] userCredentials() {
        return new Object[][]{
                {"", "", "Epic sadface: Username is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"standard_user", "invalid", "Epic sadface: Username and password do not match any user in this service"}

        };
    }

    @DataProvider(name ="information-details")
    public Object[][] checkoutDetails(){
        return new Object[][]{
                {"","","","Error: First Name is required"} ,
                {"Thathsarani", "","","Error: Last Name is required"},
                {"Thathsarani","Sudusinghe","","Error: Postal Code is required"},
                {"Thathsarani", "", "82100", "Error: Last Name is required"}

        };
    }
}
