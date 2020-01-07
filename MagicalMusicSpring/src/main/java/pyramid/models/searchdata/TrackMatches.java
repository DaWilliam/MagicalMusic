package pyramid.models.searchdata;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TrackMatches {
	
	public TrackSearchData[] track;
	
	@SerializedName("@attr")
	public Attribute attr;
	
	
}
