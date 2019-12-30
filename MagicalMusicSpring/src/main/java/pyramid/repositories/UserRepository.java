package pyramid.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pyramid.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
