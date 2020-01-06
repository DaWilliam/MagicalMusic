package pyramid.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pyramid.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	List<User> findAllByEmail(String email);
	
	List<User> findAllByName(String name);
	
	@Query(value="SELECT * FROM Users user WHERE user.name = ?1 AND user.password = ?2", nativeQuery=true)
	List<User> findUserByLogin(@Param("username") String username, @Param("password") String password);
}
