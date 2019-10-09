# WebCrawlerNorwegian

Runnable Jar is located in the packed_to_run folder.

To run the crawler download packed_to_run folder.

For the crawler to work properly a folder named "export" should be existent in the same direcotry as runnable jar. 

To run the crawler through "cmd" please navigate to the downloaded jar directory and use command: 
  
    java -jar WebCrawlNorw.jar
  
Data is exported in csv format:

    flightId, flightDate, depPlace, depTime, arrPlace, arrTime, priceType, basePrice, taxPrice;

## Questions
### Could you please write any language you prefer how you would collect the information;

For the first task I would do this:

Firstly, I acquire URL for the specific search(OSL (Oslo) to RIX (Riga), departing 2019-11-01, direct flights only). I did it by simply using the website's search and copying the URL of the search result page.

Now I'll use this URL to start scrapping the flight's data.

1) Load the URL,
2) Get the flight date and check if it is the one I'm looking for (if it's ok then continue, if not stop and save the gathered data).
3) Get the row WebElement of the first available flight
4) Get flight id which is in the row "title" attribute.
5) Find the lowest available price(in this order: LowFare, LowFare+, Flex).
6) Select the checkbox for the lowest available price(loads side Infobox element).
7) Scrape the data from the row WebElement (Departure time, Arrival time, Departure place, Arrival place)
8) Scrape the data from the side InfoBox WebElement (Total price, tax price);
9) Continue steps (3 - 8) for every flight displayed on the page.
10) Click the "Show next day" button and repeat the steps untill data for the whole month is gathered. 

### Provide the exact number of page loads (requests) that would be needed to extract the data;

From fiddler log, I can see there are 140 requests of which 80 are what I suspect the necessary page loads for getting the data.

### Could the number of requests be reduced and how? Provide the exact number.

20 requests are for images, so one way to do it could be by blocking image requests. Thats -20 requests.

11 javascript and 9 of them are fonts and css, I am not sure if its possible or healthy to block these requests so I cannot comment on that.

From what I gathered blocking requests can be done for chromedriver through DevTool API.

Also if taxes would not be required I could potentially skip 40 requests, by not clicking on the price checkbox to see the side Infobox. 

Implementing this would let me skip 60 requests and the total amount of requests would only be 80.
