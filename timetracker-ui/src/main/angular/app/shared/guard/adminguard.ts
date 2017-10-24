import {CanActivate} from "@angular/router";
import {Injectable} from "@angular/core";
import {LoggedUser} from "../service/loggedUser.service";
import {UserRole} from "../bean/userRole";
@Injectable()
export class AdminGuard implements CanActivate {

    constructor(private loggedUser: LoggedUser) {

    }
    userRole = UserRole;
    canActivate() {
        if (this.loggedUser.userRole== UserRole.MANAGER || this.loggedUser.userRole == UserRole.COMANAGER) {
            return true;
        }
        return false;
    }
}
