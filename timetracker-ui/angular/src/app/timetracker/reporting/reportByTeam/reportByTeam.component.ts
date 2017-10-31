import {User} from "../../../shared/bean/user";
import {Component, Input, OnInit} from "@angular/core";
import {ChartInfo} from "../../../shared/bean/chartInfo";
import {Scope} from "../../../shared/bean/scope";
import {ScopeUtil} from "../../../shared/util/scope.util";
import {scopes} from "../../../shared/constants/scope";
import {AlertService} from "../../../shared/service/alert.service";
import {UserService} from "../../user/user.service";
import {ReportService} from "../report.service";
import {MetricReport} from "../../../shared/bean/metricReport";
import {ChartUtil} from "../../../shared/util/chart.util";
import {metrics} from "../../../shared/constants/metric";
import {Metric} from "../../../shared/bean/metric";
import {ActivatedRoute} from "@angular/router";
@Component({
    moduleId: module.id,
    selector: 'report-by-team',
    templateUrl: './reportByTeam.component.html'
})
export class ReportByTeamComponent implements OnInit {
    @Input()
    private userId: number;
    private user: User;

    scopes: Scope[] = scopes;
    scope: Scope = new Scope('SCOPE.WEEK', ScopeUtil.getWeek());

    metrics: Metric[] = metrics;
    metric: Metric;

    chartsInfo: ChartInfo[] = [];
    isDataAvailable: boolean = false;


    constructor(private userService: UserService,
                private alertService: AlertService,
                private reportService: ReportService,
                private router: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.router.params.subscribe(params => {
            if(params['userId']!=null){
                this.userId=params['userId'];
                this.getUser(this.userId);
            }else {
                this.getUser(this.userId);
            }
        });
    }

    private generateChartsByMetric(reports, users, dates, title) {
        let data = [];
        let chartData: number[] = [];
        for (let i = 0; i < users.length; i++) {
            let user: User = users[i];
            for (let j = 0; j < dates.length; j++) {
                let date = dates[j];
                let isProgress: boolean = false;
                for (let k = 0; k < reports.length; k++) {
                    let report: MetricReport = reports[k];
                    if ((report.userId == user.id) && (report.date == date)) {
                        chartData.push(report.progress);
                        isProgress = !isProgress;
                        break;
                    }
                }
                if (!isProgress) {
                    chartData.push(null);
                }
            }
            data.push(
                {
                    data: Object.assign(chartData),
                    label: user.login
                });
            chartData = [];
        }
        this.addChartInfo(dates, data, ChartUtil.getOptionsChartByMetric(this.metric.description), title);
    }

    private createChartsByMetric(metric: string, date: string, teamId?: number, teamName?: string) {
        this.reportService.getReportsAndUsersAndDatesByMetric(metric, date, teamId).subscribe(
            data => {
                this.generateChartsByMetric(data[0], data[1], data[2], teamName);
            }, error => this.alertService.errorHandler(error));
    }

    onScopeChange() {
        this.isDataAvailable = false;
        this.onMetricChange();
    }

    onMetricChange() {
        this.chartsInfo = [];
        if (this.metric != null && this.user != null) {
            if (this.user.team != null && this.user.team.length != 0) {
                for (let team of this.user.team) {
                    this.createChartsByMetric(this.metric.column, this.scope.date, team.teamId, team.teamName);
                }
            } else {
                this.createChartsByMetric(this.metric.column, this.scope.date);
            }
        }
    }

    private getUser(id: number) {
        this.userService.getUserById(id).subscribe(
            data => {
                this.user = data;
                this.onScopeChange();
            },
            error => this.alertService.errorHandler(error));
    }

    private addChartInfo(labels, data, options, title?) {
        let chartByMetric: ChartInfo = new ChartInfo();
        chartByMetric.addChartInfo(labels, data, options, title);
        this.chartsInfo.push(chartByMetric);
    }

    reload(userId: number) {
        this.getUser(userId);
    }
}
