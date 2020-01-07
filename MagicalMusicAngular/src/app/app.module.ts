import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SongCardComponent } from './song-card/song-card.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatSelectModule, MatButtonModule, MatInputModule, MatCheckboxModule} from '@angular/material';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatCardModule} from '@angular/material/card';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatIconModule} from '@angular/material/icon';
import {MatRippleModule} from '@angular/material/core';
import { HttpClientModule } from '@angular/common/http';
import { UserServiceService } from './user-service.service';
import { SearchComponent } from './search/search.component';
import { RecommendedComponent } from './recommended/recommended.component';
import { FavoritesComponent } from './favorites/favorites.component';
import { RegistrationPageComponent } from './registration-page/registration-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import {TrackserviceService} from './trackservice.service';
import { GlobalDataService } from './global-data.service';
import { EditUserPageComponent } from './edit-user-page/edit-user-page.component';
import { SongsListComponent } from './songs-list/songs-list.component';


@NgModule({
  declarations: [
    AppComponent,
    SongCardComponent,
    SearchComponent,
    RecommendedComponent,
    FavoritesComponent,
    SongCardComponent,
    RegistrationPageComponent,
    LoginPageComponent,
    EditUserPageComponent,
    SongsListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatButtonModule,
    MatSelectModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatCardModule,
    MatGridListModule,
    MatIconModule,
    MatRippleModule,
    MatCheckboxModule
  ],
  providers: [UserServiceService, TrackserviceService, GlobalDataService],
  bootstrap: [AppComponent]
  //entryComponents: [ SongCardComponent ]
})
export class AppModule { }
