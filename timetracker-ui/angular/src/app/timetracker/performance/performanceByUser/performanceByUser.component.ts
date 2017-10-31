import {Component, Input, OnInit} from "@angular/core";
import {Metric} from "../../../shared/bean/metric";
import {Scope} from "../../../shared/bean/scope";
import {ScopeUtil} from "../../../shared/util/scope.util";
import {metrics} from "../../../shared/constants/metric";
import {scopes} from "../../../shared/constants/scope";
import {ChartInfo} from "../../../shared/bean/chartInfo";
import {User} from "../../../shared/bean/user";
import {ReportService} from "../../reporting/report.service";
import {AlertService} from "../../../shared/service/alert.service";
import {UserService} from "../../user/user.service";
import {ChartUtil} from "../../../shared/util/chart.util";
import {MetricReport} from "../../../shared/bean/metricReport";
import {ActivatedRoute} from "@angular/router";
@Component({
    moduleId: module.id,
    selector: 'performance-by-user',
    templateUrl: './performanceByUser.component.html'
})
export class PerformanceByUserComponent implements OnInit {
    @Input()
    private userId: number;
    private user: User;

    metrics: Metric[] = metrics;
    metric: Metric;
    scopes: Scope[] = scopes;
    scope: Scope = new Scope('SCOPE.WEEK', ScopeUtil.getWeek());

    chartsInfo: ChartInfo[] = [];

    constructor(private userService: UserService,
                private alertService: AlertService,
                private reportService: ReportService,
                private router: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.router.params.subscribe(params => {
            if (params['userId'] != null) {
                this.userId = params['userId'];
                this.getUser(this.userId);
            } else {
                this.getUser(this.userId);
            }
        });
    }

    private generateChartsPerformance(reports, reportsMax, dates, title?) {
        let data = [];
        let chartData: number[] = [];
        let chartMaxData: number[] = [];
        for (let j = 0; j < dates.length; j++) {
            let date = dates[j];
            let isProgress: boolean = false;
            for (let k = 0; k < reports.length; k++) {
                let report: MetricReport = reports[k];
                if (report.date == date) {
                    chartData.push(report.progress);
                    isProgress = !isProgress;
                    break;
                }
            }
            if (!isProgress) {
                chartData.push(0);
            }
            let isProgressMax: boolean = false;
            for (let k = 0; k < reportsMax.length; k++) {
                let maxReport: MetricReport = reportsMax[k];
                if (maxReport.date == date) {
                    chartMaxData.push(maxReport.progress);
                    isProgressMax = !isProgressMax;
                    break;
                }
            }
            if (!isProgressMax) {
                chartMaxData.push(0);
            }
        }
        data.push(
            {
                data: chartData,
                label: this.user.login
            });
        data.push(
            {
                data: chartMaxData,
                label: 'max'
            });
        this.addChartInfo(dates, data, ChartUtil.getOptionsChartPerformance(this.metric.description), title);
    }

    private createChartsPerformance(userId: number, metric: string, date: string, teamId?: number, teamName?: string) {
        this.reportService.getReportsByUserIdAndReportsMaxValueAndDatesByMetric(userId, metric, date, teamId).subscribe(
            data => this.generateChartsPerformance(data[0], data[1], data[2], teamName),
            error => this.alertService.errorHandler(error));
    }

    private getUser(id: number) {
        this.userService.getUserById(id).subscribe(
            data => {
                this.user = data;
                this.onScopeChange();
            },
            error => this.alertService.errorHandler(error));
    }

    onMetricChange() {
        this.chartsInfo = [];
        if (this.metric != null) {
            if (this.user.team != null && this.user.team.length != 0) {
                for (let team of this.user.team) {
                    this.createChartsPerformance(this.user.id, this.metric.column, this.scope.date, team.teamId, team.teamName);
                }
            } else {
                this.createChartsPerformance(this.user.id, this.metric.column, this.scope.date);
            }
        }
    }

    onScopeChange() {
        this.onMetricChange();
    }

    reload(userId: number) {
        this.getUser(userId);
    }

    private addChartInfo(labels, data, options, title?) {
        let chartByMetric: ChartInfo = new ChartInfo();
        chartByMetric.addChartInfo(labels, data, options, title);
        this.chartsInfo.push(chartByMetric);
    }
}