import { Component, Input } from '@angular/core';

@Component({
  selector: 'codePayByPayPal',
  templateUrl: 'payByPayPal.component.html',
  styles: []
})
export class PayByPayPalComponent
{
    @Input() code: string;
}
