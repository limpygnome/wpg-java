import { Component, Input } from '@angular/core';
import { NotificationsService } from './notifications.service'

@Component({
  selector: 'notifications',
  templateUrl: 'notifications.component.html',
  styles: []
})
export class NotificationsComponent
{
    notifications = null;

    constructor(
        private notificationsService: NotificationsService
    ) { }

    ngOnInit()
    {
        window.setInterval(() => {
            this.notificationsService.fetch()
                .subscribe(data => {
                    if (data == null || (<any>data).length == 0)
                    {
                        this.notifications = [];
                    }
                    else if (this.notifications != data)
                    {
                        this.notifications = data;
                        console.log("notifications updated");
                    }
                });
        }, 1000);
    }

    delete(id)
    {
        this.notificationsService.delete(id)
            .subscribe(success => this.notifications = null);
    }

    deleteAll()
    {
        this.notificationsService.deleteAll()
            .subscribe(success => this.notifications = null);
    }

    trackNotification(item)
    {
        return item;
    }

}
