import { Component, OnInit, Type, ViewContainerRef,ComponentFactoryResolver, ComponentRef, ViewChild } from '@angular/core';
import { SongCardComponent } from '../song-card/song-card.component';
import { InsertionDirective } from './insertion.directive';
import { TrackserviceService} from '../trackservice.service';
import { Track } from '../track';
import { Observable } from 'rxjs';
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  @ViewChild(InsertionDirective, {static: true}) songInsertion: InsertionDirective;

  tracks : Observable<Object>;
  //private componentRef: ComponentRef<any>;
  constructor(private Trackservice:TrackserviceService) {

  }

  ngOnInit() {
    //this.loadComponent();
  }

  onSearchClicked(evt: MouseEvent){
    this.tracks = this.Trackservice.findAllTrack();
  }

  // loadComponent(){
  //   const componentFactory = this.componentFactoryResolver.resolveComponentFactory(SongCardComponent);
  //   const viewContainerRef = this.songInsertion.viewContainerRef;
  //   //viewContainerRef.clear();

  //   const componentRef = viewContainerRef.createComponent(componentFactory);
  //   //(<SongCardComponent>componentRef.instance).data = adItem.data;
  // }

}
