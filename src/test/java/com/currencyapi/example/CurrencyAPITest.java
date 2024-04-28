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
        System.setProperty("webdriver.edge.driver", "C:\\Users\\alMostapha\\Downloads\\edgedriver_win64\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("http://localhost:3000");

            WebElement amountInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("amountToConvert")));
            WebElement currencyDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("targetCurrency")));
            WebElement convertButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("convert")));

            amountInput.sendKeys("100");
            currencyDropdown.sendKeys("USD");

            WebElement convertedAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("convertedAmount")));
            System.out.println("Converted Amount: " + convertedAmount.getText());
        } catch (Exception ex) {
            System.out.println("Caused by this : " + ex.getMessage());
        }
    }
}