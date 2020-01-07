import {
  Injectable,
  ComponentFactoryResolver,
  ApplicationRef,
  Injector,
  EmbeddedViewRef,
  ComponentRef,
} from '@angular/core';
import { SongCardComponent } from './song-card.component';
import { SongCardModule } from './song-card.module';

@Injectable({
  providedIn: SongCardModule,
})
export class SongCardService {
  dialogComponentRef: ComponentRef<SongCardComponent>
  constructor(
    private componentFactoryResolver: ComponentFactoryResolver,
    private appRef: ApplicationRef,
    private injector: Injector
  ) {}

  appendDialogComponentToBody(){
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(SongCardComponent);
    const componentRef = componentFactory.create(this.injector);
    this.appRef.attachView(componentRef.hostView);

    const domElem = (componentRef.hostView as EmbeddedViewRef<any>).rootNodes[0] as HTMLElement;
    document.body.appendChild(domElem);

    this.dialogComponentRef = componentRef;
  }
}
