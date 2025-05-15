package src.main.main.java.com.automation.pages;

import com.automation.pages.CartPage;
import com.automation.pages.InventoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutStep2Page {
    WebDriver driver;
    WebDriverWait wait;

    By cancelBtn = By.id("cancel");

    public CheckoutStep2Page(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public com.automation.pages.InventoryPage clickCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();
        return new InventoryPage(driver, wait);
    }
}
