import { Component, Input } from '@angular/core';

@Component({
  selector: 'codePayByToken',
  templateUrl: 'payByToken.component.html',
  styles: []
})
export class PayByTokenComponent
{
    @Input() code: string;
}
