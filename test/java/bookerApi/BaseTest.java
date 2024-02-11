package bookerApi;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.*;
public class BaseTest {
	
	@BeforeMethod
	public void checkStatus() {
		
		when()
			.get("https://restful-booker.herokuapp.com/ping")
		.then()
			.assertThat()
				.statusCode(201);
	}
	

}
