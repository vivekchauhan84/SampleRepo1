package responseCodes;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class get404 {

	private static final String BASE_ENDPOINT = "https://api.github.com/nonexistingurl";
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

	@Test
	public void get404asReponse() throws ClientProtocolException, IOException {
		
		HttpGet get = new HttpGet(BASE_ENDPOINT);
		response = client.execute(get);
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	}
}
