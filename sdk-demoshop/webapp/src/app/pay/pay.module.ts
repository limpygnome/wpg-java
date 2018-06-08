import { NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { CodeModule } from './../code/code.module';

import { OrderDetailsComponent } from './orderDetails.component';
import { PayComponent } from './pay.component';
import { PayByCardComponent } from './payByCard.component';
import { PayByTokenComponent } from './payByToken.component';

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
        PayByTokenComponent
    ],
    exports:
    [
        OrderDetailsComponent,
        PayComponent,
        PayByCardComponent,
        PayByTokenComponent
    ],
    providers: [PayService]
})
export class PayModule { }
