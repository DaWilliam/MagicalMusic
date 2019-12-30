import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchComponent } from './search/search.component';
import { RecommendedComponent } from './recommended/recommended.component';
import { FavoritesComponent } from './favorites/favorites.component';
import { SongCardComponent } from './song-card/song-card.component';
const routes: Routes = [
  {path:'SearchComponent', component: SearchComponent},
  {path:'RecommendedComponent', component: RecommendedComponent},
  {path:'FavoritesComponent', component: FavoritesComponent},
  {path:'SongCardComponent', component: SongCardComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
