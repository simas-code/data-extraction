
public class MiscStrings {
	public static final String URL = "https://www.norwegian.com/en/ipc/availability/avaday?D_City=OSL&A_City=RIX&TripType=1&D_Day=1&D_Month=201911&D_SelectedDay=1&R_Day=1&R_Month=201911&R_SelectedDay=1&IncludeTransit=false&AgreementCodeFK=-1&CurrencyCode=EUR&rnd=24488&processid=75799&mode=ab";
	
	public static final String TBODY = "//*[@id=\"avaday-outbound-result\"]/div/div/div[2]/div/table/tbody";
    public static final String PRICE_LOWFARE_CHECKBOX = "//*[@id=\"FlightSelectOutboundStandardLowFare%d\"]";
    public static final String PRICE_LOWFAREPLUS_CHECKBOX = "//*[@id=\"FlightSelectOutboundStandardLowFarePlus%d\"]";
    public static final String PRICE_FLEX_CHECKBOX = "//*[@id=\"FlightSelectOutboundStandardFlex%d\"]";
    public static final String FLIGH_TTITLE_FIELD = "//*[@id=\"avaday-outbound-result\"]/div/div/div[2]/div/table/tbody/tr[2]/td[1]";
    public static final String CONTENT_ROW_DATA = "//*[@id=\"avaday-outbound-result\"]/div/div/div[2]/div/table/tbody/tr[%d]/td[%d]/div"; // %d => rowIndex + 3; %d => 1 for DepartureData, 2 for ArrivalData
 	public static final String DATE_STRING_FIELD = "//*[@id=\"avaday-outbound-result\"]/div/div/div[1]/table/tbody/tr/td[2]";
 	public static final String NEXT_DAY_CLICK = "//*[@id=\"avaday-outbound-result\"]/div/div/div[3]/table/tbody/tr/td[2]/div/span[2]";
 	
 	public static final String LOWFARE_PRICE = "//*[@id=\"ctl00_MainContent_ipcAvaDay_upnlResSelection\"]/div[1]/div/table/tbody/tr[10]/td[2]";
 	public static final String LOWFAREPLUS_PRICE = "//*[@id=\"ctl00_MainContent_ipcAvaDay_upnlResSelection\"]/div[1]/div/table/tbody/tr[12]/td[2]";
 	public static final String FLEX_PRICE = "//*[@id=\"ctl00_MainContent_ipcAvaDay_upnlResSelection\"]/div[1]/div/table/tbody/tr[12]/td[2]";

 	public static final String TAX_TABLE = "//*[@id=\"bookingPrice_TaxesToggleBox\"]/table/tbody/tr/td/table";
 	
	public static final String INFO = "Welcome!\n"
			+ "This web crawler scrapes flight data from website \"www.Norwegian.com\".\n"
			+ "Scraped data is as follows: flightID, flight date, departure airport, departure time, arrival airport, arrival time, cheapest price, taxes.\n"
			+ "Data is scraped for all direct flights from OSL (Oslo) to RIX (Riga) departing from 2019-11-01 to 2019-11-30.\n"
			+ "Data is printed in console and saved in \".csv\" format at local project folder \"/export\".\n";
	public static final String PRINT = "Printing gathered data:\n";
 	
 	public enum PriceType { 
    	NONE, 
    	LOWFARE, 
    	LOWFAREPLUS,
    	FLEX
    	};
}
