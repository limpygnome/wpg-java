import { Component, Input, EventEmitter, Output } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

declare var PaymentRequest: any;

@Component({
  selector: 'payByCard',
  templateUrl: 'payByCard.component.html',
  styles: []
})
export class PayByCardComponent
{
    @Input() orderDetailsForm: FormGroup;
    @Output() loading = new EventEmitter();
    @Output() reply = new EventEmitter();

    public cardDetailsForm = this.fb.group({
        name: ["John Doe", Validators.required],
        cardNumber: ["4444333322221129", Validators.required],
        expiryMonth: ["12", Validators.required],
        expiryYear: ["2022", Validators.required],
        cvc: ["123", Validators.required],
        tokenise: ["true", Validators.required]
    });

    constructor(
        public fb: FormBuilder,
        private http: HttpClient
    ) {}

    pay()
    {
        if (this.orderDetailsForm.valid && this.cardDetailsForm.valid)
        {
            // TODO move into service
            // Combine into one object for request
            var orderDetailsForm = this.orderDetailsForm.value;
            var cardDetailsForm = this.cardDetailsForm.value;

            var request = {
                orderDetails: orderDetailsForm,
                cardDetails: cardDetailsForm
            };

            // Disable form
            this.loading.emit();

            // Make request
            this.http.post("http://localhost:8080/pay/card", request)
                .subscribe(
                    data => this.reply.emit(data),
                    error => this.reply.emit({ "error": error.message })
                );
        }
    }

    wallet()
    {
        var supportedPaymentMethods = [{
            supportedMethods: "basic-card",
            data: {
                supportedNetworks: ["visa", "mastercard", "amex", "diners", "jcb"],
                supportedTypes: ["debit, credit"]
            }
        }];

        var orderDetails = {
            total: {
                label: "Total",
                amount: {
                    currency: this.orderDetailsForm.value["currency"],
                    value: this.orderDetailsForm.value["amount"] / 100
                }
            }
        };

        var request = new PaymentRequest(supportedPaymentMethods, orderDetails);
        request.show().then((response) => this.walletResponse(response));
    }

    walletResponse(response)
    {
        response.complete("success").then(() => {
            // Populate fields
            this.cardDetailsForm.patchValue({
                cardNumber: response.details.cardNumber,
                cvc: response.details.cardSecurityCode,
                name: response.details.cardholderName,
                expiryMonth: response.details.expiryMonth,
                expiryYear: response.details.expiryYear
            });
        );
    }

}
