package responseCodes;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Get401 {

	private static final String BASE_ENDPOINT = "https://api.github.com";
	CloseableHttpClient client;
	CloseableHttpResponse response;

	@BeforeMethod
	public void initialization() {
		client = HttpClientBuilder.create().build();

	}

	@AfterMethod
	public void afterEverymethod() throws IOException {

		client.close();
		response.close();
	}

	@Test(dataProvider = "endpoints")
	public void userReturns401(String endpoint) throws ClientProtocolException, IOException {

		HttpGet get = new HttpGet(BASE_ENDPOINT + endpoint);
		response = client.execute(get);
		assertEquals(response.getStatusLine().getStatusCode(), 401);
	}

	@DataProvider
	public Object[][] endpoints() {

		return new Object[][] { { "/user" }, { "/user/followers" }, { "/notifications" } };
	}
}
