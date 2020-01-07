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
  favTracks : Track[];

  constructor(private trackService : TrackserviceService, public globalDataService : GlobalDataService) {}

  logOut()
  {
      this.globalDataService.logOut();
  }

  
}
