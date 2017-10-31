import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import 'rxjs/add/observable/forkJoin';
import {ApiConstants} from '../../shared/constants/api';
import {UtilService} from '../../shared/service/util.service';


@Injectable()
export class UserService {

    constructor(private http: Http,
                private utilService: UtilService) {
    }

    getUsers() {
        return this.http.get(ApiConstants.USERS + '.json', {headers: this.utilService.getHeaders()})
            .map(UtilService.extractData);
    }

    getUserByLogin(login: string) {
        return this.http.get(ApiConstants.USER + '/' + login + '.json', {headers: this.utilService.getHeaders()})
            .map(UtilService.extractData);
    }

    getUserById(id: number) {
        return this.http.get(ApiConstants.USER + '/' + id + '.json', {headers: this.utilService.getHeaders()})
            .map(UtilService.extractData);
    }
}
