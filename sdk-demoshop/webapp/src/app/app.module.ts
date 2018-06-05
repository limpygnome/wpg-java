import { NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { PayModule } from './pay/pay.module';

import { AppComponent } from './app.component';

@NgModule({
    declarations:
    [
        AppComponent
    ],
    imports:
    [
        BrowserModule,
        HttpClientModule,
        PayModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule { }
