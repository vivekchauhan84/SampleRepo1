package entity;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rate_Limit {

	private int corelimit;
	private int searchlimit;

	public int getCoreLimit() {
		return corelimit;
	}

	public int getSearchLimit() {
		return searchlimit;
	}

	@SuppressWarnings("unchecked")
	@JsonProperty("resources")
	public void unmarshallNested(Map<String, Object> resources) {

		Map<String, Integer> core = (Map<String, Integer>) resources.get("core");
		corelimit = core.get("limit");

		Map<String, Integer> search = (Map<String, Integer>) resources.get("search");
		searchlimit = search.get("limit");
	}
}
