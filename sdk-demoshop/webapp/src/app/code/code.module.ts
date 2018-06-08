import { NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { PrismComponent } from './prism.component';
import { PayByCardComponent } from './payByCard/payByCard.component';
import { PayByTokenComponent } from './payByToken/payByToken.component';
import { PayByHppComponent } from './payByHpp/payByHpp.component';
import { PayByPayPalComponent } from './payByPayPal/payByPayPal.component';

@NgModule({
    imports:
    [
        BrowserModule,
        HttpClientModule
    ],
    declarations:
    [
        PrismComponent,
        PayByCardComponent,
        PayByTokenComponent,
        PayByHppComponent,
        PayByPayPalComponent
    ],
    exports:
    [
        PrismComponent,
        PayByCardComponent,
        PayByTokenComponent,
        PayByHppComponent,
        PayByPayPalComponent
    ],
    providers: []
})
export class CodeModule { }
