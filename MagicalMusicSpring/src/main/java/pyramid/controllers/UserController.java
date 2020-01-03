package pyramid.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pyramid.models.Track;
import pyramid.models.User;
import pyramid.repositories.UserRepository;


@CrossOrigin(origins ="http://localhost:4200")	//	Should be the location that the angular server is hosted on
@RestController	//	This is a Web Service
@RequestMapping("/users")
public class UserController {
		
	@Autowired	//	Dependency Injection. Automatic Singleton Instance (from bean?)
	UserRepository userJpa;
	
	@PostMapping("/add")	//	Short-hand for RequestMapping("/add", RequestMethod.POST)
	public ResponseEntity<User> addUser(@RequestBody User user)	//	@RequestBody takes all the info as an object. @RequestParam takes data as pieces
	{
		User savedUser = userJpa.save(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<User> getUser(@PathVariable int id)
	{
		Optional<User> returnUser = userJpa.findById(id);
		if(returnUser.isPresent())
		{
			User guy = returnUser.get();	//	Hope this gets the actual user held in Option<User>
			return new ResponseEntity<User>(guy, HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getUser()
	{
		List<User> allUsers = userJpa.findAll();
		return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<User> delete(@PathVariable int id)
	{
		if(userJpa.existsById(id))
		{
			userJpa.deleteById(id);
			return new ResponseEntity<User>(HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
		
	@PutMapping("/update")
	public ResponseEntity<User> update(@RequestBody User userIn)
	{
		Optional<User> updatedUser = userJpa.findById(userIn.getId());
		if(updatedUser.isPresent())
		{
			User user = updatedUser.get();
			user.updateValues(userIn.getName(), userIn.getPassword(), userIn.getImage());	//	No email b/c prompt says you cannot update email
			userJpa.save(user);
			return new ResponseEntity<User>(HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	
    // Login
    @PostMapping(value = "/login")
    public ResponseEntity<Track> getTrack(@RequestBody User user){

    	if(user.getName().equals("Will") && user.getPassword().equals("isAwesome"))
    	{
    		System.out.println("DAMN WILL IS AWESOME");
    		return new ResponseEntity<Track>(HttpStatus.OK);
    	}
    	
    	System.out.println("YOU PUT THE WRONG INFO");
        return new ResponseEntity<Track>(HttpStatus.NOT_FOUND);
    }
    
    // Register
    //@Query("SELECT u FROM users u WHERE u.email = ?2")
    @PostMapping(value = "/register")
    public ResponseEntity<Track> register(@RequestBody User user){
    	
    	List<User> queriedUsers = userJpa.findAllByEmail(user.getEmail());
    	System.out.println("Count: " + queriedUsers.size());
    	
    	System.out.println("Database registering");
    	
    	if(userJpa.count() != 0)
    	{
    		System.out.println("Failed Registration");
    		return new ResponseEntity<Track>(HttpStatus.CONFLICT);	//	Email already exists    		 
    	}
    	System.out.println("Succeeded Registration");
    	userJpa.save(user);    	    	   
        return new ResponseEntity<Track>(HttpStatus.OK);
    }
}
