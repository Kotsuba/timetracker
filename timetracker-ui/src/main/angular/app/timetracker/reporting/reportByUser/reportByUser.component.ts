import {Component, Input, OnInit} from "@angular/core";
import {Report} from "../../../shared/bean/report";
import {scopes} from "../../../shared/constants/scope";
import {ChartUtil} from "../../../shared/util/chart.util";
import {User} from "../../../shared/bean/user";
import {UserService} from "../../user/user.service";
import {AlertService} from "../../../shared/service/alert.service";
import {ReportService} from "../report.service";
import {ScopeUtil} from "../../../shared/util/scope.util";
import {Scope} from "../../../shared/bean/scope";
import {ActivatedRoute} from "@angular/router";
@Component({
    moduleId: module.id,
    selector: 'report-by-user',
    templateUrl: './reportByUser.component.html'
})
export class ReportByUserComponent implements OnInit {
    @Input()
    private userId: number;
    private user: User;

    report: Report;
    reports: Report[];

    scopes: Scope[] = scopes;
    scope: Scope = new Scope('SCOPE.WEEK', ScopeUtil.getWeek());

    chartTicketCountData: any[] = [];
    chartAvgTicketCountData: any[] = [];
    chartAvgTimeTicketData: any[] = [];
    chartOtherMetricData: any[] = [];

    labels: any[] = [];

    ticketCountOptions: {};
    avgTicketCountOptions: {};
    avgTimeTicketOptions: {};
    otherMetricOptions: {};

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

    private generateChartTicketCount(reports, dates): void {
        this.chartTicketCountData = [];
        this.ticketCountOptions = ChartUtil.getOptionsChartTicketCount(this.user.name + ' [' + this.user.login + ']');
        let inProgressTicketCount: number[] = [];
        let closedTicketCount: number[] = [];
        let fixedTicketCount: number[] = [];
        let reopenedTicketCount: number[] = [];
        for (let j = 0; j < dates.length; j++) {
            let date = dates[j];
            let isProgress: boolean = false;
            for (let i = 0; i < reports.length; i++) {
                let report: Report = reports[i];
                if (report.date == date) {
                    inProgressTicketCount.push(report.inProgressTicketCount);
                    closedTicketCount.push(report.closedTicketCount);
                    fixedTicketCount.push(report.fixedTicketCount);
                    reopenedTicketCount.push(report.reopenedTicketCount);
                    isProgress = !isProgress;
                    break;
                }
            }
            if (!isProgress) {
                inProgressTicketCount.push(null);
                closedTicketCount.push(null);
                fixedTicketCount.push(null);
                reopenedTicketCount.push(null);
            }
        }
        this.chartTicketCountData.push(
            {
                data: inProgressTicketCount,
                label: 'inprogress ticket count'
            },
            {
                data: closedTicketCount,
                label: 'closed ticket count'
            },
            {
                data: fixedTicketCount,
                label: 'fixed ticket count'
            },
            {
                data: reopenedTicketCount,
                label: 'reopened count ticket'
            });
    }

    private generateChartAvgTicketCount(reports, dates): void {
        this.chartAvgTicketCountData = [];
        this.avgTicketCountOptions = ChartUtil.getOptionsChartTicketCount(this.user.name + ' [' + this.user.login + ']');
        let inProgressAvgTicketCount: number[] = [];
        let closedAvgTicketCount: number[] = [];
        let fixedAvgTicketCount: number[] = [];
        let reopenedAvgTicketCount: number[] = [];
        for (let j = 0; j < dates.length; j++) {
            let date = dates[j];
            let isProgress: boolean = false;
            for (let i = 0; i < reports.length; i++) {
                let report: Report = reports[i];
                if (report.date == date) {
                    inProgressAvgTicketCount.push(Number((report.inProgressTicketCount / report.workedDays).toFixed(2)));
                    closedAvgTicketCount.push(Number((report.closedTicketCount / report.workedDays).toFixed(2)));
                    fixedAvgTicketCount.push(Number((report.fixedTicketCount / report.workedDays).toFixed(2)));
                    reopenedAvgTicketCount.push(Number((report.reopenedTicketCount / report.workedDays).toFixed(2)));
                    isProgress = !isProgress;
                    break;
                }
            }
            if (!isProgress) {
                inProgressAvgTicketCount.push(null);
                closedAvgTicketCount.push(null);
                fixedAvgTicketCount.push(null);
                reopenedAvgTicketCount.push(null);
            }
        }
        this.chartAvgTicketCountData.push(
            {
                data: inProgressAvgTicketCount,
                label: 'avg inprogress ticket count'
            },
            {
                data: closedAvgTicketCount,
                label: 'avg closed ticket count'
            },
            {
                data: fixedAvgTicketCount,
                label: 'avg fixed ticket count'
            },
            {
                data: reopenedAvgTicketCount,
                label: 'avg reopened count ticket'
            });
    }

