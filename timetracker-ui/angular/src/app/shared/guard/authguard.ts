import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Injectable} from "@angular/core";
import {AuthService} from "../../timetracker/authorization/authorization.service";
import {CookieService} from "angular2-cookie/core";
import {UtilService} from "../service/util.service";
import {JwtHelper} from "angular2-jwt";

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private authService: AuthService,
                private router: Router,
                private cookieService: CookieService,
                private utilService: UtilService) {

    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        let jwtHelper = new JwtHelper();
        this.authService.redirectUrl = state.url;
        if (!this.authService.isUserLoggedIn()) {
            if (this.cookieService.get('user_logged') && this.cookieService.get('user_token')) {
                let token = this.cookieService.get('user_token');
                let login = this.cookieService.get('user_logged');
                let token_info = jwtHelper.decodeToken(token);
                if (token_info.hasOwnProperty('sub') && token_info.sub == login) {
                    if (!jwtHelper.isTokenExpired(token)) {
                        this.utilService.setHeaders(token);
                        this.authService.getUser(login);
                        return true;
                    } else {
                        this.router.navigate(['/login']);
                        return false;
                    }
                } else {
                    this.router.navigate(['/login']);
                    return false;
                }
            }
            this.router.navigate(['/login']);
            return false;
        }
        return true;
    }
}