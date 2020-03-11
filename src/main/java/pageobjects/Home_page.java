package pageobjects;

public class Home_page {
	public String round_trip_ = "//input[@id='RoundTrip']";
	public String From_textbox ="//input[@id='FromTag']";
	public String To_textbox = "//input[@id='ToTag']";
	public String fromdate_picker="//input[@id='FromDate']/..//a/i";
	public String depart_date="//div[contains(@class,'last')]//a[.='20']";
	public String return_date="//div[contains(@class,'first')]//a[.='20']";
	public String search_flight_BTN="//input[@id='SearchBtn']";
	public String oneStop_xpath="//nav[contains(@class,'stops')]//li[2]";
	public String zeroStop="//nav[contains(@class,'stops')]//li[1]";
	public String Departure_time_earlyM="//nav[contains(@class,'departureTime')]//li[1]";
	public String trip_duration_right_slider="//div[contains(@class,'tripDuration')]//a[contains(@class,'right')]";
	public String layover_right_slider= "//div[contains(@class,'layoverDuration')]//a[contains(@class,'right')]";
	public String Book_button ="//div[@style='display: block;']/..//button[.='Book']";
	public String title_booking_page="//p[contains(@class,'pageTitle')]";
	
	
	
	
	
	
	
	public String Duration_click_return="//div[contains(@class,'last')]//li[contains(@class,'duration')]";
	

	
	
	
	

}
