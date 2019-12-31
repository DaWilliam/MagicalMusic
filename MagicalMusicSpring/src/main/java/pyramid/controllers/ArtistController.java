package pyramid.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pyramid.models.Artist;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;


@CrossOrigin(origins ="localhost:4200")	//	Should be the location that the angular server is hosted on
@RestController    //	This is a Web Service


//https://ws.audioscrobbler.com/2.0/?method=artist.gettoptracks&artist=ice+nine+kills&api_key=c781cfdd6742edee32e9c8483f67daee&format=json
@RequestMapping("/artist")
public class ArtistController {

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

        ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);

//        Body artist1 = gson.fromJson(response.toString(), Body.class);

/*---------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------
-------------------------------------------START TESTING---------------------------------------------------
-----------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------*/




        String inline = "";
        try {
            URL url1 = new URL("https://ws.audioscrobbler.com/2.0/?method=artist.gettoptracks&artist=" + artist + "&api_key=" +apiKey + "&format=json");

            HttpURLConnection conn =(HttpURLConnection) url1.openConnection();

            conn.setRequestMethod("GET");
            conn.connect();


            int responseCode = conn.getResponseCode();
            if(responseCode != 200){
                throw new RuntimeException("HttpRsponseCode: " + responseCode );
            } else{
                Scanner sc = new Scanner(url1.openStream());
                while (sc.hasNext()) {
                    inline += sc.nextLine();
                }
                System.out.println(inline);
                sc.close();


//                JSONParser parser = new JSONParser();
//                JSONObject jobj = (JSONObject) parser.parse(inline);
//
//                System.out.println(jobj);


            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        int i = inline.indexOf("name");
        int j = inline.indexOf("mbid");
//        System.out.println(inline.substring(i,j));




/*---------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------
-------------------------------------------END TESTING-----------------------------------------------------
-----------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------*/







//        System.out.println(response);
//
//        System.out.println(response.getClass());


        return new ResponseEntity(inline.substring(i,j-2), HttpStatus.OK);
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




    }
