import { Injectable } from '@angular/core';
import { User } from './user';
import { Track } from './track';

@Injectable({
  providedIn: 'root'
})
export class GlobalDataService {

  user : User;
  loggedIn : boolean;
  favTracks : Track[];
  
  constructor() { }

  getUser(){
    return this.user;
  }

  //  Log the user in
  logIn(userIn : User) {
    console.log("logged in name: " + userIn.name);
    console.log("logged in id " + userIn.id);
    this.user = userIn;
    this.loggedIn = true;
  }

  //  Log the user out
  logOut() {
    this.user = null;
    this.loggedIn = false;
  }

  
}
