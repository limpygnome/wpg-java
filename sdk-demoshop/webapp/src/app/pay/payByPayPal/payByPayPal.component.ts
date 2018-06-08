import { Component, Input, EventEmitter, Output } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { PayService } from './../pay.service'

@Component({
  selector: 'payByPayPal',
  templateUrl: 'payByPayPal.component.html',
  styles: []
})
export class PayByPayPalComponent
{
    @Input() orderDetailsForm: FormGroup;
    @Output() loading = new EventEmitter();
    @Output() reply = new EventEmitter();

    public payPalDetailsForm = this.fb.group({
        successUrl: ["https://google.co.uk/search?q=success"],
        failureUrl: ["https://google.co.uk/search?q=failure"],
        cancelUrl: ["https://google.co.uk/search?q=cancel"],
        languageCode: [""],
        email: ["test@worldpay.com"]
    });

    constructor(
        public fb: FormBuilder,
        private payService: PayService
    ) {}

    pay()
    {
        if (this.orderDetailsForm.valid && this.payPalDetailsForm.valid)
        {
            this.loading.emit();
            this.payService.payByPayPal(this.orderDetailsForm, this.payPalDetailsForm)
                .subscribe(
                    data => this.reply.emit(data),
                    error => this.reply.emit({ "error": error.message })
                );
        }
    }

}
