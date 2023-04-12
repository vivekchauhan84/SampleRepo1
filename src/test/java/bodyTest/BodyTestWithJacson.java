package bodyTest;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import entity.NotFound;
import entity.User;
import responseUtils.ResponseUtils;

public class BodyTestWithJacson {

	private static final String BASE_ENDPOINT = "https://api.github.com";
	CloseableHttpClient client;
	CloseableHttpResponse response;

	@BeforeMethod
	public void setup() {
		client = HttpClientBuilder.create().build();

	}

	@AfterMethod
	public void closeResources() throws IOException {

		client.close();
		response.close();
	}

	@Test
	public void getCorrectLogin() throws ClientProtocolException, IOException {

		HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");
		response = client.execute(get);

		User newUser = ResponseUtils.unmarshallGeneric(response, User.class);
		assertEquals(newUser.getLogin(), "andrejss88");

	}

	@Test
	public void getCorrectId() throws ClientProtocolException, IOException {

		HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");
		response = client.execute(get);

		User newUser=ResponseUtils.unmarshallGeneric(response, User.class);
		assertEquals(newUser.getId(), 11834443);
	}

	@Test
	public void notFoundMessageIsCorrect() throws ClientProtocolException, IOException {

		HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");
		response = client.execute(get);

		NotFound newUser=ResponseUtils.unmarshallGeneric(response, NotFound.class);
		assertEquals(newUser.getMessage(), null);
	}

}
