import { Component, Input, EventEmitter, Output } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { PayService } from './pay.service'

declare var PaymentRequest: any;

@Component({
  selector: 'payByToken',
  templateUrl: 'payByToken.component.html',
  styles: []
})
export class PayByTokenComponent
{
    @Input() orderDetailsForm: FormGroup;
    @Output() loading = new EventEmitter();
    @Output() reply = new EventEmitter();

    public tokenDetailsForm = this.fb.group({
        tokenId: ["", Validators.required],
        captureCvc: [false, Validators.required]
    });

    constructor(
        public fb: FormBuilder,
        private payService: PayService
    ) { }

    pay()
    {
        if (this.orderDetailsForm.valid && this.tokenDetailsForm.valid)
        {
            this.loading.emit();
            this.payService.payByToken(this.orderDetailsForm, this.tokenDetailsForm)
                .subscribe(
                    data => this.reply.emit(data),
                    error => this.reply.emit({ "error": error.message })
                );
        }
    }

}
