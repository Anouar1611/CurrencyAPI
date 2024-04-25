package com.currencyapi.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CurrencyAPITest {
    public static void main(String[] args) {
        // Set the path to the Edge WebDriver
        System.setProperty("webdriver.edge.driver", "C:\\Users\\alMostapha\\Downloads\\edgedriver_win64\\msedgedriver.exe");

        // Initialize an Edge driver
        WebDriver driver = new EdgeDriver();

        // Explicit wait to handle elements that take time to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Navigate to the CurrencyAPI web page
            driver.get("http://localhost:3000"); // Assuming your front-end is served on port 3000

            // Locate elements on the web page
            WebElement amountInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("amountToConvert")));
            WebElement currencyDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("targetCurrency")));
            WebElement convertButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("convert")));

            // Interact with the elements
            amountInput.sendKeys("100"); // Enter amount
            currencyDropdown.sendKeys("USD"); // Select currency
            convertButton.click(); // Click Convert

            // Optionally, wait for the converted amount to be displayed
            WebElement convertedAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("convertedAmount")));
            System.out.println("Converted Amount: " + convertedAmount.getText());
        } catch (Exception ex) {
            System.out.println("Caused by this shit: " + ex.getMessage());
        }
    }
}




