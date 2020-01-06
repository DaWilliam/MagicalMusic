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

  errorMessage : string;

  constructor(private uService : UserServiceService) { }

  ngOnInit() {
    
  }

  register()
  {
    var user = new User();
    user.name = this.username;
    user.password = this.password;
    user.email = this.email;
    
    this.uService.addUser(user).subscribe(
      data => {
        console.log("Registering");
      }, error => {
        if(error.status == '409')
        {
          console.log("Email in use");
          this.errorMessage = "Email already used";
        }
        console.log("Error registering");
      }
    )
  }
}
