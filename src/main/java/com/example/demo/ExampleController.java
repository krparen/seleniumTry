package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@Slf4j
@RequestMapping("/getCheapestTicket")
public class ExampleController {

    private String DEPARTURE_CITY_BLOCK_XPATH = "//div[@class = 'group-fields__from-block']/location-autocomplete/div";
    private String DESTINATION_CITY_BLOCK_XPATH = "//div[@class = 'search-form__group-fields']/location-autocomplete/div";
    private String CHEAPEST_TICKET_INFO_PATH = "//div[@class = 'ticket-group ticket-group_cheapest']/div[@class = 'ticket-group__container']";
    private WebDriver driver;

    @RequestMapping
    public TicketInfo getSiteContent() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE); // https://www.skptricks.com/2018/08/timed-out-receiving-message-from-renderer-selenium.html
        driver = new ChromeDriver(options);
        driver.get("https://spasibosberbank.travel/#/");

        fillCityFrom();
        fillCityTo();
        increasePassengersCount();
        clickSubmitButton();

        return parseCheapestTicketInfo();
    }

    private void fillCityFrom() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DEPARTURE_CITY_BLOCK_XPATH + "/div[@class = 'autocomplete__close']")));
        WebElement inputFrom = driver.findElement(By.xpath(DEPARTURE_CITY_BLOCK_XPATH + "/input"));
        inputFrom.clear();
        inputFrom.sendKeys("Москва");
        By firstAutocompleteItem = By.xpath(DEPARTURE_CITY_BLOCK_XPATH + "/ul/li");
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstAutocompleteItem));
        WebElement autocompleteResult = driver.findElement(firstAutocompleteItem);
        autocompleteResult.click();
    }

    private void fillCityTo() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement inputTo = driver.findElement(By.xpath(DESTINATION_CITY_BLOCK_XPATH + "/input"));
        inputTo.sendKeys("Новосибирск");
        By firstAutocompleteItem = By.xpath(DESTINATION_CITY_BLOCK_XPATH + "/ul/li");
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstAutocompleteItem));
        WebElement searchResultTo = driver.findElement(firstAutocompleteItem);
        searchResultTo.click();
    }

    private TicketInfo parseCheapestTicketInfo() {
        TicketInfo result = new TicketInfo();
        WebDriverWait wait = new WebDriverWait(driver, 7);

        By buyButtonXpath = By.xpath(CHEAPEST_TICKET_INFO_PATH + "//button[@class = 'btn btn__blue ticket-group__buy-btn']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(buyButtonXpath));
        WebElement buyButton = driver.findElement(buyButtonXpath);
        String cost = buyButton.getText().replace("Купить за ", "");
        result.setCost(cost);

        WebElement departureTimeElem = driver.findElements(By.xpath(CHEAPEST_TICKET_INFO_PATH + "//div[@class = 'flight-description__to-from']//div[@class = 'flight-description__to-from-time']"))
                .get(0);
        WebElement departureDateElem = driver.findElements(By.xpath(CHEAPEST_TICKET_INFO_PATH + "//div[@class = 'flight-description__to-from']//div[@class = 'flight-description__to-from-date']"))
                .get(0);
        result.setDepartureDateTime(departureTimeElem.getText() + " " + departureDateElem.getText());

        WebElement arrivalTimeElem = driver.findElements(By.xpath(CHEAPEST_TICKET_INFO_PATH + "//div[@class = 'flight-description__to-from']//div[@class = 'flight-description__to-from-time']"))
                .get(1);
        WebElement arrivalDateElem = driver.findElements(By.xpath(CHEAPEST_TICKET_INFO_PATH + "//div[@class = 'flight-description__to-from']//div[@class = 'flight-description__to-from-date']"))
                .get(1);
        result.setArrivalDateTime(arrivalTimeElem.getText() + " " + arrivalDateElem.getText());

        WebElement flightLengthElem = driver.findElement(By.xpath(CHEAPEST_TICKET_INFO_PATH + "//div[@class = 'flight-description__transfer-stops-about hide-in-mobile']"));
        result.setFlightLength(flightLengthElem.getAttribute("textContent"));

        WebElement airlineElem = driver.findElement(By.xpath(CHEAPEST_TICKET_INFO_PATH + "//div[@class = 'flight-description__company-icon show-only-mobile']"));
        result.setAirline(airlineElem.getAttribute("textContent"));

        return result;
    }

    private void clickSubmitButton() {
        WebElement submitButton = driver.findElement(By.className("search-form__submit"));
        submitButton.click();
    }

    private void increasePassengersCount() throws  Exception {
        driver.findElement(By.xpath("//div[@class = 'select-passengers-count__label']")).click();
        driver.findElement(By.xpath("//div[@class = 'select-passengers-count__popup']/div/div//button[@class = 'counter__btn btn-plus']")).click();
    }

    @PostConstruct
    public void postConstruct() {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
    }
}
