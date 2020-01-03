package pyramid.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "track")
public class Track {
    @Id
    public int id;
    public String songName;
    public String artistName;
    public String image;    
    
    public Track()
    {
    	
    }
    
	public Track(int id, String songName, String artistName, String image) {
		super();
		this.id = id;
		this.songName = songName;
		this.artistName = artistName;
		this.image = image;
	}


}
