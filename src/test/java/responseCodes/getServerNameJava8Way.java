package responseCodes;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import responseUtils.ResponseUtils;

public class getServerNameJava8Way {

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
		String headerValue = getheaderJava8Way(response, "Server");
		assertEquals(headerValue, "GitHub.com");
	}

	public static String getheaderJava8Way(CloseableHttpResponse response, final String headerName) {

		List<Header> httpheaders = Arrays.asList(response.getAllHeaders());

		Header matchedHeader = httpheaders.stream().filter(header -> headerName.equalsIgnoreCase(header.getName()))
				.findFirst().orElseThrow(() -> new RuntimeException("Did not find the header: " + headerName));

		return matchedHeader.getValue();
	}
}
