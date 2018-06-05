import { NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { CodeModule } from './../code/code.module';

import { OrderDetailsComponent } from './orderDetails.component';
import { PayComponent } from './pay.component';
import { PayByCardComponent } from './payByCard.component';

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
        PayByCardComponent
    ],
    exports:
    [
        OrderDetailsComponent,
        PayComponent,
        PayByCardComponent
    ],
    providers: []
})
export class PayModule { }
