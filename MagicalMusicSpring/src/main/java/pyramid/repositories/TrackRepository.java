package pyramid.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pyramid.models.Track;
import pyramid.models.User;

public interface TrackRepository extends JpaRepository<Track,Integer> {
	
	@Query(value="select * from Track track where track.user_id = ?1", nativeQuery = true)
	List<Track> findByUserId(@Param("user_id") int user_id);
	
}
