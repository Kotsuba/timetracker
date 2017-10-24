import {NotificationsService} from "angular2-notifications";
import {Injectable} from "@angular/core";
import {Router} from "@angular/router";
import {root} from "rxjs/util/root";
import {Observable} from "rxjs/Observable";

@Injectable()
export class AlertService {
    private UNAUTHORIZED: number = 401;

    constructor(private notification: NotificationsService,
                private router: Router) {
    }

    public errorHandler(error: any, message?: string) {
        if (error.status == this.UNAUTHORIZED.toString()) {
            this.router.navigate(['/login']);
        } else {
            this.notification.error("Error", message ? message : error.status, {
                timeOut: 4000,
                showProgressBar: false,
                lastOnBottom: true,
            });
        }
    }

    public simpleErrorHandler(message: string) {
        this.notification.error("Error", message, {
            timeOut: 4000,
            showProgressBar: false,
            lastOnBottom: true,
        });
    }

    public successHandler(body: string) {
        this.notification.success("Success", body, {
            timeOut: 4000,
            showProgressBar: false,
            pauseOnHover: false,
            clickToClose: false,
        });
    }
}
