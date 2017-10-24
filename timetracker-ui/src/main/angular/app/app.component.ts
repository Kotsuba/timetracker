import {Component} from "@angular/core";
import {TranslateService} from "@ngx-translate/core";

@Component({
    moduleId: module.id,
    selector: 'app',
    templateUrl: './app.component.html',
})
export class AppComponent {
    constructor(translate: TranslateService) {
        translate.setDefaultLang('en');
        translate.use(translate.getBrowserLang());
    }
}
