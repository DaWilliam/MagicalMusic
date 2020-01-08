import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../user-service.service';
import { User } from '../user';
import { GlobalDataService } from '../global-data.service';

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
  imageFile : File = null;
  url : string = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png";

  constructor(private uService : UserServiceService, private globalDataService : GlobalDataService) { }

  ngOnInit() {
    
  }

  onFileChange(event){
    console.log("OnFileChanged"); 
    this.imageFile = event.target.files[0];
    this.uploadImage();
    console.log(event);
  }

  uploadImage()
  {
    console.log("Uploading image: " + (this.imageFile == null))
    if(this.imageFile == null)
      return;

    console.log("Uploading image: " + this.imageFile.name);

    var reader = new FileReader();
    reader.onload = (event : any) => {
      this.url = event.target.result;
      console.log("url: " + this.url);
    };
    
    const blobImage = this.imageFile as Blob;
    reader.readAsDataURL(this.imageFile)

  
  }

  register()
  {
    var user = new User();
    user.name = this.username;
    user.password = this.password;
    user.email = this.email;
    user.imageFile = this.imageFile;

    this.uService.addUser(user).subscribe(
      data => {
        console.log("Registering " + data.name);
        this.globalDataService.logIn(data); 
        this.username = data.name;
        this.password = data.password;
        this.email = data.password;    
        this.imageFile = data.imageFile;    
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
