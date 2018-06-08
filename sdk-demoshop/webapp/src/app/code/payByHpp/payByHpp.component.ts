import { Component, Input } from '@angular/core';

@Component({
  selector: 'codePayByHpp',
  templateUrl: 'payByHpp.component.html',
  styles: []
})
export class PayByHppComponent
{
    @Input() code: string;
}
