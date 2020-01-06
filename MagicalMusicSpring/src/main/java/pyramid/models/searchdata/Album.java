package pyramid.models.searchdata;

import com.google.gson.annotations.SerializedName;

public class Album {
	public String artist;
	public String title;
	public String mbid;
	public String url;
	public Image[] image;
	@SerializedName("@attr")
	public Attr attr;
}
