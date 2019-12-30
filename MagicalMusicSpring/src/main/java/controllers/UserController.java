package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import pyramid.models.User;
import pyramid.repositories.UserRepository;


@CrossOrigin(origins ="localhost:4200")	//	Should be the location that the angular server is hosted on
@RestController	//	This is a Web Service
@RequestMapping("/users")
public class UserController {
		
	@Autowired	//	Dependency Injection. Automatic Singleton Instance (from bean?)
	UserRepository userJpa;
	
	@PostMapping("/add")	//	Short-hand for RequestMapping("/add", RequestMethod.POST)
	public ResponseEntity<User> addUser(@RequestBody User user)
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
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<User> update(@RequestParam int id, @RequestParam String name, @RequestParam String password, @RequestParam String email, @RequestParam byte[] image)
	{
		User updateUser = new User(id, name, password, email, image);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
}
