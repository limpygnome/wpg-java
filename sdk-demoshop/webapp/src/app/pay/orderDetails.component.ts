import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'orderDetails',
  templateUrl: 'orderDetails.component.html',
  styles: []
})
export class OrderDetailsComponent
{
    @Input() orderDetailsForm: FormGroup;

}
