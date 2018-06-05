import { Component, AfterViewChecked } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styles: []
})
export class AppComponent
{

    ngAfterViewChecked()
    {
        //Prism.highlightAll();
    }

}
