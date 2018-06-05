import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

import * as $ from 'jquery';

@Component({
  selector: 'pay',
  templateUrl: 'pay.component.html',
  styles: []
})
export class PayComponent
{
    payType: string = "card";
    showCode: boolean = false;
    code: string = "java-sdk";

    public orderDetailsForm = this.fb.group({
        orderCode: ["order123", Validators.required],
        shopperId: ["shopper123", Validators.required],
        amount: ["1234", Validators.required],
        currency: ["GBP", Validators.required],
        address: ["123 Test Street", Validators.required],
        city: ["Cambridge", Validators.required],
        postalCode: ["orderDetailsForm", Validators.required],
        countryCode: ["GB", Validators.required]
    });

    constructor(
        public fb: FormBuilder
    ) {}

    toggleCode(codeElement)
    {
        console.log(codeElement);
        // Toggle state and scroll to code
        this.showCode = !this.showCode;
        $('html, body').animate({
            scrollTop: $(codeElement).offset().top
        }, 1000);
    }

    viewCode(code)
    {
        this.code = code;
    }

}
