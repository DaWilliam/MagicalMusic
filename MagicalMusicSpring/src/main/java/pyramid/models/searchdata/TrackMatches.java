package pyramid.models.searchdata;

import com.google.gson.annotations.SerializedName;

public class TrackMatches {
	
	public TrackSearchData[] track;
	
	@SerializedName("@attr")
	public Attribute attr;
}
