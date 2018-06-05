import { Component, AfterViewInit, AfterViewChecked, ElementRef, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import 'prismjs';
import 'prismjs/components/prism-java';
import 'prismjs/plugins/unescaped-markup/prism-unescaped-markup';
import 'prismjs/plugins/toolbar/prism-toolbar';
import 'prismjs/plugins/line-numbers/prism-line-numbers';
import 'prismjs/plugins/copy-to-clipboard/prism-copy-to-clipboard';

declare var Prism: any;

@Component({
  selector: 'prism',
  template: '<pre class="line-numbers"><code #codeRef class="language-{{language}}" [innerHTML]="content"></code></pre>',
  styles: ['pre.line-numbers { padding-left: 3.8em !important; }']
})
export class PrismComponent implements AfterViewInit, AfterViewChecked
{
    @Input("path") path: string;

    @Input("language") language: string = "java";

    content: string;

    constructor(private elementRef: ElementRef, private http: HttpClient) { }

    ngAfterViewInit()
    {
        this.http.get(this.path, {responseType: 'text'}).subscribe(data => {
            this.content = data.replace(/</g, "&lt;")
                               .replace(/>/g, "&gt;");
        });
    }

    ngAfterViewChecked()
    {
        Prism.highlightAll();
    }

}
