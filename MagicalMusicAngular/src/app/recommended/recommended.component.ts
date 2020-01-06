import { Component, OnInit } from '@angular/core';
import { TrackserviceService } from '../trackservice.service';
import { Track } from '../track';

@Component({
  selector: 'app-recommended',
  templateUrl: './recommended.component.html',
  styleUrls: ['./recommended.component.css']
})
export class RecommendedComponent implements OnInit {
  tracks : Track[];
  constructor(private Trackservice:TrackserviceService) { }

  ngOnInit() {
    this.Trackservice.findRecommended().subscribe(
      (data : Track[]) => { 
        console.log("DataLen: " + data.length);
        console.log("Data: " + data.values);
        this.tracks = data
        this.tracks.forEach(function(value) {
          console.log(value);
        })
        
      }, error => {
        console.log(error);
      });
  }

}
