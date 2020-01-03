import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { TrackserviceService } from '../trackservice.service';
import { Track } from '../track';
import { Song } from '../song';
@Component({
  selector: 'app-song-card',
  templateUrl: './song-card.component.html',
  styleUrls: ['./song-card.component.css']
})

export class SongCardComponent implements OnInit {
  //  FormGroup Did You Know: 
  //    Value of data/favoriteForm is the variable names of the HTML page
  @Input() song : string;
  @Input() artist : string;
  
  constructor(private sTrack : TrackserviceService) { 
    //this.song = "This is the longest Song-Name You have ever seen";
    this.song = "Song-Name";
    this.artist = "Artist-Name";    
  }

  ngOnInit() {

  }

  addFavorite() {        
      var track = new Track();
      track.songName = "The William Song";
      track.artistName = "William";
      track.image = "YOUR IMAGE GOES HERE";

      this.sTrack.addTrack(track).subscribe(
        data => {                    
          // track.songName = data.songName;
          // track.artistName = data.artistName;
          // track.image = data.image;
          console.log("ADDED SONG");
        }, error => {
          console.log("WE COULDN'T FAVORITE YOUR SONG FOOL");
        }
      )
  }
}
