package com.mycompany.app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.CommonPage;

public class APRValidationTest {
    private WebDriver driver;
    private CommonPage commonPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver-mac-x64/chromedriver");
        driver = new ChromeDriver();
        commonPage = new CommonPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void validateAPR() {

        commonPage.goToURL("https://pnfp.myapexcard.com/info");

        commonPage.clickBusinessCategory();

        commonPage.clickTermsAndConditionsLink();

        commonPage.assertAPR(24.0);
    }
}
