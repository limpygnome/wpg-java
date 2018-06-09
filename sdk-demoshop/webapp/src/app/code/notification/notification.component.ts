import { Component, Input } from '@angular/core';

@Component({
  selector: 'codeNotification',
  templateUrl: 'notification.component.html',
  styles: []
})
export class NotificationComponent
{
    @Input() code: string;
}
