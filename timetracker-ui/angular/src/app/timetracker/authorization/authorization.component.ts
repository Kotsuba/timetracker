import {Component, Injectable, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from './authorization.service';
import {UtilService} from '../../shared/service/util.service';
import {CookieService} from 'angular2-cookie/core';
import {AlertService} from '../../shared/service/alert.service';

@Component({
    moduleId: module.id,
    selector: 'login',
    templateUrl: './authorization.component.html'
})
@Injectable()
export class AuthorizationComponent implements OnInit {
    authForm: FormGroup;
    login: string;
    password: string;

    constructor(private fb: FormBuilder,
                private authService: AuthService,
                private utilService: UtilService,
                private cookieService: CookieService,
                private alert: AlertService) {
    }

    ngOnInit() {
        this.buildForm();
        if (this.cookieService.get('user_logged') != null) {
            this.login = this.cookieService.get('user_logged');
        }
        this.authService.logout();
    }

    private  buildForm(): void {
        this.authForm = this.fb.group({
            'login': [this.login, Validators.required],
            'password': [this.password, Validators.required]
        });
    }

    authorization() {
        this.authService.login(this.login, this.password).subscribe(
            data => {
                delete this.password;
                this.utilService.setHeaders(data.token);
                this.cookieService.put('user_logged', this.login);
                this.cookieService.put('user_token', data.token);
                try {
                    this.authService.getUser(this.login);
                } catch (error) {
                    this.alert.errorHandler(error);
                }
            }, error => {
                if (error.status == 401) {
                    this.alert.simpleErrorHandler('Invalid login or password');
                }
            });
    }
}