    private generateChartAvgTimeTicket(reports, dates): void {
        this.chartAvgTimeTicketData = [];
        this.avgTimeTicketOptions = ChartUtil.getOptionsChartAvgTimeTicket(this.user.name + ' [' + this.user.login + ']');
        let avgTimeUnResolvedTicket: number[] = [];
        let avgTimeResolvedTicket: number[] = [];
        let inProgressLoggedTime: number[] = [];
        for (let j = 0; j < dates.length; j++) {
            let date = dates[j];
            let isProgress: boolean = false;
            for (let i = 0; i < reports.length; i++) {
                let report: Report = reports[i];
                if (report.date == date) {
                    avgTimeUnResolvedTicket.push(report.avgTimeUnResolvedTicket);
                    avgTimeResolvedTicket.push(report.avgTimeResolvedTicket);
                    inProgressLoggedTime.push(report.inProgressLoggedTime);
                    isProgress = !isProgress;
                    break;
                }
            }
            if (!isProgress) {
                avgTimeUnResolvedTicket.push(null);
                avgTimeResolvedTicket.push(null);
                inProgressLoggedTime.push(null);
            }
        }
        this.chartAvgTimeTicketData.push(
            {
                data: avgTimeResolvedTicket,
                label: 'time spent on fixed ticket / fixed ticket count'
            },
            {
                data: avgTimeUnResolvedTicket,
                label: 'time spent on closed ticket - time spent on fixed ticket) / (closed ticket count - fixed tickets count)'
            },
            {
                data: inProgressLoggedTime,
                label: 'inprogress logged time'
            });
    }

    private generateChartOtherMetric(reports, dates): void {
        this.chartOtherMetricData = [];
        this.otherMetricOptions = ChartUtil.getOptionsChartOtherMetric(this.user.name + ' [' + this.user.login + ']');
        let fixedClosedTicketCount: number[] = [];
        let fixedClosedTicketTime: number[] = [];
        let inProgressAllTicketTime: number[] = [];
        let inProgressAllTicketCount: number[] = [];
        let ticketFullTime: number[] = [];
        let userFullTime: number[] = [];
        let jiraTimeTrackerTime: number[] = [];
        let doorTimeTrackerTime: number[] = [];
        let fixedTicketTimeOriginalEstimate: number[] = [];
        for (let j = 0; j < dates.length; j++) {
            let date = dates[j];
            let isProgress: boolean = false;
            for (let i = 0; i < reports.length; i++) {
                let report: Report = reports[i];
                if (report.date == date) {
                    fixedClosedTicketCount.push(report.fixedClosedTicketCount);
                    fixedClosedTicketTime.push(report.fixedClosedTicketTime);
                    inProgressAllTicketTime.push(report.inProgressAllTicketTime);
                    inProgressAllTicketCount.push(report.inProgressAllTicketCount);
                    ticketFullTime.push(report.ticketFullTime);
                    userFullTime.push(report.userFullTime);
                    jiraTimeTrackerTime.push(report.jiraTimeTrackerTime);
                    doorTimeTrackerTime.push(report.doorTimeTrackerTime);
                    fixedTicketTimeOriginalEstimate.push(report.fixedTicketTimeOriginalEstimate);
                    isProgress = !isProgress;
                    break;
                }
            }
            if (!isProgress) {
                fixedClosedTicketCount.push(null);
                fixedClosedTicketTime.push(null);
                inProgressAllTicketTime.push(null);
                inProgressAllTicketCount.push(null);
                ticketFullTime.push(null);
                userFullTime.push(null);
                jiraTimeTrackerTime.push(null);
                doorTimeTrackerTime.push(null);
                fixedTicketTimeOriginalEstimate.push(null);
            }

        }
        this.chartOtherMetricData.push(
            {
                data: fixedClosedTicketCount,
                label: 'fixed ticket count/closed ticket count'
            },
            {
                data: fixedClosedTicketTime,
                label: 'fixed ticket time/closed ticket time'
            },
            {
                data: inProgressAllTicketTime,
                label: 'inprogress ticket count/(closed ticket count + inprogress tickets count)'
            },
            {
                data: inProgressAllTicketCount,
                label: 'inprogress ticket time/(closed ticket time + inprogress tickets time)'
            },
            {
                data: ticketFullTime,
                label: 'time spent on closed ticket + time spent on inprogress ticket) / (time spent on inprogress ticket + time spent on closed ticket + other help time)'
            },
            {
                data: userFullTime,
                label: 'time spent on inprogress ticket + time spent on closed ticket) / (time spent on inprogress ticket + time spent on closed ticket + user help time)'
            },
            {
                data: jiraTimeTrackerTime,
                label: 'jira logged time / timetracker logged time'
            },
            {
                data: doorTimeTrackerTime,
                label: 'timetracker logged time / door time'
            },
            {
                data: fixedTicketTimeOriginalEstimate,
                label: 'fixed ticket time / original estimate'
            });
    }

    private generateCharts(reports, dates) {
        if (this.reports != null && this.reports.length != 0) {
            this.generateChartTicketCount(reports, dates);
            this.generateChartAvgTicketCount(reports, dates);
            this.generateChartOtherMetric(reports, dates);
            this.generateChartAvgTimeTicket(reports, dates);
            this.isDataAvailable = true;
        }
    }

    private createCharts(userId: number, date: string) {
        this.reportService.getReportsAndDatesByUserId(userId, date).subscribe(
            data => {
                this.reports = data[0];
                this.labels = data[1];
                this.generateCharts(data[0], data[1]);
            }, error => this.alertService.errorHandler(error));
    }

    private getUser(id: number) {
        this.userService.getUserById(id).subscribe(
            data => {
                this.user = data;
                this.onScopeChange();
            },
            error => this.alertService.errorHandler(error));
    }

    onScopeChange() {
        this.isDataAvailable = false;
        this.createCharts(this.userId, this.scope.date);
    }

    reload(userId: number) {
        this.getUser(userId);
    }

    onTicketChange() {
        window.open('https://jira.touchcommerce.com/issues/?jql=key in(' + this.report.tickets + ')', "_blank");
    }
}
