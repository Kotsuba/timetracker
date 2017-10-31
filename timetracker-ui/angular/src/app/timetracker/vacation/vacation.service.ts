import {Injectable} from "@angular/core";
import {Http} from "@angular/http";

import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import "rxjs/add/observable/throw";
import {Vacation} from "../../shared/bean/vacation";
import {ApiConstants} from "../../shared/constants/api";
import {UtilService} from "../../shared/service/util.service";

@Injectable()
export class VacationService {

    constructor(private http: Http,
                private utilService: UtilService) {
    }

    getVacations(date: string) {
        return this.http.get(ApiConstants.VACATION + "/" + date + ".json",
            {headers: this.utilService.getHeaders()})
            .map(UtilService.extractData);
    }

    addVacation(vacation: Vacation) {
        let headers=this.utilService.getHeaders();
        headers.set('Content-type','application/json');
        return this.http.post(ApiConstants.VACATION,
            UtilService.getJSONBody(vacation),
            {headers: headers});
    }

    editVacation(vacation: Vacation) {
        let headers=this.utilService.getHeaders();
        headers.set('Content-type','application/json');
        return this.http.put(ApiConstants.VACATION + "/" + vacation.id,
            UtilService.getJSONBody(vacation),
            {headers: headers})
            .map(UtilService.extractData);
    }

    deleteVacation(vacation: Vacation) {
        return this.http.delete(ApiConstants.VACATION + "/" + vacation.id,
            {headers: this.utilService.getHeaders()});
    }
}