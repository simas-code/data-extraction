public class Main {
	  public static void main(String[] args) {
    	System.out.print(MiscStrings.INFO);
    	Printer printer = new Printer("export/");
    	Crawler crawler = new Crawler();
    	
		crawler.crawl(MiscStrings.URL);
		
		System.out.print(MiscStrings.PRINT);
		printer.printFlights(crawler.getFlights());
		printer.exportFlights(crawler.getFlights());
		printer.close();
	}
}
