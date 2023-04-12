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
import entity.Rate_Limit;
import entity.User;
import responseUtils.ResponseUtils;

public class NestedBodyTestWithJacson {

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

		HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");
		response = client.execute(get);

		Rate_Limit newUser = ResponseUtils.unmarshallGeneric(response, Rate_Limit.class);
		assertEquals(newUser.getCoreLimit(), 60);
		assertEquals(newUser.getSearchLimit(), 10);

	}


}
