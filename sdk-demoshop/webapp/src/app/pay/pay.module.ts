import { NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { CodeModule } from './../code/code.module';

import { OrderDetailsComponent } from './orderDetails.component';
import { PayComponent } from './pay.component';
import { PayByCardComponent } from './payByCard/payByCard.component';
import { PayByTokenComponent } from './payByToken/payByToken.component';
import { PayByHppComponent } from './payByHpp/payByHpp.component';
import { PayByPayPalComponent } from './payByPayPal/payByPayPal.component';

import { PayService } from './pay.service'

@NgModule({
    imports:
    [
        BrowserModule,
        HttpClientModule,
        ReactiveFormsModule,
        CodeModule
    ],
    declarations:
    [
        OrderDetailsComponent,
        PayComponent,
        PayByCardComponent,
        PayByTokenComponent,
        PayByHppComponent,
        PayByPayPalComponent
    ],
    exports:
    [
        OrderDetailsComponent,
        PayComponent,
        PayByCardComponent,
        PayByTokenComponent,
        PayByHppComponent,
        PayByPayPalComponent
    ],
    providers: [PayService]
})
export class PayModule { }
