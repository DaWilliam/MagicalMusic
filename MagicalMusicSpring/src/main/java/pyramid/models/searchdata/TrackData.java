package pyramid.models.searchdata;

import com.google.gson.annotations.SerializedName;


public class TrackData {


	public String name;
	public int playcount;
	public int listeners;
	public String mbid;
	public String url;
	public int streamable;
	
	public ArtistData artist;
	public Image[] image;
	
	@SerializedName("@attr")
	public Attribute attr;
}
