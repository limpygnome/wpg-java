import { NgModule } from '@angular/core';

import { RouterModule, Routes } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { PayModule } from './pay/pay.module';

import { CodeModule } from './code/code.module';

import { AppComponent } from './app.component';
import { PayComponent } from './pay/pay.component';
import { NotificationsComponent } from './notifications/notifications.component';

import { NotificationsService } from './notifications/notifications.service'

const appRoutes: Routes = [
    { path: 'pay',                  component: PayComponent },
    { path: 'notifications',        component: NotificationsComponent },
    { path: '**',                   redirectTo: 'pay' }
];


@NgModule({
    declarations:
    [
        AppComponent,
        NotificationsComponent
    ],
    imports:
    [
        RouterModule.forRoot(appRoutes),
        BrowserModule,
        HttpClientModule,
        PayModule,
        CodeModule
    ],
    providers: [NotificationsService],
    bootstrap: [AppComponent]
})
export class AppModule { }
