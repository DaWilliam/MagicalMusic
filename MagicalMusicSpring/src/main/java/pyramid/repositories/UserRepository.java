package pyramid.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pyramid.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	List<User> findAllByEmail(String email);
}
