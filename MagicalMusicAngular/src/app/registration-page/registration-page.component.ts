import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../user-service.service';
import { User } from '../user';

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent implements OnInit {

  username : string;
  password : string;
  email : string;

  constructor(private uService : UserServiceService) { }

  ngOnInit() {
    
  }

  register()
  {
    var user = new User();
    user.name = this.username;
    user.password = this.password;
    user.email = this.email;
    
    this.uService.register(user, user.email).subscribe(
      data => {
        console.log("Registering");
      }, error => {
        console.log("Error registering");
      }
    )
  }
}
