import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { PrismComponent } from './code/prism.component';
import { PayByCardComponent } from './code/payByCard.component';


@NgModule({
    declarations:
    [
        AppComponent,
        PrismComponent,
        PayByCardComponent
    ],
    imports:
    [
        BrowserModule,
        HttpClientModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule { }
