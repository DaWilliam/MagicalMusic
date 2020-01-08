package pyramid.models;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	@JsonIgnore
	@OneToMany (fetch = FetchType.LAZY, mappedBy="user")	//	Saying in the other class, in this case 'Track' there is a variable named user
	public List<Track> tracks;
	
	String name;
	String password;
	String email;	
	String image;
	
	File imageFile;
	
	public User(int id, String name, String password, String email, String image, List<Track> tracks, File imageFile) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.image = image;
		this.tracks = tracks;
		this.imageFile = imageFile;
	}
	
	public User()
	{
		
	}

	
	public File getImageFile() {
		return imageFile;
	}

	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	public void updateValues(String name, String password, String image)
	{
		this.name = name;
		this.password = password;
		this.image = image;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
		
}
