package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/getSiteContent")
public class ExampleController {

    @RequestMapping
    public String getSiteContent() {
        WebDriver driver = new ChromeDriver();
        WebElement inputFrom = driver.findElement(By.xpath("//div[@class = 'group-fields__from-block']/location-autocomplete/div"));
        driver.get("https://spasibosberbank.travel/#/");
//        return driver.getPageSource();
        return inputFrom.toString();
    }

    @PostConstruct
    public void postConstruct() {
        System.setProperty("webdriver.chrome.driver","C:/chromedriver/chromedriver.exe");
    }
}
