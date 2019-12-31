package pyramid.models.searchdata;

import com.google.gson.annotations.SerializedName;

public class TopTracks {
	
	public TrackData[] track;
	
	@SerializedName("@attr")
	public ArtistPage attr;

}
