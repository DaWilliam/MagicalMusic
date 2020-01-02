package pyramid.models.searchdata;

import com.google.gson.annotations.SerializedName;

public class Query {

	@SerializedName("#text")
	public String text;
	
	public String role;
	public int startPage;
	
}
