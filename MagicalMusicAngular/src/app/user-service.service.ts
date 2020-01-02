import { Injectable } from '@angular/core';
import { HttpClient, HttpParams} from '@angular/common/http';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  //  You have to make it private (or public?) to be seen by the class
  constructor(private httpClient : HttpClient){ }

  getresult(){
    return this.httpClient.get<Array<User>>('http://localhost:8088/users/getAll');
  }
  
  addUser(user:User)  {
    return this.httpClient.post<User>('http://localhost:8088/users/add', user);
  }

  login(user:User)  {
    return this.httpClient.post<User>('http://localhost:8088/users/login', user);
  }

  register(user:User, email:string)  {
    return this.httpClient.post<User>('http://localhost:8088/users/register', user);
  }

  //  Should find the user by the ID and update it accordingly
  update(user:User)  {
    return this.httpClient.post<User>('http://localhost:8088/users/update' + user.id, { user });
  }

}
