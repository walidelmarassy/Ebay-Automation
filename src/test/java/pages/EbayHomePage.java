package pages;

import org.openqa.selenium.By;

public class EbayHomePage extends BasePage {
    private final By searchInput = By.id("gh-ac");
    private final By searchButton = By.id("gh-btn");
    private final By homeLogo = By.id("gh-logo");

    public EbayHomePage(org.openqa.selenium.WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return actions.isVisible(homeLogo);
    }

    public void search(String query) {
        actions.type(searchInput, query);
        actions.pressEnter(searchInput);
    }
}
