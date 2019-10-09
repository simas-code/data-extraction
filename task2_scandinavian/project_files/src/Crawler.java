
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Crawler {
	
 	private static final int START_DAY = 4;
 	private static final int END_DAY = 10;
 	private static final String MONTH = "nov";
 	private static final String MONTH_FULL = "november";
 	
    private ChromeDriver driver;
    private WebDriverWait wait;
    private List<Flight> flights;
    private String dateDay;
    
    
    private void setCookies() {
    	driver.manage().deleteAllCookies();
    }
    
    Crawler() {
    	System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver\\chromedriver.exe");
    	ChromeOptions options = new ChromeOptions();
    	Map<String, Object> prefs = new HashMap<String, Object>();
    	prefs.put("intl.accept_languages", "en-US,en");
    	options.setExperimentalOption("prefs", prefs);
		options.addArguments("--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));    

    	this.driver = new ChromeDriver(options);
        this.wait = new WebDriverWait(driver, 30);
        this.flights = new ArrayList<Flight>();
        this.dateDay = String.valueOf(START_DAY);
    }

    private void sleep(int flg) {
    	try {
	    	switch (flg) {
	    	case 1 :
		    	Thread.sleep(3000);
		    	break;
	    	case 2 : 
	    		Thread.sleep(10000);		
	    		break;
	    	}
    	} catch(InterruptedException e) {
			System.out.println("Thread.sleep failed.");
			e.printStackTrace();
		}
    }
    
	private String selectPrice(String id) {
		String type = "";
		if (existsElement(String.format(MiscStrings.PRICE_ECONBG,id))) {
			safeClickXpath(String.format(MiscStrings.PRICE_ECONBG,id));
			type = "ECONBG";
		} else if (existsElement(String.format(MiscStrings.PRICE_ECOA,id))) {
			safeClickXpath(String.format(MiscStrings.PRICE_ECOA,id));
			type = "ECOA";
		} else if (existsElement(String.format(MiscStrings.PRICE_PREMN,id))) {
			safeClickXpath(String.format(MiscStrings.PRICE_PREMN,id));
			type = "PREMN";
		} else if (existsElement(String.format(MiscStrings.PRICE_PREMB,id))) {
			safeClickXpath(String.format(MiscStrings.PRICE_PREMB,id));
			type = "PREMB";
		} else if (existsElement(String.format(MiscStrings.PRICE_PREMA,id))) {
			safeClickXpath(String.format(MiscStrings.PRICE_PREMA,id));
			type = "PREMA";
		}
		return type;
	}
    
	private String safeInnerText(String xpath) {
		String tmp;
		try {
			tmp = fluentWait(By.xpath(xpath)).getAttribute("innerText");
		} catch(org.openqa.selenium.StaleElementReferenceException ex) {
			tmp = fluentWait(By.xpath(xpath)).getAttribute("innerText");
		}
		return tmp;
	}
	
	private String safeInnerText(WebElement element) {
		String tmp;
		try {
			tmp = element.getAttribute("innerText");
		} catch(org.openqa.selenium.StaleElementReferenceException ex) {
			tmp = element.getAttribute("innerText");
		}
		return tmp;
	}
	
    private void safeClickXpath(String xpath) {
    	try {
			fluentWait(By.xpath(xpath)).click();
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			fluentWait(By.xpath(xpath)).click();
		}
    }
    
    private void safeClick(String className) {
    	try {
			fluentWait(By.className(className)).click();
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			fluentWait(By.className(className)).click();
		}
    }
    
    private void safeClick(WebElement element) {
    	try {
			element.click();
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			element.click();
		}
    }
    
    private boolean existsElement(String xpath) {
    	driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
    	boolean exists = driver.findElements(By.xpath(xpath) ).size() != 0;
    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    	return exists;
    }
    
    public WebElement fluentWait(final By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
        		.withTimeout(Duration.ofSeconds(45))
                .pollingEvery(Duration.ofMillis(10))
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
        return  foo;
    }
    
    private Flight getFlightData(String rowId, String priceType, int flg) {
    	this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(MiscStrings.ROW_INFOBOX,rowId))));
    	Flight flight = new Flight(safeInnerText(String.format(MiscStrings.FLIGHT_ID, rowId, flg -1)),priceType);
    	flight.setDateString(String.format("%s %s", dateDay, MONTH));
    	flight.setDepTime(safeInnerText(String.format(MiscStrings.DEP_TIME_FIELD, rowId)));
    	flight.setArrTime(safeInnerText(String.format(MiscStrings.ARR_TIME_FIELD, rowId)));
    	if (flg == 3) {
    		flight.setDepPlace(safeInnerText(String.format(MiscStrings.ROW_INFOBOX_ROUTE_FIRST, rowId, flg)));
			flight.setConPlace(safeInnerText(String.format(MiscStrings.ROW_INFOBOX_ROUTE_CON, rowId, flg)));
			flight.setArrPlace(safeInnerText(String.format(MiscStrings.ROW_INFOBOX_ROUTE_LAST, rowId)));
    	} else {
    		flight.setDepPlace(safeInnerText(String.format(MiscStrings.ROW_INFOBOX_ROUTE_FIRST, rowId, flg)));
    		flight.setArrPlace(safeInnerText(String.format(MiscStrings.ROW_INFOBOX_ROUTE_CON, rowId, flg))); 
		}
    	flight.setBasePrice(new BigDecimal(safeInnerText(MiscStrings.TOTAL_PRICE_CASH).replace(",", ".")));
    	flight.setTaxPrice(new BigDecimal(safeInnerText(MiscStrings.TOTAL_TAXES).replace(",", ".")));
    	System.out.println(flight.toText());
    	return flight;
    }
    
    private int checkRouteViability(String id) {
    	this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(MiscStrings.INFOBOX, id))));
    	int flg = -1;
    	
    	if (existsElement(String.format(MiscStrings.ROW_INFOBOX_ROUTE_CON, id, 2))) {
    		if (safeInnerText(String.format(MiscStrings.ROW_INFOBOX_ROUTE_CON, id, 2)).toLowerCase().contains("heathrow")) {
        		flg = 2;
        	}
    	} else {
    		if (safeInnerText(String.format(MiscStrings.ROW_INFOBOX_ROUTE_CON, id, 3)).toLowerCase().contains("oslo")) {
        		flg = 3;
    		}
    	}
    	return flg;
    }
    
    private void initiateSearch() {
    	this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MiscStrings.SEARCH_CONTAINER)));
    	safeClickXpath(String.format(MiscStrings.ONE_WAY_CHECKBOX));
    	
    	fluentWait(By.xpath(MiscStrings.FROM_FIELD)).sendKeys("ARN");
    	this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MiscStrings.ARN)));
    	safeClickXpath(MiscStrings.ARN);
    	
    	fluentWait(By.xpath(MiscStrings.TO_FIELD)).sendKeys("LHR");
    	this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MiscStrings.LHR)));
    	safeClickXpath(MiscStrings.LHR);
    	
    	this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("flOutDate")));
    	safeClick("flOutDate");
    	
    	WebElement calendarHead = fluentWait(By.xpath(MiscStrings.CALENDAR_HEAD));
    	String tmp;
    	for (WebElement span : calendarHead.findElements(By.tagName("span"))) {
    		tmp = safeInnerText(span);
    		if (tmp.toLowerCase().equals(MONTH) ||
    				tmp.toLowerCase().equals(MONTH_FULL)) {
    			safeClick(span);
    			break;
    		}
    	}
    	
    	this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MiscStrings.CALENDAR_BODY)));
    	WebElement calendarBody = fluentWait(By.xpath(MiscStrings.CALENDAR_BODY));
    	for (WebElement a : calendarBody.findElements(By.tagName("a"))) {
    		tmp = safeInnerText(a);
    		if (tmp.equals(String.valueOf(START_DAY))) {
    			safeClick(a);
    			break;
    		}
    	}
    	sleep(1);
    	safeClickXpath(MiscStrings.SELECT);
    	
    }
    
    private String incDateDay(String dateDay) {
    	return dateDay = String.valueOf(Integer.parseInt(dateDay) + 1);
    }
    
    private boolean selectDate(String dateDay) { // returns false if date not found also later_dates button was clicked
    	boolean isFound = false;
    	if (existsElement(MiscStrings.DATE_SELECTION_TABS)) {
			WebElement dateTabs = fluentWait(By.xpath(MiscStrings.DATE_SELECTION_TABS));
			for (WebElement td : dateTabs.findElements(By.tagName("td"))) {
				String tmp = safeInnerText(td);
				if (tmp.toLowerCase().contains(dateDay + " " + MONTH)){
					isFound = true;
					safeClick(td);
					sleep(2);
					break;
				}
			}
		}
    	return isFound;
    }
    
    public boolean crawl(String url) throws PageNotLoadedException {
		this.driver.navigate().to(url);
		initiateSearch();
		sleep(2);
		boolean run = true;
		
		while(run){
			if (existsElement(MiscStrings.TBODY)) {
				
				if (Integer.parseInt(dateDay) > END_DAY) {
					break;
				}
				
				if (!selectDate(dateDay)) {
					safeClickXpath(MiscStrings.LATER_DATES_BUTTON);
					sleep(2);
					continue;
				} 
			
				String id = "";
				String priceType = "";
				WebElement tbody = fluentWait(By.xpath(MiscStrings.TBODY));
				int flg = 0;
				for(WebElement row : tbody.findElements(By.tagName("tr"))) {
					id = row.getAttribute("id");
					if (id.length() > 7) {
						id = (row.getAttribute("id").split("_"))[2];
						priceType = selectPrice(id);
						flg = checkRouteViability(id); // check if direct flight or with a connection at Oslo
						if (flg > 0) {
							flights.add(getFlightData(id, priceType, flg));
						}
					} else {
						continue;
					}
				}
				dateDay = incDateDay(dateDay);
			} else {
				throw new PageNotLoadedException("Possible human verification screen.");
			}
		}
		driver.close();
    	return true;
    }
    
    public List<Flight> getFlights(){
    	return this.flights;
    }
}