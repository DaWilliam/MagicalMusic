import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchComponent } from './search/search.component';
import { RecommendedComponent } from './recommended/recommended.component';
import { FavoritesComponent } from './favorites/favorites.component';
import { SongCardComponent } from './song-card/song-card.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegistrationPageComponent } from './registration-page/registration-page.component';
import { EditUserPageComponent } from './edit-user-page/edit-user-page.component';
const routes: Routes = [
  {path:'SearchComponent', component: SearchComponent},
  {path:'RecommendedComponent', component: RecommendedComponent},
  {path:'FavoritesComponent', component: FavoritesComponent},
  {path:'SongCardComponent', component: SongCardComponent},
  {path:'LoginPageComponent', component: LoginPageComponent},
  {path:'RegistrationPageComponent', component: RegistrationPageComponent},
  {path:'EditUserPageComponent', component: EditUserPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
