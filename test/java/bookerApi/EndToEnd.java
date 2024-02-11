package bookerApi;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.ValidatableResponseOptions;

public class EndToEnd {
	private String token;
	private String firstName;
	private String lastName;
	private int price;
	private String checkIn;
	private String checkOut;
	public int bookingId;
	
	public EndToEnd() {
		System.out.println("Called");
	}

	@Test
	public void getAll_IDs() {
		given().contentType("application/json").when().get("https://restful-booker.herokuapp.com/booking").then()
				.assertThat().statusCode(200).body(containsString("bookingid"));
	}

	@Test
	public void createBooking() {
		DataGenerator data = new DataGenerator();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstname", firstName = data.firstNameGenerator());
		map.put("lastname", lastName = data.lastNameGenerator());
		map.put("totalprice", price = data.numberGenerator());
		map.put("depositpaid", true);
		Map<String, String> dates = new HashMap<>();
		dates.put("checkin", "2023-05-09");
		dates.put("checkout", "2023-07-09");
		map.put("bookingdates", dates);
		map.put("additionalneeds", "Breakfast,Dinner");

		bookingId = given()
					.contentType("application/json")
					.body(map).baseUri("https://restful-booker.herokuapp.com")
				.when()
					.post("/booking/")
				.then()
					.log().body().assertThat().statusCode(200).body("booking.firstname", equalTo(firstName))
					.body("booking.lastname", equalTo(lastName)).body("booking.totalprice", equalTo(price)).extract()
					.path("bookingid");
		System.out.println("ID01 :" + bookingId);
	}

	@Test
	public void getIdDetails() {
		given()
		.contentType("application/json")
		.baseUri("https://restful-booker.herokuapp.com")
	.when()
		.get("/booking/{bookingId}",bookingId)
	.then()
		.log().all();
		System.out.println("ID02 :" + bookingId);

	}
}
