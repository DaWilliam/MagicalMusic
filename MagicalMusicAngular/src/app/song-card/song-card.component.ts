import { Component, OnInit, Input, Output } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { TrackserviceService } from '../trackservice.service';
import { Track } from '../track';
import { Song } from '../song';
import { GlobalDataService } from '../global-data.service';
import { EventEmitter } from '@angular/core';
@Component({
  selector: 'app-song-card',
  templateUrl: './song-card.component.html',
  styleUrls: ['./song-card.component.css']
})

export class SongCardComponent implements OnInit {
  //  FormGroup Did You Know: 
  //    Value of data/favoriteForm is the variable names of the HTML page
  @Input() id : number;
  @Input() song : string;
  @Input() artist : string;
  @Input() image : string;
  @Input() isFavorited : boolean;
  @Output() removeEvent = new EventEmitter();

  constructor(private sTrack : TrackserviceService, private globalDataService : GlobalDataService) { 
    //this.song = "This is the longest Song-Name You have ever seen";
    this.song = "Song-Name";
    this.artist = "Artist-Name";      
    console.log("SONG: " + this.song);
  }

  ngOnInit() {
    console.log("SONG2: " + this.song);
  }

  onClick() {
      if(!this.isFavorited)      
          this.addFavorite();
      else
          this.removeFavorite();
      
  }

  addFavorite() {        
      this.isFavorited = true;

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

  removeFavorite() {      
      this.isFavorited = false;

      var track = new Track();
      track.id = this.id;
      track.songName = this.song;
      track.artistName = this.artist;
      track.image = this.image;
      
      this.sTrack.removeTrack(track).subscribe(
        data  => {                   
          this.globalDataService.favTracks = data;          
          console.log("Removed Song");
          console.log("About to emit")
          this.removeEvent.emit(null);
          console.log("Emitted");
        }, error => {
          console.log("WE COULDN'T REMOVE YOUR SONG FOOL");
        })
        
        
  }
}
