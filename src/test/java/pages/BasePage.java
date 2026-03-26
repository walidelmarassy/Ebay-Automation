package pages;

import org.openqa.selenium.WebDriver;
import utils.BrowserActions;

public class BasePage {
    protected WebDriver driver;
    protected BrowserActions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new BrowserActions(driver);
    }
}
