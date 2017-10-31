import {Headers, Response} from "@angular/http";
import {Injectable} from "@angular/core";

@Injectable()
export class UtilService {
    headers = new Headers();

    public static extractData(res: Response) {
        let body = res.json();
        return body || {};
    }

    public static getJSONBody(object) {
        return JSON.stringify(object);
    }

    public getHeaders() {
        return this.headers;
    }

    public setHeaders(token: string) {
        this.headers.set("Authorization", "Bearer " + token);
    }
}
