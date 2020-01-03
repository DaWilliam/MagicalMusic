import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { UserServiceService } from '../user-service.service';
import { User } from '../user';
import { GlobalDataService } from '../global-data.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  loginForm : FormGroup;
  username : FormControl;
  password : FormControl;

  constructor(private sUser : UserServiceService, private sGlobal : GlobalDataService) { }

  ngOnInit() {
    this.username = new FormControl('');
    this.password = new FormControl('');
    this.loginForm = new FormGroup({
      username : this.username,
      password : this.password
    });
  }

  login()
  {
    var user = new User();
    user.name = this.loginForm.value.username;
    user.password = this.loginForm.value.password;

    //console.log("Name: " + user.name + " Pass: " + user.password);
    this.sUser.login(user).subscribe(
      data => {
        this.sGlobal.logIn(user);
        console.log("YA BOI " + user.name + " IS NOW PART OF THE GANG");
      }, error => {
        console.log("COULD NOT ADD YOUR BOI");
      }
    );
  }
}
