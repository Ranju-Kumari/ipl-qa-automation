package com.ipl.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    @BeforeMethod
    public void setup(){

        String browser = "Chrome";
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get("https://www.iplt20.com/points-table");
            driver.manage().window().maximize();
        }
    }

    @AfterMethod
    public void teardown(){
        if(driver!=null){
            driver.quit();
        }
    }
}
