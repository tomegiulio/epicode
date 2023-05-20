import { Directive, Input, HostBinding } from '@angular/core';

@Directive({
  selector: '[appColora]'
})
export class ColoraDirective {

@Input('appColora') newColor : string = 'yellow'
@HostBinding('style.background-color') color: string ='yellow';
  constructor() { }

}
