import {Component, OnInit, ViewChild} from '@angular/core';
import {UserService} from '../user/user.service';
import {User} from '../../shared/bean/user';
import {AlertService} from '../../shared/service/alert.service';
import {ReportByUserComponent} from './reportByUser/reportByUser.component';
import {ReportByTeamComponent} from './reportByTeam/reportByTeam.component';

@Component({
    moduleId: module.id,
    selector: 'report',
    templateUrl: './report.component.html'
})
export class ReportComponent implements OnInit {
    @ViewChild(ReportByUserComponent) reportByUser: ReportByUserComponent;
    @ViewChild(ReportByTeamComponent) reportByTeam: ReportByUserComponent;
    users: User[];
    user: User;

    isDataAvailable: boolean = false;
    isUserSelected: boolean = false;

    ngOnInit(): void {
        this.getUsers();
    }

    constructor(private userService: UserService,
                private alert: AlertService) {
    }

    private getUsers() {
        this.userService.getUsers().subscribe(
            data => {
                this.users = data;
                this.isDataAvailable = true;
            },
            error => this.alert.errorHandler(error));
    }

    onUserChange() {
        this.isUserSelected = true;
        if (this.reportByUser != null) {
            this.reportByUser.reload(this.user.id);
        }
        if (this.reportByTeam != null) {
            this.reportByTeam.reload(this.user.id);
        }
    }
}
