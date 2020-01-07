import { Component, OnInit, Type, ViewContainerRef,ComponentFactoryResolver, ComponentRef, ViewChild } from '@angular/core';
import { SongCardComponent } from '../song-card/song-card.component';
import { InsertionDirective } from './insertion.directive';
import { TrackserviceService} from '../trackservice.service';
import { Track } from '../track';
import { Observable } from 'rxjs';
import { FormGroup, FormControl } from '@angular/forms';
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  @ViewChild(InsertionDirective, {static: true}) songInsertion: InsertionDirective;

  tracks : Track[];
  songSearch : string;
  artistSearch : string;
  isSearching : boolean;

  //private componentRef: ComponentRef<any>;
  constructor(private Trackservice:TrackserviceService) {
      
  }

  ngOnInit() {
    
    //this.loadComponent();
  }

  onSearchClicked(evt: MouseEvent){         
    this.isSearching = true;

    console.log("song "+isValid(this.songSearch));
    console.log("artist "+isValid(this.artistSearch));
    if(isValid(this.songSearch) && isValid(this.artistSearch)){
      console.log("song and artist true");
      this.Trackservice.findAllTrack(this.songSearch,this.artistSearch).subscribe(
        (data : Track[]) => { 
          console.log("DataLen: " + data.length);
          console.log("Data: " + data.values);
          this.tracks = data
          this.tracks.forEach(function(value) {
            console.log(value);
          })
          
        }, error => {
          console.log(error);
        });
    }else if(isValid(this.songSearch) && !isValid(this.artistSearch)){
      console.log("song true");
      this.Trackservice.findSong(this.songSearch).subscribe(
      (data : Track[]) => { 
        console.log("DataLen: " + data.length);
        console.log("Data: " + data.values);
        this.tracks = data
        this.tracks.forEach(function(value) {
          console.log(value);
        })
        
      }, error => {
        console.log(error);
      });
    }else if(!isValid(this.songSearch) && isValid(this.artistSearch)){
      console.log("artist true");
      this.Trackservice.findArtist(this.artistSearch).subscribe(
        (data : Track[]) => { 
          console.log("DataLen: " + data.length);
          console.log("Data: " + data.values);
          this.tracks = data
          this.tracks.forEach(function(value) {
            console.log(value);
          })
          
        }, error => {
          console.log(error);
        });
    }else{
      this.songSearch=="empty";
      this.artistSearch=="empty";
    }
      
  }
  // loadComponent(){
  //   const componentFactory = this.componentFactoryResolver.resolveComponentFactory(SongCardComponent);
  //   const viewContainerRef = this.songInsertion.viewContainerRef;
  //   //viewContainerRef.clear();

  //   const componentRef = viewContainerRef.createComponent(componentFactory);
  //   //(<SongCardComponent>componentRef.instance).data = adItem.data;
  // }

}

export function isValid(str:string){
  if(str){
    return true;
  }else{
    return false;
  }
  
}