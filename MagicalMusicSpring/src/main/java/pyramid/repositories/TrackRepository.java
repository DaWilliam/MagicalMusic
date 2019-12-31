package pyramid.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pyramid.models.Track;

public interface TrackRepository extends JpaRepository<Track,Integer> {
}
