package pyramid.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pyramid.models.Artist;


@CrossOrigin(origins ="localhost:4200")	//	Should be the location that the angular server is hosted on
@RestController    //	This is a Web Service


//https://ws.audioscrobbler.com/2.0/?method=artist.gettoptracks&artist=ice+nine+kills&api_key=c781cfdd6742edee32e9c8483f67daee&format=json
@RequestMapping("/artist")
public class ArtistController {

    @Value("${api.key}")
    private String apiKey;


    //find artist
    @GetMapping(value = "/{artist}", produces= "application/json")
    public ResponseEntity<Artist> getArtist(@PathVariable String artist)
    {

        if(artist.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if(artist.contains(" ")){
            artist.replaceAll(" ", "+");
        }

        String url = "https://ws.audioscrobbler.com/2.0/?method=artist.gettoptracks&artist=" + artist + "&api_key=" +apiKey + "&format=json" ;



        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println(response);

        System.out.println(response.getClass());


        return new ResponseEntity(response, HttpStatus.OK);
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

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println(response);

        System.out.println(response.getClass());


        return new ResponseEntity(response, HttpStatus.OK);
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

        return new ResponseEntity(response,HttpStatus.OK);
    }

}
