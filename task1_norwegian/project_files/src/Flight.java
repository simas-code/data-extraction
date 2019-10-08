import java.math.BigDecimal;

public class Flight {
	private String flightId;
	private String dateString;
	private String depTime;
	private String depPlace;
	private String arrTime;
	private String arrPlace;
	private MiscStrings.PriceType priceType;
	private BigDecimal basePrice;
	private BigDecimal taxPrice;
	
	Flight(String flightId, MiscStrings.PriceType priceType){
		this.flightId = flightId;
		this.dateString = "";
		this.depTime = "";
		this.depPlace = "";
		this.arrTime = "";
		this.arrPlace = "";
		this.priceType = priceType;
		this.basePrice = new BigDecimal(0);
		this.taxPrice = new BigDecimal(0);
	}
	
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}
	public void setDepPlace(String depPlace) {
		this.depPlace = depPlace;
	}
	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}
	public void setArrPlace(String arrPlace) {
		this.arrPlace = arrPlace;
	}
	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}
	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}
	
	public String toText() {
		return String.format("%s %s %s %s %s %s %s %.2f %.2f", flightId, dateString, depPlace, depTime, arrPlace, arrTime, priceType, basePrice, taxPrice);
	}
	public String toCsvString() {
		return String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",%.2f,%.2f", flightId, dateString, depPlace, depTime, arrPlace, arrTime, priceType, basePrice, taxPrice);
	}
}
