import { NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { PrismComponent } from './prism.component';
import { PayByCardComponent } from './payByCard.component';

@NgModule({
    imports:
    [
        BrowserModule,
        HttpClientModule
    ],
    declarations:
    [
        PrismComponent,
        PayByCardComponent
    ],
    exports:
    [
        PrismComponent,
        PayByCardComponent
    ],
    providers: []
})
export class CodeModule { }
