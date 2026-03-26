package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EbayHomePage;
import pages.EbayResultsPage;
import utils.DataLoader;

import java.util.Map;

public class EbaySearchTest extends BaseTest {

    @Test
    @Description("Search for mazda mx-5, validate results, and apply transmission filter")
    public void ebaySearchFlow() {
        Map<String, Object> data = DataLoader.loadTestData("test_data.json");
        String baseUrl = data.get("baseUrl").toString();
        String searchQuery = data.get("searchQuery").toString();
        Map<String, Object> filters = (Map<String, Object>) data.get("filters");
        String transmission = filters.get("transmission").toString();

        EbayHomePage homePage = new EbayHomePage(driver);
        EbayResultsPage resultsPage = new EbayResultsPage(driver);

        driver.get(baseUrl);
        Assert.assertTrue(homePage.isLoaded(), "Ebay home page did not load");

        homePage.search(searchQuery);
        resultsPage.waitForResultsPage();
        Assert.assertTrue(resultsPage.hasResults(), "No search results found");

        int resultsCount = resultsPage.getResultsCount();
        System.out.println("Search results count: " + resultsCount);
        Assert.assertTrue(resultsCount > 0, "Results count should be greater than zero");

        resultsPage.applyTransmissionFilter(transmission);
        Assert.assertTrue(resultsPage.isFilterApplied(transmission), "Transmission filter not applied");
        Assert.assertTrue(resultsPage.hasResults(), "Results disappeared after applying transmission filter");
    }
}
