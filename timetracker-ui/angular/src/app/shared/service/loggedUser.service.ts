import {User} from "../bean/user";
import {UserRole} from "../bean/userRole";
import {Injectable} from "@angular/core";
@Injectable()
export class LoggedUser {
    private _user: User;
    private _userRole: UserRole;
    private _isUserLogged:boolean=false;

    get isUserLogged(): boolean {
        return this._isUserLogged;
    }

    set isUserLogged(value: boolean) {
        this._isUserLogged = value;
    }

    get user(): User {
        return this._user;
    }

    set user(value: User) {
        this._user = value;
    }

    get userRole(): UserRole {
        return this._userRole;
    }

    set userRole(value: UserRole) {
        this._userRole = value;
    }
}
