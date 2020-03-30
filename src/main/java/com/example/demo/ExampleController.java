package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@Slf4j
@RequestMapping("/getSiteContent")
public class ExampleController {

    private String FROM_BLOCK_XPATH = "//div[@class = 'group-fields__from-block']/location-autocomplete/div";
    private String TO_BLOCK_XPATH = "";

    @RequestMapping
    public String getSiteContent() throws Exception {
        WebDriver driver = new ChromeDriver();
        driver.get("https://spasibosberbank.travel/#/");
        WebDriverWait wait = new WebDriverWait(driver,30);
        //wait.until(ExpectedConditions.visibilityOfElementLocated())
        Thread.sleep(2000);
        WebElement inputFrom = driver.findElement(By.xpath("//div[@class = 'group-fields__from-block']/location-autocomplete/div/input"));
        inputFrom.clear();
        inputFrom.sendKeys("Москва");
        Thread.sleep(2000);
        WebElement searchResult = driver.findElement(By.xpath("//div[@class = 'group-fields__from-block']/location-autocomplete/div/ul/li"));
        searchResult.click();

        // autocomplete__close

        WebElement inputTo = driver.findElement(By.xpath("//div[@class = 'search-form__group-fields']/location-autocomplete/div/input"));
        inputTo.clear();
        inputTo.sendKeys("Новосибирск");
        Thread.sleep(2000);
        WebElement searchResultTo = driver.findElement(By.xpath("//div[@class = 'search-form__group-fields']/location-autocomplete/div/ul/li"));
        searchResultTo.click();

        WebElement submitButton = driver.findElement(By.className("search-form__submit"));
        submitButton.click();

        //search-form__submit
        return inputFrom.toString();
    }

    @PostConstruct
    public void postConstruct() {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
    }
}
