#!/bin/bash
set -e

if ! command -v allure >/dev/null 2>&1; then
  echo "Allure CLI not found. Install with: brew install allure"
  exit 1
fi

mvn clean test
allure generate target/allure-results --clean -o target/allure-report
allure open target/allure-report
