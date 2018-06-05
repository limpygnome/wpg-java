import { Component, Input } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'payByCard',
  templateUrl: 'payByCard.component.html',
  styles: []
})
export class PayByCardComponent
{
    @Input() orderDetailsForm: FormGroup;

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
            // Combine into one object for request
            var orderDetailsForm = this.orderDetailsForm.value;
            var cardDetailsForm = this.cardDetailsForm.value;

            var request = {
                orderDetails: orderDetailsForm,
                cardDetails: cardDetailsForm
            };

            // Disable form

            // Make request
            this.http.post("http://localhost:8080/pay/card", request)
                .subscribe(
                    data => console.log(data),
                    error => console.log(error);
                );
        }
    }

}
