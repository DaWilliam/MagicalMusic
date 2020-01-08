import { Component } from '@angular/core';
import { GlobalDataService } from './global-data.service';
import { TrackserviceService } from './trackservice.service';
import { Track } from './track';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'MagicalMusicAngular';
  url = "";
  favTracks : Track[];

  constructor(private trackService : TrackserviceService, public globalDataService : GlobalDataService) {}

  onChange() {
    var reader = new FileReader();
    reader.onload = (event : any) => {
      this.url = event.data;
    };
    
    reader.readAsDataURL(this.globalDataService.user.imageFile)
  }

  logOut()
  {
      this.globalDataService.logOut();
  }

  
}
