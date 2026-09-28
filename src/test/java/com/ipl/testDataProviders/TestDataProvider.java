package com.ipl.testDataProviders;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name="teamNamesDataProvider")
    public Object [][] teamNames(){
        return new Object[][]{
                {"Royal Challengers Bengaluru"},
                {"Mumbai Indians"}
        };
    }
}
