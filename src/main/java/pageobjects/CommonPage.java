package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class CommonPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CommonPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 3); 
    }

    public void goToURL(String url) {
        driver.get(url);
    }

    public void clickBusinessCategory() {
        WebElement businessCardInfo = wait.until(ExpectedConditions.elementToBeClickable(By.className("productSelector_category_business")));
        businessCardInfo.click();
    }

    public void clickTermsAndConditionsLink() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"div.productSelector_cardRow_applyTCContainer_tc a.clickableLink[href='#p=application/terms']\").click();");

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public String getAPR() {
        WebDriverWait pause = new WebDriverWait(driver, 3);
        WebElement row = pause.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table//tr[4]")));

        String APRString = row.findElement(By.xpath(".//td[2]")).getText();
        System.out.println("APR: " + APRString);

        return APRString;
    }

    public void assertAPR(double expectedAPR) {
        String APRString = getAPR();
        double APR = Double.parseDouble(APRString.replace("%", ""));
        assert (APR < expectedAPR) : "APR is not less than " + expectedAPR + "%";
    }
}
