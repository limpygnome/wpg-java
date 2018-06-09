import { Component, Input } from '@angular/core';

import * as $ from 'jquery';

@Component({
  selector: 'codeViewer',
  templateUrl: 'codeViewer.component.html',
  styles: []
})
export class CodeViewerComponent
{
    @Input() type: string;
    showCode: boolean = false;
    code: string = "java-sdk";

    toggleCode(codeElement)
    {
        console.log(codeElement);
        // Toggle state and scroll to code
        this.showCode = !this.showCode;
        $('html, body').animate({
            scrollTop: $(codeElement).offset().top
        }, 1000);
    }

    viewCode(code)
    {
        this.code = code;
    }

}
