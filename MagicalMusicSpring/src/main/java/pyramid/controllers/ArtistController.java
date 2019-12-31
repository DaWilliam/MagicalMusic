package pyramid.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pyramid.models.Artist;
import pyramid.models.Track;
import pyramid.models.searchdata.Body;
import pyramid.repositories.TrackRepository;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")	//	Should be the location that the angular server is hosted on
@RestController    //	This is a Web Service
//https://ws.audioscrobbler.com/2.0/?method=artist.gettoptracks&artist=ice+nine+kills&api_key=c781cfdd6742edee32e9c8483f67daee&format=json
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    TrackRepository tr;

    @Value("${api.key}")
    private String apiKey;


    //find artist
    @GetMapping(value = "/{artist}")
    public ResponseEntity<Artist> getArtist(@PathVariable String artist) {

        if(artist.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if(artist.contains(" ")){
            artist.replaceAll(" ", "+");
        }

        String url = "https://ws.audioscrobbler.com/2.0/?method=artist.gettoptracks&artist=" + artist + "&api_key=" +apiKey + "&format=json" ;

        Gson gson = new Gson();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        String bodyJson = response.getBody();
        
        System.out.println(bodyJson);
        
        Body body = gson.fromJson(bodyJson, Body.class);
        Artist responseArtist = new Artist(0, body.toptracks.attr.artist, body.toptracks.attr.total);
        
        //System.out.println(response.getClass());
        System.out.println("CONVERTED");

        return new ResponseEntity<Artist>(responseArtist, HttpStatus.OK);


    }


    //find all songs with same name
    @GetMapping(value = "/song/{song}", produces= "application/json")
    public ResponseEntity<Artist> getSong(@PathVariable String song)
    {

        if(song.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if(song.contains(" ")){
            song.replaceAll(" ", "+");
        }

        String url ="https://ws.audioscrobbler.com/2.0/?method=track.search&track=" + song  + "&api_key=" +apiKey + "&format=json"  ;



        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
        System.out.println(response);

        System.out.println(response.getClass());


        return new ResponseEntity(response.getBody(), HttpStatus.OK);
    }

    //find song with artist
    @GetMapping(value = "/{artist}/{song}")
    public ResponseEntity<Artist> getSongWArtist(@PathVariable String artist, @PathVariable String song)
    {

        if(artist.isEmpty() || song.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if(artist.contains(" ")){
            artist.replaceAll(" ", "+");
        }
        if(song.contains(" ")){
            song.replaceAll(" ","+");
        }

//        https://ws.audioscrobbler.com/2.0/?method=track.search&track=Believe&api_key=YOUR_API_KEY&format=json

        String url = "https://ws.audioscrobbler.com/2.0/?method=track.search&artist=" + artist + "&track=" + song  + "&api_key=" +apiKey + "&format=json" ;


        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
        System.out.println(response);

        return new ResponseEntity(response.getBody(),HttpStatus.OK);
    }


        //find geo top artist
        @GetMapping(value = "/{geo}/artist")
        public ResponseEntity<Artist> getTopArtist(@PathVariable String geo) {

            if(geo.isEmpty()){
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }

            if(geo.contains(" ")){
                geo.replaceAll(" ", "+");
            }

            String url = "https://ws.audioscrobbler.com/2.0/?method=geo.gettopartists&country=" + geo + "&api_key=" + apiKey + "&format=json" ;

            Gson gson = new Gson();

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);


            return new ResponseEntity(response.getBody(), HttpStatus.OK);
        }


    //find geo top tracks
    @GetMapping(value = "/{geo}/tracks")
    public ResponseEntity<Artist> getTopTracks(@PathVariable String geo) {

        if(geo.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if(geo.contains(" ")){
            geo.replaceAll(" ", "+");
        }

        String url = "https://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&country=" + geo + "&api_key=" + apiKey + "&format=json" ;
        Gson gson = new Gson();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);


        return new ResponseEntity(response.getBody(), HttpStatus.OK);
    }

    // add track
    @GetMapping(value = "/add")
    public ResponseEntity<Track> addTrack(@RequestParam Track track){
    	System.out.println("Added Track from Spring");
    	track.id = (int)tr.count() + 1;
    	
        tr.save(track);
        return new ResponseEntity<Track>(HttpStatus.OK);
    }

    // find track by id
    @GetMapping(value = "/track/{id}")
    public ResponseEntity<Track> getTrack(@PathVariable int id){


        if(tr.existsById(id))
        {
            tr.findById(id);
            return new ResponseEntity<Track>(HttpStatus.OK);
        }

        return new ResponseEntity<Track>(HttpStatus.NOT_FOUND);
    }


    //find all tracks
    @GetMapping("/getAll")
    public ResponseEntity<List<Track>> getTracks()
    {
        List<Track> allTracks = tr.findAll();
        return new ResponseEntity<List<Track>>(allTracks, HttpStatus.OK);
    }


    //delete track
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Track> delete(@PathVariable int id)
    {
        if(tr.existsById(id))
        {
            tr.deleteById(id);
            return new ResponseEntity<Track>(HttpStatus.OK);
        }
        return new ResponseEntity<Track>(HttpStatus.NOT_FOUND);
    }


}
