package responseCodes;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import responseUtils.ResponseUtils;

public class Options204 {

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

	@Test
	public void optionsReturnsCorrectMethodsList() throws ClientProtocolException, IOException {

		String header = "Access-Control-Allow-methods";
		String expectedReply = "GET, POST, PATCH, PUT, DELETE";

		HttpOptions request = new HttpOptions(BASE_ENDPOINT);
		response = client.execute(request);
		String actualValue = ResponseUtils.getheader(response, header);

		assertEquals(actualValue, expectedReply);
	}
}
