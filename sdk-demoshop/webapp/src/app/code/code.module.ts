import { NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { PrismComponent } from './prism.component';
import { CodeViewerComponent } from './codeViewer.component';
import { PayByCardComponent } from './payByCard/payByCard.component';
import { PayByTokenComponent } from './payByToken/payByToken.component';
import { PayByHppComponent } from './payByHpp/payByHpp.component';
import { PayByPayPalComponent } from './payByPayPal/payByPayPal.component';
import { NotificationComponent } from './notification/notification.component';

@NgModule({
    imports:
    [
        BrowserModule,
        HttpClientModule
    ],
    declarations:
    [
        PrismComponent,
        CodeViewerComponent,
        PayByCardComponent,
        PayByTokenComponent,
        PayByHppComponent,
        PayByPayPalComponent,
        NotificationComponent
    ],
    exports:
    [
        PrismComponent,
        CodeViewerComponent,
        PayByCardComponent,
        PayByTokenComponent,
        PayByHppComponent,
        PayByPayPalComponent,
        NotificationComponent
    ],
    providers: []
})
export class CodeModule { }
