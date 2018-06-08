import { Component, Input, EventEmitter, Output } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { PayService } from './../pay.service'

@Component({
  selector: 'payByHpp',
  templateUrl: 'payByHpp.component.html',
  styles: []
})
export class PayByHppComponent
{
    @Input() orderDetailsForm: FormGroup;
    @Output() loading = new EventEmitter();
    @Output() reply = new EventEmitter();

    public hppDetailsForm = this.fb.group({
        successUrl: [""],
        pendingUrl: [""],
        failureUrl: [""],
        errorUrl: [""],
        cancelUrl: [""],
        paymentMethod: [""]
    });

    constructor(
        public fb: FormBuilder,
        private payService: PayService
    ) {}

    pay()
    {
        if (this.orderDetailsForm.valid && this.hppDetailsForm.valid)
        {
            this.loading.emit();
            this.payService.payByHpp(this.orderDetailsForm, this.hppDetailsForm)
                .subscribe(
                    data => this.reply.emit(data),
                    error => this.reply.emit({ "error": error.message })
                );
        }
    }

}
