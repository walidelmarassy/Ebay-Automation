package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserActions {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public BrowserActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public WebElement click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            element.click();
        } catch (Exception e) {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        return element;
    }

    public WebElement clickWithJs(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        return element;
    }

    public boolean clickByJs(String script) {
        Object result = ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(script);
        return Boolean.TRUE.equals(result);
    }

    public WebElement pressEnter(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.sendKeys(org.openqa.selenium.Keys.ENTER);
        return element;
    }

    public WebElement type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
        return element;
    }

    public String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public void waitForPresence(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForCountGreaterThan(By locator, int count) {
        wait.until(driver -> driver.findElements(locator).size() > count);
    }

    public void waitForAnyVisible(By... locators) {
        wait.until(driver -> {
            for (By locator : locators) {
                if (!driver.findElements(locator).isEmpty()) {
                    return true;
                }
            }
            return false;
        });
    }

    public void waitForAnyPresent(By... locators) {
        wait.until(driver -> {
            for (By locator : locators) {
                if (!driver.findElements(locator).isEmpty()) {
                    return true;
                }
            }
            return false;
        });
    }

    public int count(By locator) {
        return driver.findElements(locator).size();
    }

    public java.util.List<String> getTexts(By locator) {
        java.util.List<WebElement> elements = driver.findElements(locator);
        java.util.List<String> texts = new java.util.ArrayList<>();
        for (WebElement element : elements) {
            texts.add(element.getText());
        }
        return texts;
    }

    public boolean isVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement scrollIntoView(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        return element;
    }

    public void scrollBy(int pixels) {
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("window.scrollBy(0, arguments[0]);", pixels);
    }

    public void waitForUrlContains(String value) {
        wait.until(ExpectedConditions.urlContains(value));
    }

    public boolean isPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    public boolean isSelected(By locator) {
        try {
            return driver.findElement(locator).isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickIfNotSelected(By locator) {
        if (!isSelected(locator)) {
            click(locator);
        }
    }
}
