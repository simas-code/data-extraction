
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Crawler {   
 	private static final int DAYS_TO_CHECK = 30;
 	
    private ChromeDriver driver;
    private WebDriverWait wait;
    private List<Flight> flights;
    
    Crawler() {
    	System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("disable-gpu");
            	
    	this.driver = new ChromeDriver(chromeOptions);
        this.wait = new WebDriverWait(driver, 30);
        this.flights = new ArrayList<Flight>();
    }

    private void sleep() {
    	try {
			Thread.sleep(3000); 
		} catch(InterruptedException e) {
			System.out.println("Thread.sleep failed.");
			e.printStackTrace();
		}
    }
    
    private String getRowXpath(int rowIndex, int typeFlag) {
    	return String.format(MiscStrings.CONTENT_ROW_DATA,rowIndex,typeFlag);
    }
    
    private BigDecimal getTaxValue() {
    	String tax = "0";
    	WebElement taxTable = fluentWait(By.xpath(MiscStrings.TAX_TABLE));
    	for (WebElement tr : taxTable.findElements(By.tagName("tr"))) {
    		if (safeInnerText(tr).toLowerCase().contains("tax")) {
    			tax = safeInnerText(tr.findElement(By.className("rightcell")));
    		}
    	}
    	return new BigDecimal(tax.substring(1));
    }
    
    private BigDecimal getTotalPriceValue(int rowIndex, MiscStrings.PriceType priceType) {
    	String price = "-1";  	
    	switch (priceType) {
			case LOWFARE :
				price = safeInnerText(MiscStrings.LOWFARE_PRICE).substring(1);
				break;
			case LOWFAREPLUS :
				price = safeInnerText(MiscStrings.LOWFAREPLUS_PRICE).substring(1);
				break;
			case FLEX :
				price = safeInnerText(MiscStrings.FLEX_PRICE).substring(1);
				break;
		}
    	return new BigDecimal(price);
    }
    
    
    private MiscStrings.PriceType checkSelection(int index) {
    	MiscStrings.PriceType select = MiscStrings.PriceType.NONE;
    	if (existsElement(String.format(MiscStrings.PRICE_LOWFARE_CHECKBOX,index))) {
			select = MiscStrings.PriceType.LOWFARE;
		} else if (existsElement(String.format(MiscStrings.PRICE_LOWFAREPLUS_CHECKBOX,index))) {
			select = MiscStrings.PriceType.LOWFAREPLUS;
		} else if (existsElement(String.format(MiscStrings.PRICE_FLEX_CHECKBOX,index))) {
			select = MiscStrings.PriceType.FLEX;
		}
    	return select;
    }
    
	private boolean selectCheckbox(MiscStrings.PriceType select, int index) {
		boolean selected = false;
		switch (select) {
			case LOWFARE :
				safeClick(String.format(MiscStrings.PRICE_LOWFARE_CHECKBOX,index));
				selected = true;
				break;
			case LOWFAREPLUS :
				safeClick(String.format(MiscStrings.PRICE_LOWFAREPLUS_CHECKBOX,index));
				selected = true;
				break;
			case FLEX :
				safeClick(String.format(MiscStrings.PRICE_FLEX_CHECKBOX,index));
				selected = true;
				break;
			case NONE :
				selected = false;
		}
		return selected;
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
	
    private void safeClick(String xpath) {
    	try {
			fluentWait(By.xpath(xpath)).click();
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			fluentWait(By.xpath(xpath)).click();
		}
    }
    
    private boolean existsElement(String xpath) {
    	driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
    	boolean exists = driver.findElements(By.xpath(xpath)).size() != 0;
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
    
    private Flight getFlight(int timeRowIndex, int locationRowIndex, MiscStrings.PriceType priceType) {
    	Flight flight = new Flight(fluentWait(By.xpath(MiscStrings.FLIGH_TTITLE_FIELD)).getAttribute("title").substring(7),priceType);
    	flight.setDateString(safeInnerText(MiscStrings.DATE_STRING_FIELD));
    	flight.setDepTime(safeInnerText(getRowXpath(timeRowIndex, 1)));
    	flight.setDepPlace(safeInnerText(getRowXpath(locationRowIndex, 1)));
    	flight.setArrTime(safeInnerText(getRowXpath(timeRowIndex, 2)));
    	flight.setArrPlace(safeInnerText(getRowXpath(locationRowIndex, 2))); 	
    	
    	//*[@id="ctl00_MainContent_ipcAvaDay_upnlResSelection"]/div[1]/div/table/tbody/tr[15]/td[2]
    	//*[@id="ctl00_MainContent_ipcAvaDay_upnlResSelection"]/div[1]/div/table/tbody/tr[15]/td[2]
    	
//    	flight.setBasePrice(new BigDecimal(safeInnerText(getPriceXpath(16)).substring(1)));
    	
    	
    	
    	flight.setBasePrice(getTotalPriceValue(timeRowIndex, priceType));
    	flight.setTaxPrice(getTaxValue());
    	
//    	flight.setBasePrice(new BigDecimal(safeInnerText(MiscStrings.TOTAL_PRICE).substring(1)));
//       	flight.setBasePrice(new BigDecimal(getTaxValue().substring(1)));
    	
    	
//    	flight.setTaxPrice(new BigDecimal(safeInnerText(getPriceXpath(18)).substring(1)));
    	
    	
    	return flight;
    }
    
    public boolean crawl(String url) {
		this.driver.navigate().to(url);
		int index;
    	int timeRowIndex;
    	int locationRowIndex;
    	MiscStrings.PriceType priceType;
		for (int i = 0; i < DAYS_TO_CHECK; i++) {
			index = 0;
	    	timeRowIndex = 1;
	    	locationRowIndex = 2;
	    	do {
    			this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MiscStrings.TBODY)));
    			priceType = checkSelection(index);
    			selectCheckbox(priceType,index);
    			sleep();
    			
    			flights.add(getFlight(timeRowIndex,locationRowIndex,priceType));
    			
    			//*[@id="bookingPrice_TaxesToggleBox"]/table
    			
    			
	    		index++;
	    		timeRowIndex += 3;
	    		locationRowIndex += 3;
	    	} while(checkSelection(index) != MiscStrings.PriceType.NONE);
	    	
	    	this.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MiscStrings.NEXT_DAY_CLICK)));
	    	safeClick(MiscStrings.NEXT_DAY_CLICK);
	    	sleep();
    	}
    	driver.close();
    	return true;
    }
    
    public List<Flight> getFlights(){
    	return this.flights;
    }
    
}