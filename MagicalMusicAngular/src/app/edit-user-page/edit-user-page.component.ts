import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { User } from '../user';
import { GlobalDataService } from '../global-data.service';
import { UserServiceService } from '../user-service.service';

@Component({
  selector: 'app-edit-user-page',
  templateUrl: './edit-user-page.component.html',
  styleUrls: ['./edit-user-page.component.css']
})
export class EditUserPageComponent implements OnInit {

  username : string;
  password : string;
  email : string;
  usernameEditing : boolean;
  passwordEditing : boolean;
  
  constructor(private sUser : UserServiceService, private globalData : GlobalDataService) { 

  }

  ngOnInit() {
    this.username = this.globalData.user.name;
    this.password = this.globalData.user.password;
  }

  ngOnChanges()
  {
    this.username = this.globalData.user.name;
    this.password = this.globalData.user.password;
  }

  save()
  {
    var uUser = new User();
    uUser.name = this.username;
    uUser.password = this.password;
    uUser.email = this.email;
    this.sUser.update(uUser);
  }

}
