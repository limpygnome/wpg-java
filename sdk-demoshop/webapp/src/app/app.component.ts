import { Component } from '@angular/core';
import { NotificationsService } from './notifications/notifications.service'

import * as toastr from 'toastr';

toastr.options.closeButton = true;

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styles: []
})
export class AppComponent
{
    lastCount = 0;

    constructor(
        private notificationsService: NotificationsService
    ) { }

    ngOnInit()
    {
        window.setInterval(() => {
            this.notificationsService.fetch()
                .subscribe(data => {
                    if (data != null)
                    {
                        if (this.lastCount < (<any>data).length)
                        {
                            toastr.info("New channel notification received");
                            console.info("new channel notification");
                        }
                        this.lastCount = (<any>data).length;
                        console.debug("notification count: " + this.lastCount);
                    }
                    else
                    {
                        this.lastCount = 0;
                    }
                });
        }, 1000);
    }

}
