package pyramid.models.searchdata;

import com.google.gson.annotations.SerializedName;

public class TopTracks {
	
	public TrackData[] track;
	public TrackSearchData[] trackSearchData;
	public Tracks[] tracks;
	
	@SerializedName("@attr")
	public ArtistPage attr;




}
