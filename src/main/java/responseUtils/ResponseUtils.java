package responseUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import entity.User;

public class ResponseUtils {

	public static String getheader(CloseableHttpResponse response, String headerName) {

		Header[] headers = response.getAllHeaders();
		List<Header> httpheaders = Arrays.asList(headers);
		String returnHeaderName = "";
		for (Header header : httpheaders) {

			if (headerName.equalsIgnoreCase(header.getName())) {
				returnHeaderName = header.getValue();
			}
		}

		if (returnHeaderName.isEmpty()) {
			throw new RuntimeException("Did not find the header: " + headerName);
		}

		return returnHeaderName;
	}

	//Use the generic method below
	public static User unmarshall(CloseableHttpResponse response2, Class<User> class1) throws ParseException, IOException {
		String jsonBody = EntityUtils.toString(response2.getEntity());
		return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.readValue(jsonBody, class1);

	}
	public static <T> T unmarshallGeneric(CloseableHttpResponse response, Class<T> class1) throws ParseException, IOException {
		String jsonBody = EntityUtils.toString(response.getEntity());
		return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.readValue(jsonBody, class1);

	}

}
