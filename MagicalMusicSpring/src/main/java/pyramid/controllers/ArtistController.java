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
import pyramid.models.User;
import pyramid.models.searchdata.Body_Search_Artist;
import pyramid.models.searchdata.Body_Search_Img;
import pyramid.models.searchdata.Body_Search_Song;
import pyramid.models.searchdata.Body_Search_Song_With_Artist;
import pyramid.models.searchdata.TrackData;
import pyramid.models.searchdata.TrackSearchData;
import pyramid.repositories.TrackRepository;
import pyramid.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")	//	Should be the location that the angular server is hosted on
@RestController    //	This is a Web Service
//https://ws.audioscrobbler.com/2.0/?method=artist.gettoptracks&artist=ice+nine+kills&api_key=c781cfdd6742edee32e9c8483f67daee&format=json
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    TrackRepository tr;

    @Autowired	//	Dependency Injection. Automatic Singleton Instance (from bean?)
	UserRepository userJpa;
    
    @Value("${api.key}")
    private String apiKey;

    Gson gson = new Gson();

    //find artist
    @GetMapping(value = "/{artist}")
    public ResponseEntity<List<Track>> getArtist(@PathVariable String artist) {

        if(artist.isEmpty()){
            return new ResponseEntity<List<Track>>(HttpStatus.NOT_FOUND);
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
        
        Body_Search_Artist body = gson.fromJson(bodyJson, Body_Search_Artist.class);
        
        TrackData[] trackSearchData = body.toptracks.track;
        List<Track> tracks =  ConvertTrackData(trackSearchData);
        
        return new ResponseEntity<List<Track>>(tracks, HttpStatus.OK);
    }


    //find all songs with same name
    @GetMapping(value = "/song/{song}", produces= "application/json")
    public ResponseEntity<TrackSearchData[]> getSong(@PathVariable String song)
    {
        System.out.println("HITTING SONG");
        if(song.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if(song.contains(" ")){
            song.replaceAll(" ", "+");
        }

        String url ="https://ws.audioscrobbler.com/2.0/?method=track.search&track=" + song  + "&api_key=" +apiKey + "&format=json"  ;

        Gson gson = new Gson();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        String bodyJson = response.getBody();

        System.out.println(bodyJson);

        Body_Search_Song body = gson.fromJson(bodyJson, Body_Search_Song.class);
        TrackSearchData[] trackModelObj = body.results.trackmatches.track;
        
        //	Create tracks from all the model data
        Track[] tracks = new Track[trackModelObj.length];
        for(int i = 0; i < tracks.length; i++)
        {
        	tracks[i] = new Track();
        	tracks[i].artistName = trackModelObj[i].artist;
        	tracks[i].songName = trackModelObj[i].name;
        	
        	tracks[i].image = getImg(tracks[i].artistName, tracks[i].songName);
//        	if(tracks[i].image.isEmpty())
//        		tracks[i].image = "https://lastfm.freetls.fastly.net/i/u/174s/2a96cbd8b46e442fc41c2b86b821562f.png";
        	
        	//	If it has an image
//        	if(trackModelObj[i].image.length > 2)
//        		tracks[i].image = trackModelObj[i].image[2].text;
        }
        //track.songName = body.results.trackmatches.track[0].name;
        //Artist responseArtist = new Artist(0, "Will", 6);

        return new ResponseEntity(tracks, HttpStatus.OK);
    }

    //find song with artist
    @GetMapping(value = "/{artist}/{song}")
    public ResponseEntity<List<Track>> getSongWArtist(@PathVariable String artist, @PathVariable String song)
    {

        if(artist.isEmpty() || song.isEmpty()){
            return new ResponseEntity<List<Track>>(HttpStatus.NOT_FOUND);
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

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        String bodyJson = response.getBody();

        System.out.println(bodyJson);

        Body_Search_Song body = gson.fromJson(bodyJson, Body_Search_Song.class);
        TrackSearchData[] trackSearchData = body.results.trackmatches.track;
        List<Track> tracks =  ConvertTrackSearchData(trackSearchData);
        
        return new ResponseEntity<List<Track>>(tracks, HttpStatus.OK);

//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("Track: " + track.songName + System.lineSeparator()
//                + " | Artist: " + body.results.trackmatches.track[0].artist + System.lineSeparator()
//                + " | Listeners: " + body.results.trackmatches.track[0].listeners
//                + " | ImageURL: " + body.results.trackmatches.track[0].image[0].text);


//      return new ResponseEntity(track, HttpStatus.OK);
    }

    //	Gets the image for the song
    public String getImg(String artist, String song){
    	System.out.println("Getting image");
    	if(artist.isEmpty() || song.isEmpty()){
            return "";
        }
    	if(artist.contains(" ")){
            artist.replaceAll(" ", "+");
        }
        if(song.contains(" ")){
            song.replaceAll(" ","+");
        }
        
        String url = "https://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=c781cfdd6742edee32e9c8483f67daee&artist="+artist+"&track="+song+"&format=json";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        System.out.println("SONG IMAGE RESPONSE: " + response);
        
        String bodyJson = response.getBody();
        if(bodyJson.substring(2, 7).equals("error"))
        	return "";

        Body_Search_Img body = gson.fromJson(bodyJson, Body_Search_Img.class);
        System.out.println("Converted Json");
        String imgURL="";
        try {
        	imgURL = body.track.album.image[3].text;
        }catch(NullPointerException e) {
        	imgURL = "https://lastfm.freetls.fastly.net/i/u/174s/2a96cbd8b46e442fc41c2b86b821562f.png";
        }
        
        return imgURL;
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


        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);


        String bodyJson = response.getBody();

        System.out.println(bodyJson);       

        Body_Search_Artist body = gson.fromJson(bodyJson, Body_Search_Artist.class);
        Artist responseArtist = new Artist(0, body.toptracks.attr.artist, body.toptracks.attr.total);

        //System.out.println(response.getClass());
        System.out.println("CONVERTED");


        return new ResponseEntity<Artist>(responseArtist, HttpStatus.OK);

    }


    //find geo top tracks
    @GetMapping(value = "/{geo}/tracks")
    public ResponseEntity<List<Track>> getTopTracks(@PathVariable String geo) {

        if(geo.isEmpty()){
            return new ResponseEntity<List<Track>>(HttpStatus.NOT_FOUND);
        }

        if(geo.contains(" ")){
            geo.replaceAll(" ", "+");
        }

        Gson gson = new Gson();

        String url = "https://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&country=" + geo + "&api_key=" + apiKey + "&format=json" ;
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        String bodyJson = response.getBody();

        System.out.println(bodyJson);

        Body_Search_Song body = gson.fromJson(bodyJson, Body_Search_Song.class);
        pyramid.models.searchdata.Track[] trackData = body.tracks.track;
        List<Track> tracks =  ConvertTrackModel(trackData);
        
        return new ResponseEntity<List<Track>>(tracks, HttpStatus.OK);

    }

    @GetMapping(value = "/toptracks")
    public ResponseEntity<Track[]> getTopTracks() {

        Gson gson = new Gson();

        String url = "https://ws.audioscrobbler.com/2.0/?method=tag.gettoptracks&api_key=c781cfdd6742edee32e9c8483f67daee&format=json" ;
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        String bodyJson = response.getBody();

        System.out.println(bodyJson);

        Body_Search_Song body = gson.fromJson(bodyJson, Body_Search_Song.class);
        pyramid.models.searchdata.Track[] tracks = body.tracks.track;
        Track[] tracklist = new Track[tracks.length];
        for(int i = 0; i< tracklist.length; i++) {
        	tracklist[i]=new Track();
        	tracklist[i].songName = tracks[i].name;
        	tracklist[i].artistName = tracks[i].artist.name;
        	tracklist[i].image = getImg(tracklist[i].artistName, tracklist[i].songName);
        }

        return new ResponseEntity(tracklist, HttpStatus.OK);

    }
    
    // add track
    @PostMapping(value = "/add/{id}")
    public ResponseEntity<Track> addTrack(@RequestBody Track track, @PathVariable int id){
    	System.out.println("Added Track from Spring");
    	    	
    	Optional<User> currentUser = userJpa.findById(id);
    	track.id = (int)tr.count() + 1;  	
    	track.user = currentUser.get();
    	
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
    @DeleteMapping(value = "/delete/{user_id}")
    public ResponseEntity<List<Track>> delete(@PathVariable int user_id, @RequestParam String id, @RequestParam String artist, @RequestParam String song, @RequestParam String imageURL)
    {
    	System.out.println("Trying to delete");
    	int songId = Integer.parseInt(id);
    	
    	Track scoutTrack = tr.findByUserAndSongId(user_id, songId);//new Track(songId, song, artist, imageURL);
    	List<Track> favoriteList = tr.findByUserId(user_id);
    	if(favoriteList.contains(scoutTrack))
    	{
    		System.out.println("Deleted Track");
    		tr.delete(scoutTrack);
    		favoriteList.remove(scoutTrack);
    		return new ResponseEntity<List<Track>>(favoriteList, HttpStatus.OK);
    	}
    	
    	System.out.println("Track doesn't exist");
        return new ResponseEntity<List<Track>>(HttpStatus.NOT_FOUND);
    }

    
    @GetMapping("/favorites/{id}")
    public ResponseEntity<List<Track>> getUserFavorites(@PathVariable int id)
    {
    	List<Track> newTracks = tr.findByUserId(id);
    	System.out.println("Lenght: " + newTracks.size());
    	return new ResponseEntity<List<Track>>(newTracks, HttpStatus.OK);
    }
    
    public List<Track> ConvertTrackSearchData(TrackSearchData[] trackSearchData)
	{
		List<Track> tracks = new ArrayList<Track>();
		for(TrackSearchData tsData : trackSearchData)
		{
			System.out.println("Getting track search data: " + tsData.name);
			tracks.add(new Track(-1, tsData.name, tsData.artist, getImg( tsData.artist, tsData.name)));
		}
		return tracks;
	}
    
    public List<Track> ConvertTrackData(TrackData[] trackData)
	{
		List<Track> tracks = new ArrayList<Track>();
		for(TrackData tsData : trackData)
		{
			System.out.println("Getting track search data: " + tsData.name);
			tracks.add(new Track(-1, tsData.name, tsData.artist.name, getImg(tsData.artist.name, tsData.name)));
		}
		return tracks;
	}
    
    public List<Track> ConvertTrackModel(pyramid.models.searchdata.Track[] modelTracks)
    {
    	List<Track> tracks = new ArrayList<Track>();
		for(pyramid.models.searchdata.Track tsData : modelTracks)
		{
			System.out.println("Getting track search data: " + tsData.name);
			tracks.add(new Track(-1, tsData.name, tsData.artist.name, getImg(tsData.artist.name, tsData.name)));
		}
		return tracks;
    }
}


