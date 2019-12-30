import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-song-card',
  templateUrl: './song-card.component.html',
  styleUrls: ['./song-card.component.css']
})
export class SongCardComponent implements OnInit {

  song : string;
  artist : string;
  image : HTMLImageElement;

  constructor() { 
    //this.song = "This is the longest Song-Name You have ever seen";
    this.song = "Song-Name";
    this.artist = "Artist-Name";
  }

  ngOnInit() {

  }

}
