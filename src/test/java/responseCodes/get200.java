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

public class get200 {

	public static final String BASE_ENDPOINT = "https://api.github.com/";
	CloseableHttpClient client;
	CloseableHttpResponse response;

	@BeforeMethod
	public void beforeEveryTest() {
		client = HttpClientBuilder.create().build();
	}

	@AfterMethod
	public void afterEveryTest() throws IOException {
		client.close();
		response.close();
	}

	@Test
	public void get200StatusResponse() throws ClientProtocolException, IOException {

		HttpGet get = new HttpGet(BASE_ENDPOINT);

		response = client.execute(get);

		int statuscode = response.getStatusLine().getStatusCode();

		Assert.assertEquals(statuscode, 200);

		System.out.println("This is for new branch");
		System.out.println("This is for new branch1");
		System.out.println("This is for new branch2");
		System.out.println("This is for new branch3");

	}

	@Test
	public void get200RateLimitResponse() throws ClientProtocolException, IOException {

		HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");

		response = client.execute(get);

		int statuscode = response.getStatusLine().getStatusCode();

		Assert.assertEquals(statuscode, 200);
	}

	@Test
	public void get200SearchResponse() throws ClientProtocolException, IOException {

		HttpGet get = new HttpGet(BASE_ENDPOINT + "/search/repositories?q=java");

		response = client.execute(get);

		int statuscode = response.getStatusLine().getStatusCode();

		Assert.assertEquals(statuscode, 200);
	}
}
