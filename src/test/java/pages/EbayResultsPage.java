package pages;

import org.openqa.selenium.By;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EbayResultsPage extends BasePage {
    private final By resultsHeader = By.cssSelector("h1.srp-controls__count-heading");
    private final By resultsCount = By.cssSelector("h1.srp-controls__count-heading span.BOLD");
    private final By resultsContainer = By.cssSelector("ul.srp-results");
    private final By resultsContainerAlt = By.id("srp-river-results");
    private final By resultItems = By.cssSelector("li.s-item");
    private final By resultItemsAlt = By.cssSelector("div.s-item__wrapper");
    private final By transmissionFilter = By.xpath("//div[contains(@class,'x-refine__item__title-container') and normalize-space()='Transmission']");
    private final By appliedFilters = By.cssSelector("li.srp-refine__category__item--applied");
    private final By appliedFilterText = By.xpath("//li[contains(@class,'srp-refine__category__item--applied')]//span");
    private final By selectedFilterText = By.xpath("//li[contains(@class,'srp-refine__category__item--selected')]//span");
    private final By activeFilterLinks = By.xpath("//a[contains(@class,'x-refine__multi-select-link') and contains(@class,'x-refine__multi-select-link--selected')]//span");

    public EbayResultsPage(org.openqa.selenium.WebDriver driver) {
        super(driver);
    }

    public boolean hasResults() {
        actions.waitForAnyVisible(resultsHeader, resultsContainer, resultsContainerAlt);
        if (countResults() > 0) {
            return true;
        }
        return getResultsCount() > 0;
    }

    public void waitForResultsPage() {
        actions.waitForUrlContains("_nkw=");
        actions.waitForAnyVisible(resultsHeader, resultsContainer, resultsContainerAlt);
    }

    private int countResults() {
        return actions.count(resultItems) + actions.count(resultItemsAlt);
    }

    public int getResultsCount() {
        String text = actions.getText(resultsHeader);
        Matcher matcher = Pattern.compile("([\\d,]+)").matcher(text);
        if (!matcher.find()) {
            return 0;
        }
        return Integer.parseInt(matcher.group(1).replace(",", ""));
    }

    public void applyTransmissionFilter(String transmission) {
        scrollUntilPresent(transmissionFilter, 10);
        if (actions.isVisible(transmissionFilter)) {
            actions.clickWithJs(transmissionFilter);
        }

        By manualCheckbox = By.xpath("//input[@type='checkbox' and @aria-label='" + transmission + "']");
        By manualLink = By.xpath("//input[@type='checkbox' and @aria-label='" + transmission + "']/ancestor::a");
        scrollUntilPresent(manualCheckbox, 10);

        if (actions.isPresent(manualLink)) {
            actions.clickWithJs(manualLink);
        } else if (actions.isPresent(manualCheckbox)) {
            actions.clickIfNotSelected(manualCheckbox);
        }

        actions.waitForUrlContains("Transmission=Manual");
    }

    public boolean isFilterApplied(String filterValue) {
        String target = filterValue.toLowerCase();
        return actions.getTexts(appliedFilterText).stream().anyMatch(text -> text.toLowerCase().contains(target))
                || actions.getTexts(selectedFilterText).stream().anyMatch(text -> text.toLowerCase().contains(target))
                || actions.getTexts(activeFilterLinks).stream().anyMatch(text -> text.toLowerCase().contains(target))
                || driver.getCurrentUrl().toLowerCase().contains("transmission=" + target);
    }

    private void scrollUntilPresent(By locator, int maxAttempts) {
        int attempts = 0;
        while (attempts < maxAttempts && !actions.isPresent(locator)) {
            actions.scrollBy(350);
            attempts++;
        }
        if (actions.isPresent(locator)) {
            actions.scrollIntoView(locator);
        }
    }
}
