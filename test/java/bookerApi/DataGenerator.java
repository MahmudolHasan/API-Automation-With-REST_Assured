package bookerApi;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;

import org.testng.annotations.Test;

public class DataGenerator {
    static Faker faker = new Faker(new Locale ("en-US"));

    public String firstNameGenerator() {
        return faker.name().firstName();
    }

    public String lastNameGenerator() {
        return faker.name().lastName();
    }
    public String fullNameGenerator(){
        return faker.name ().fullName ();
    }
    public String emailGenerator() {
        return faker.bothify("????##@mail.com");
    }
    public int numberGenerator() {
    	return Integer.parseInt(faker.numerify("####"));
    }
    @Test
    public String boolenaGenerator() {
    	Random random = new Random();
    	if(random.nextInt(10) % 2 == 0)
    		return "true";
    	return "false";
    }
}
