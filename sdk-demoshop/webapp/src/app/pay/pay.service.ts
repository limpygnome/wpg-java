import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment'

@Injectable()
export class PayService {

    constructor(
        private http: HttpClient
    ) { }

    payByCard(orderDetailsForm, cardDetailsForm)
    {
        var request = {
            orderDetails: orderDetailsForm.value,
            cardDetails: cardDetailsForm.value
        };

        return this.http.post(environment.baseUrl + "/pay/card", request);
    }

    payByToken(orderDetailsForm, tokenDetailsForm)
    {
        var request = {
            orderDetails: orderDetailsForm.value,
            tokenDetails: tokenDetailsForm.value
        };

        return this.http.post(environment.baseUrl + "/pay/token", request);
    }

    payByHpp(orderDetailsForm, hppDetailsForm)
    {
        var request = {
            orderDetails: orderDetailsForm.value,
            hppDetails: hppDetailsForm.value
        };

        return this.http.post(environment.baseUrl + "/pay/hpp", request);
    }

    payByPayPal(orderDetailsForm, payPalDetailsForm)
    {
        var request = {
            orderDetails: orderDetailsForm.value,
            payPalDetails: payPalDetailsForm.value
        };

        return this.http.post(environment.baseUrl + "/pay/paypal", request);
    }

}
