package pyramid.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	int id;
	
	String name;
	String password;
	String email;	
	byte[] image;
	
	public User(int id, String name, String password, String email, byte[] image) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.image = image;
	}
	
	public User()
	{
		
	}

	public void updateValues(String name, String password, byte[] image)
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
		
}
