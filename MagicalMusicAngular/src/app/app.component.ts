import { Component } from '@angular/core';
import { GlobalDataService } from './global-data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'MagicalMusicAngular';

  constructor(public globalDataService : GlobalDataService) {}

  logOut()
  {
      this.globalDataService.logOut();
  }

}
