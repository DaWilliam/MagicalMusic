package pyramid.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "track")
public class Track {
    @Id
    public int id;
    public String songName;
    public String artistName;
    public String image;

}
