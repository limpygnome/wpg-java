import { Component, Input } from '@angular/core';

@Component({
  selector: 'codePayByCard',
  templateUrl: 'payByCard.component.html',
  styles: []
})
export class PayByCardComponent
{
    @Input() code: string;
}
