package pyramid.models.searchdata;

import com.google.gson.annotations.SerializedName;

public class Results {
	
	@SerializedName("opensearch:Query")
	public Query query;
	@SerializedName("opensearch:totalResults")
	public int totalResults;
	@SerializedName("opensearch:startIndex")
	public int startIndex;
	@SerializedName("opensearch:itemsPerPage")
	public int itemsPerPage;
	
	public TrackMatches trackmatches;
	
	public String statusCode;
	public int statusCodeValue;
}
