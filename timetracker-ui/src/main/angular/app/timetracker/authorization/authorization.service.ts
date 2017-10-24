import {Injectable} from "@angular/core";
import {Headers, Http, Response, URLSearchParams} from "@angular/http";

import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import "rxjs/add/observable/throw";

import {ApiConstants} from "../../shared/constants/api";
import {User} from "../../shared/bean/user";
import {UtilService} from "../../shared/service/util.service";
import {UserRole} from "../../shared/bean/userRole";
import {AppComponent} from "../../app.component";
import {LoggedUser} from "../../shared/service/loggedUser.service";
import {UserService} from "../user/user.service";
import {Router} from "@angular/router";
import {AlertService} from "../../shared/service/alert.service";
import {CookieService} from "angular2-cookie/core";
import {Observable} from "rxjs/Observable";


@Injectable()
export class AuthService {
    headers = new Headers({"Content-Type": "application/x-www-form-urlencoded"});
    redirectUrl: string;

    constructor(private http: Http,
                private loggedUser: LoggedUser,
                private userService: UserService,
                private router: Router,
                private cookieService: CookieService) {
    }

    login(login: string, password: string) {
        let params = new URLSearchParams();
        params.set('login', login);
        params.set('password', password);
        return this.http.post(ApiConstants.AUTH, params.toString(),
            {headers: this.headers})
            .map(UtilService.extractData);
    }

    getUser(login: string) {
        this.userService.getUserByLogin(login).subscribe(
            data => {
                this.loggedUser.user = data;
                this.setUserRole(data);
                this.loggedUser.isUserLogged = true;
                this.redirectOnPage();
            }, error => {
                return Observable.throw(error);
            });
    }

    public isUserLoggedIn(): boolean {
        return this.loggedUser.isUserLogged;
    }


    logout(): void {
        this.loggedUser.isUserLogged = false;
        this.cookieService.remove("user_token");
    }

    private redirectOnPage() {
        if (this.redirectUrl != null) {
            if ((this.redirectUrl != '/login') && (this.redirectUrl != '/logout')) {
                this.router.navigate([this.redirectUrl]);
            }
        } else {
            this.router.navigate(['/performance']);
        }
    }

    private setUserRole(user: User) {
        this.loggedUser.user = user;
        if (user.viewManager != null) {
            this.loggedUser.userRole = UserRole.VIEWMANAGER;
        }
        else if (user.coManager != null) {
            this.loggedUser.userRole = UserRole.COMANAGER;
        }
        else if (user.manager == null) {
            this.loggedUser.userRole = UserRole.MANAGER;
        } else {
            this.loggedUser.userRole = UserRole.USER;
        }
    }
}