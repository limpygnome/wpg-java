import { NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { PrismComponent } from './prism.component';
import { PayByCardComponent } from './payByCard.component';
import { PayByTokenComponent } from './payByToken.component';

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
        PayByTokenComponent
    ],
    exports:
    [
        PrismComponent,
        PayByCardComponent,
        PayByTokenComponent
    ],
    providers: []
})
export class CodeModule { }
