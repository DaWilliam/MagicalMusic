import { Component, OnInit } from '@angular/core';
import { SongCardComponent } from '../song-card/song-card.component';

@Component({
  selector: 'app-songs-list',
  //templateUrl: './songs-list.component.html',
  styleUrls: ['./songs-list.component.css'],
  template: 
  `
    <app-song-card [song]=hello song [artist]=me></app-song-card>
  `
})
export class SongsListComponent implements OnInit {

  constructor() { }
  
  ngOnInit() {
    
  }

}
