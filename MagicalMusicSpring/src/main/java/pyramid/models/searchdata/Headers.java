package pyramid.models.searchdata;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class Headers {
	@SerializedName("Server")
	public String[] server;
	
	@SerializedName("Date")
	public Date[] date;
	
	@SerializedName("Content-Type")
	public String[] contentType;
	
	@SerializedName("Connection")
	public String[] connection;
	
	@SerializedName("content-length")
	public String[] contentLength;
	
	@SerializedName("Access-Control-Allow-Methods")
	public String[] acessCAM;
}
