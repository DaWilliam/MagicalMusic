package pyramid.models.searchdata;

import java.net.URL;
import java.util.HashMap;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Track {
	
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
