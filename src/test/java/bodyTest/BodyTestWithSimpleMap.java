package bodyTest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import entity.User;

public class BodyTestWithSimpleMap {

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

		HttpGet get = new HttpGet(BASE_ENDPOINT+"/users/andrejss88");
		response = client.execute(get);
		
		String jsonBody = EntityUtils.toString(response.getEntity());
		JSONObject jsonobject = new JSONObject(jsonBody);
		String valueoflogin = (String) getValueFor(jsonobject, User.LOGIN);
		System.out.println("The value of login is: "+valueoflogin);
	}
	
	@Test
	public void getCorrectId() throws ClientProtocolException, IOException {

		HttpGet get = new HttpGet(BASE_ENDPOINT+"/users/andrejss88");
		response = client.execute(get);
		
		String jsonBody = EntityUtils.toString(response.getEntity());
		JSONObject jsonobject = new JSONObject(jsonBody);
		Integer valueofID = (Integer) getValueFor(jsonobject, User.ID);
		System.out.println("The value of ID is: "+valueofID);
	}

	private Object getValueFor(JSONObject jsonobject, String key) {
		return jsonobject.get(key);
		
	}


}
