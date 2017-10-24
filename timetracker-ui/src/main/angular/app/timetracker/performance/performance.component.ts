import {Component, OnInit, ViewChild} from "@angular/core";
import {MetricReport} from "../../shared/bean/metricReport";
import {metrics} from "../../shared/constants/metric";
import {scopes} from "../../shared/constants/scope";
import {ReportService} from "../reporting/report.service";
import {AlertService} from "../../shared/service/alert.service";
import {ChartUtil} from "../../shared/util/chart.util";
import {LoggedUser} from "../../shared/service/loggedUser.service";
import {ScopeUtil} from "../../shared/util/scope.util";
import {ChartInfo} from "../../shared/bean/chartInfo";
import {User} from "../../shared/bean/user";
import {UserService} from "../user/user.service";
import {UserRole} from "../../shared/bean/userRole";
import {Metric} from "../../shared/bean/metric";
import {Scope} from "../../shared/bean/scope";
import {PerformanceByUserComponent} from "./performanceByUser/performanceByUser.component";

@Component({
    moduleId: module.id,
    selector: 'performance',
    templateUrl: './performance.component.html'
})
export class PerformanceComponent implements OnInit {
    @ViewChild(PerformanceByUserComponent) performanceByUser: PerformanceByUserComponent;
    public users: User[];
    public user: User;
    public UserRole = UserRole;
    public isUsersAvailable: boolean = false;
    public isUserSelected: boolean = false;

    constructor(private alert: AlertService,
                private userService: UserService,
                public loggedUser: LoggedUser) {
    }


    ngOnInit(): void {
        this.getUsers();
        this.user=this.loggedUser.user;
        this.isUserSelected=true;
    }

    onUserChange() {
        if(this.performanceByUser!=null){
            this.performanceByUser.reload(this.user.id)
        }
    }

    private getUsers() {
        this.userService.getUsers().subscribe(
            data => {
                this.users = data;
                if (this.users.length != 0) {
                    this.isUsersAvailable = true;
                }
            },
            error => this.alert.errorHandler(error));
    }
}