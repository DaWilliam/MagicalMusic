import { Injectable } from '@angular/core';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class GlobalDataService {

  user : User;
  loggedIn : boolean;

  constructor() { }

  getUser(){
    return this.user;
  }

  //  Log the user in
  logIn(userIn : User) {
    this.user = userIn;
    this.loggedIn = true;
  }

  //  Log the user out
  logOut() {
    this.user = null;
    this.loggedIn = false;
  }
}
