package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@Slf4j
@RequestMapping("/getSiteContent")
public class ExampleController {

    @RequestMapping
    public String getSiteContent() throws Exception {
        WebDriver driver = new ChromeDriver();
        driver.get("https://spasibosberbank.travel/#/");
        Thread.sleep(5000);
        WebElement inputFrom = driver.findElement(By.xpath("//div[@class = 'group-fields__from-block']/location-autocomplete/div/input"));
        inputFrom.clear();
        inputFrom.sendKeys("Москва");
        Thread.sleep(5000);
        WebElement searchResult = driver.findElement(By.xpath("//div[@class = 'group-fields__from-block']/location-autocomplete/div/ul/li"));
        searchResult.click();
        return inputFrom.toString();
    }

    @PostConstruct
    public void postConstruct() {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
    }
}
