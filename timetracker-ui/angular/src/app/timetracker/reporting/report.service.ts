import {Injectable} from "@angular/core";
import {Http, Response, Headers, RequestOptions, URLSearchParams} from '@angular/http';
import {ApiConstants} from "../../shared/constants/api";
import {UtilService} from "../../shared/service/util.service";
import {Observable} from "rxjs/Observable";
import 'rxjs/add/observable/forkJoin';

@Injectable()
export class ReportService {

    constructor(private http: Http,
                private utilService: UtilService) {
    }

    getReportByMetric(metric: string, date: string, teamId?: number) {
        const params: URLSearchParams = new URLSearchParams();
        params.set('metric', metric);
        if (date != null) {
            params.set('date', date);
        }
        if (teamId != null) {
            params.set('project', String(teamId));
        }
        return this.http.get(ApiConstants.REPORT, {search: params, headers: this.utilService.getHeaders()})
            .map(UtilService.extractData);
    }

    getReportByMetricMaxValue(metric: string, date: string, teamId?: number) {
        const params: URLSearchParams = new URLSearchParams();
        params.set('metric', metric);
        if (date != null) {
            params.set('date', date);
        }
        if (teamId != null) {
            params.set('project', String(teamId));
        }
        return this.http.get(ApiConstants.REPORT_MAX_VALUE, {search: params, headers: this.utilService.getHeaders()})
            .map(UtilService.extractData);
    }

    getReportByUserId(userId: number, date: string, metric?: string) {
        let params: URLSearchParams = new URLSearchParams();
        if (date != null) {
            params.set('date', date);
        }
        if (metric != null) {
            params.set('metric', metric);
        }
        return this.http.get(ApiConstants.REPORT + '/users/' + userId, {
            search: params,
            headers: this.utilService.getHeaders()
        })
            .map(UtilService.extractData);
    }

    getDates(date: string) {
        let params: URLSearchParams = new URLSearchParams();
        if (date != null) {
            params.set("date", date);
        }
        return this.http.get(ApiConstants.REPORT + '/dates', {search: params, headers: this.utilService.getHeaders()})
            .map(UtilService.extractData);
    }

    getUsers(date: string, teamId?: number) {
        let params: URLSearchParams = new URLSearchParams();
        if (date != null) {
            params.set('date', date);
        }
        if (teamId != null) {
            params.set('project', String(teamId));
        }
        return this.http.get(ApiConstants.REPORT + '/users', {search: params, headers: this.utilService.getHeaders()})
            .map(UtilService.extractData);
    }

    getReportsAndUsersAndDatesByMetric(metric: string, date: string, teamId?: number) {
        return Observable.forkJoin(
            this.getReportByMetric(metric, date, teamId),
            this.getUsers(date, teamId),
            this.getDates(date),
        );
    }

    getReportsAndDatesByUserId(userId: number, date: string) {
        return Observable.forkJoin(
            this.getReportByUserId(userId, date),
            this.getDates(date),
        );
    }

    getReportsByUserIdAndReportsMaxValueAndDatesByMetric(userId: number, metric: string, date: string, teamId?: number) {
        return Observable.forkJoin(
            this.getReportByUserId(userId, date, metric),
            this.getReportByMetricMaxValue(metric, date, teamId),
            this.getDates(date),
        );
    }

}
