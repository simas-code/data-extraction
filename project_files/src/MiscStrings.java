
public class MiscStrings {
	public static final String ARN = "//*[@id=\"ARN\"]";
	public static final String LHR = "//*[@id=\"LHR\"]";
	
	public static final String URL = "https://classic.flysas.com/en/de/";
	public static final String URL_TEST = "https://www.whatismybrowser.com";
	
	public static final String SEARCH_CONTAINER = "//*[@id=\"SipContentFullRegionContainer\"]";
	public static final String ONE_WAY_CHECKBOX = "//*[@id=\"ctl00_FullRegion_MainRegion_ContentRegion_ContentFullRegion_ContentLeftRegion_CEPGroup1_CEPActive_cepNDPRevBookingArea_ceptravelTypeSelector_oneway\"]";
	public static final String FROM_FIELD = "//*[@id=\"ctl00_FullRegion_MainRegion_ContentRegion_ContentFullRegion_ContentLeftRegion_CEPGroup1_CEPActive_cepNDPRevBookingArea_predictiveSearch_txtFrom\"]";
	public static final String TO_FIELD = "//*[@id=\"ctl00_FullRegion_MainRegion_ContentRegion_ContentFullRegion_ContentLeftRegion_CEPGroup1_CEPActive_cepNDPRevBookingArea_predictiveSearch_txtTo\"]";
	public static final String CALENDAR_HEAD = "//*[@id=\"ui-datepicker-div\"]/div/div"; 
	public static final String CALENDAR_BODY = "//*[@id=\"ui-datepicker-div\"]/table"; 
	public static final String SELECT = "//*[@id=\"ctl00_FullRegion_MainRegion_ContentRegion_ContentFullRegion_ContentLeftRegion_CEPGroup1_CEPActive_cepNDPRevBookingArea_Searchbtn_ButtonLink\"]/span[2]";
	
	public static final String TBODY = "//*[@id=\"WDSEffect_table_0\"]/tbody";
	public static final String PRICE_ECONBG = "//*[@id=\"price_0_%s_ECONBG\"]";
	public static final String PRICE_ECOA = "//*[@id=\"price_0_%s_ECOA\"]";
	public static final String PRICE_PREMN = "//*[@id=\"price_0_%s_PREMN\"]";
	public static final String PRICE_PREMB = "//*[@id=\"price_0_%s_PREMB\"]";
	public static final String PRICE_PREMA = "//*[@id=\"price_0_%s_PREMA\"]";
	
	public static final String ROW_INFOBOX = "//*[@id=\"toggleId_0_%s\"]/table/tbody";
	public static final String FLIGHT_ID = "//*[@id=\"toggleId_0_%s\"]/table/tbody/tr[2]/td[%d]/acronym";
	
	public static final String INFOBOX = "//*[@id=\"toggleId_0_%s\"]/table/tbody";
	public static final String DEP_TIME_FIELD = "//*[@id=\"idLine_0_%s\"]/td[7]/span[1]";
	public static final String ARR_TIME_FIELD = "//*[@id=\"idLine_0_%s\"]/td[7]/span[3]";
	public static final String ROW_INFOBOX_ROUTE_FIRST = "//*[@id=\"toggleId_0_%s\"]/table/tbody/tr[2]/td[%d]/span[1]";
	public static final String ROW_INFOBOX_ROUTE_CON = "//*[@id=\"toggleId_0_%s\"]/table/tbody/tr[2]/td[%d]/span[3]";
	public static final String ROW_INFOBOX_ROUTE_LAST = "//*[@id=\"toggleId_0_%s\"]/table/tbody/tr[5]/td[3]/span[3]";
	public static final String LATER_DATES_BUTTON = "//*[@id=\"later_1\"]/a";
	
	//public static final String ROW_INFOBOX_ROUTE_FIRST_ = "//*[@id=\"toggleId_0_%s\"]/table/tbody/tr[2]/td[3]/span[3]";
	
	
	
	//*[@id="toggleId_0_0"]/table/tbody/tr[2]/td[2]
	
	//*[@id="toggleId_0_16"]/table/tbody/tr[2]/td[3]/span[3]/span
	//*[@id="toggleId_0_2"]/table/tbody/tr[2]/td[3]/span[3]/span
	//*[@id="toggleId_0_0"]/table/tbody/tr[2]/td[2]/span[3]/span[1]
	public static final String DATE_SELECTION_TABS = "//*[@id=\"flow\"]/div/div[2]/div[1]/div/table/tbody/tr/td/div[2]/table/tbody/tr";
	
	
	public static final String TOTAL_PRICE_CASH = "//*[@id=\"totalPriceCash\"]";
	public static final String TOTAL_TAXES = "//*[@id=\"taxesAndFees\"]";
	
	public static final String INFO = "Welcome!\n"
			+ "This web crawler scrapes flight data from website \"https://classic.flysas.com/en/de/\".\n"
			+ "Scraped data is as follows: flightID, flight date, departure airport, departure time, arrival airport, arrival time, cheapest price, taxes.\n"
			+ "Data is scraped for all direct or with a connection at Oslo flights from ARN (Stockholm) to LHR (London) departing from 2019-11-04 to 2019-11-10.\n"
			+ "Data is printed in console and saved in \".csv\" format at local project folder \"/export\".\n";
	public static final String PRINT = "Printing gathered data:\n";
	
	
	
}
