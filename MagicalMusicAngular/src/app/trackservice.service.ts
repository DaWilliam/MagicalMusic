import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {User} from './user';
import {Track} from './track';
import { GlobalDataService } from './global-data.service';

@Injectable({
  providedIn: 'root'
})
export class TrackserviceService {

  //  You have to make it private (or public?) to be seen by the class
  constructor(private httpClient : HttpClient, private globalDataService : GlobalDataService) { }

  addTrack(track: Track)  {
    //const params = new HttpParams().set('track', track).set('id', id);
    return this.httpClient.post<Track>('http://localhost:8088/artist/add/' + this.globalDataService.user.id, track);
  }

  removeTrack(track: Track)  {
    const params = new HttpParams().set('id', track.id.toString()).set('artist', track.artistName).set('song', track.songName).set('imageURL', track.image);
    return this.httpClient.request<Track[]>('delete', 'http://localhost:8088/artist/delete/' + this.globalDataService.user.id, {params});
  }

  findAllTrack(song : string, artist : string){
    return this.httpClient.get('http://localhost:8088/artist/' + artist + '/' + song);
  }

  findSong(song : string){
    return this.httpClient.get('http://localhost:8088/artist/song/' + song);
  }
  
  findRecommended(){
    return this.httpClient.get('http://localhost:8088/artist/toptracks');
  }

  findFavorites(user : User) {
    return this.httpClient.get('http://localhost:8088/artist/favorites/' + user.id);
  }
}
