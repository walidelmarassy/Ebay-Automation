
# eBay Automation Framework — Java + Selenium

A **maintainable UI test automation framework** built using **Java, Selenium WebDriver, TestNG, and Allure Reports**.
Follows the **Page Object Model (POM)** and uses **external JSON test data** for flexibility and scalability.

---

## Overview

Automates a core eBay user journey:

* Navigate to homepage
* Validate page load
* Search for `mazda mx-5`
* Validate search results
* Log results count
* Apply filter: **Transmission → Manual**
* Validate filtered results

---

## Tech Stack

* Java 17+
* Selenium WebDriver 4
* TestNG
* Maven
* Allure Reports
* WebDriverManager

---

## Project Structure

```
src/test/java/
├── base/      → WebDriver setup & teardown
├── pages/     → Page Object classes
├── tests/     → Test cases
├── utils/     → Utilities (actions, data loader)

src/test/resources/
├── testdata/  → JSON test data
└── testng.xml
```

---

## Test Data

All test data is stored externally in JSON:

* No hardcoded values
* Easy to update and extend
* Clear separation of test logic and data

Example `testdata.json`:

```json
{
  "baseUrl": "https://www.ebay.com/",
  "searchQuery": "mazda mx-5",
  "filters": {
    "transmission": "Manual"
  }
}

```

---

## Running Tests

Run all tests:

```bash
mvn clean test
```

Run a specific TestNG suite:

```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```

Headless mode:

```bash
mvn clean test -Dheadless=true
```

---

## Reporting

Generate and open Allure report:

```bash
allure generate target/allure-results --clean -o target/allure-report
allure open target/allure-report
```

Report includes:

* Execution summary
* Step-level details
* Test timeline

---

## Framework Design

* **POM (Page Object Model):** separates UI and test logic
* **BaseTest:** manages WebDriver lifecycle
* **Reusable Utilities:** centralized browser actions
* **DataLoader:** reads external JSON test data

---

## Test Coverage

* Homepage validation
* Search functionality
* Results verification
* Results count logging
* Filter application & validation

---

## Setup

1. Clone the repository:

```bash
git clone <repo-url>
cd ebay_automation_java
```

2. Install dependencies:

```bash
mvn clean install
```



