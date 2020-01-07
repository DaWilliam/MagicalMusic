package pyramid.models.searchdata;

import com.google.gson.annotations.SerializedName;

public class Body_Search_Song_With_Artist {
	
	Results results;
	
	@SerializedName("opensearch:totalResults")
	public int totalResults;
	
	@SerializedName("opensearch:startIndex")
	public int startIndex;
	
	@SerializedName("opensearch:itemsPerPage")
	public int itemsPerPage;
	
	public TrackMatches trackmatches;
	
	@SerializedName("@attr")
	public Attr attr;
}
