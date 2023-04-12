package responseCodes;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckContentType {

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
	public void get404asReponse() throws ClientProtocolException, IOException {
		
		HttpGet get = new HttpGet(BASE_ENDPOINT);
		response = client.execute(get);
		ContentType ct = ContentType.getOrDefault(response.getEntity());
		assertEquals(ct.getMimeType(), "application/json");
		
	}
}
