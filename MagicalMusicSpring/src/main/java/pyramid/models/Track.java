package pyramid.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "track")
public class Track {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int id;
    
    public String songName;
    public String artistName;
    public String image;    
    
    
    @ManyToOne //@JoinColumn(name = "tracks")
    public User user;
    
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
