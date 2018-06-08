import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class PayService {

    constructor(
        private http: HttpClient
    ) { }

    payByCard(orderDetailsForm, cardDetailsForm)
    {
        var orderDetailsForm = orderDetailsForm.value;
        var cardDetailsForm = cardDetailsForm.value;

        var request = {
            orderDetails: orderDetailsForm,
            cardDetails: cardDetailsForm
        };

        return this.http.post("http://localhost:8080/pay/card", request);
    }

    payByToken(orderDetailsForm, tokenDetailsForm)
    {
        var orderDetailsForm = orderDetailsForm.value;
        var tokenDetailsForm = tokenDetailsForm.value;

        var request = {
            orderDetails: orderDetailsForm,
            tokenDetails: tokenDetailsForm
        };

        return this.http.post("http://localhost:8080/pay/token", request);
    }

}
