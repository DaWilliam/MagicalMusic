import { Directive, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[songInsertion]'
})
export class InsertionDirective {

  constructor(public viewContainerRef: ViewContainerRef) { }

}
