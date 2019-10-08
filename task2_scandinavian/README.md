
## Questions
### Could you please write any language you prefer how you would collect the information;

I have not completed the second task because I was not able to find how to configure cookies for chromedriver to work properly on this site.

For the second task I would do this:
Firstly, I need to initiate the search (ARN (Stockholm) to LHR (London) departing 2019-11-04).

1) Wait for the next page to load.
2) Check if the day selected is correct. If not choose the appropriate day(out of three displayed dates at the top of the page)
3) Get the row WebElement of the first available flight. 
4) Find the lowest available price(in this order: SAS Go Light, SAS Go Smart, SAS Plus Smart, SAS Plus Pro, SAS Plus Full Flex).
5) Select the lowest available price(this loads side Infobox1 element and Infobox2 under the selected row).
6) Check if the flight is direct or has a connection at Oslo. If yes continue scrapping this row, if not skip it. This condition can be checked by parsing the information in the Infobox2.  
7) Get the flight id which is in the Infobox2 under the selected row.
8) Scrape the data from the row WebElement (Departure time, Arrival time).
9) Scrape the data from the infobox under the selected row (Departure place, Arrival place, Connection place).
10) Scrape the data from the side InfoBox WebElement (Total price, tax price);
11) Repeat steps (4 - 11) for every displayed flight.
12) If existent select the next day at the top of the page, otherwise click "Later dates" button.
13) Repeat steps (2 - 13) until data is collected for the period from 2019-11-04 to 2019-11-10.

### Provide the exact number of page loads (requests) that would be needed to extract the data;

-

### Could the number of requests be reduced and how? Provide the exact number.

-

