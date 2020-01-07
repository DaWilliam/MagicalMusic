import { Component, OnInit } from '@angular/core';
import { GlobalDataService } from '../global-data.service';
import { Track } from '../track';
import { TrackserviceService } from '../trackservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.css']
})
export class FavoritesComponent implements OnInit {
  
  tracks : Track[];
  
  constructor(private trackService : TrackserviceService, private router: Router, private globalDataService : GlobalDataService) {
    router.events.subscribe((val) => {
      console.log("route changed from favorites");
      this.findFavorites();
      // see also 
      //console.log(val instanceof NavigationEnd) 
    });
  }

  ngOnInit() {
      
  }

  findFavorites() {
      location.reload;
      console.log("Finding Favorites from favComp")
      if(this.globalDataService.user == null)
          return;

      this.trackService.findFavorites(this.globalDataService.user).subscribe(
        (data : Track[]) => {
            this.tracks = data;
            console.log("populating favorites " + data.length);
        }, error => {
          console.log("Error pulling favorites")
        }
      )
  }
  

}
