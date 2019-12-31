import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from './user';
import {Track} from './track';

@Injectable({
  providedIn: 'root'
})
export class TrackserviceService {

  //  You have to make it private (or public?) to be seen by the class
  constructor(private httpClient : HttpClient) { }

  addTrack(track: Track)  {
    return this.httpClient.post<Track>('http://localhost:8088/artist/add', track);
  }

}
