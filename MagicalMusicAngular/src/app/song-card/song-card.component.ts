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
  @Input() image : string;
  
  constructor(private sTrack : TrackserviceService) { 
    //this.song = "This is the longest Song-Name You have ever seen";
    this.song = "Song-Name";
    this.artist = "Artist-Name";      
    console.log("SONG: " + this.song);
  }

  ngOnInit() {
    console.log("SONG2: " + this.song);
  }

  addFavorite() {        
      var track = new Track();
      track.songName = this.song;
      track.artistName = this.artist;
      track.image = this.image;
      
      this.sTrack.addTrack(track).subscribe(
        data => {                   
          console.log("ADDED SONG");
        }, error => {
          console.log("WE COULDN'T FAVORITE YOUR SONG FOOL");
        }
      )
  }

  centered = false;
  disabled = false;
  unbounded = false;

  radius: number;
  color: string;
}
