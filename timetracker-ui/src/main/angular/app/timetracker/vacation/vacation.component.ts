import {Component, OnInit} from "@angular/core";

import {VacationService} from "./vacation.service";
import {UserService} from "../user/user.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "../../shared/bean/user";
import {Vacation} from "../../shared/bean/vacation";
import {UserRole} from "../../shared/bean/userRole";
import {TypeVacation} from "../../shared/bean/typevacation";
import {AlertService} from "../../shared/service/alert.service";
import {LoggedUser} from "../../shared/service/loggedUser.service";

@Component({
    moduleId: module.id,
    selector: 'vacation',
    templateUrl: './vacation.component.html'
})
export class VacationComponent implements OnInit {
    workedUser: User = new User("");
    TypeVacation = TypeVacation;
    vacation: Vacation = new Vacation(null, null, null, null, null, null, TypeVacation.UNPAID_VACATION, null);

    users: User[];
    vacations: Vacation[];
    vacationForm: FormGroup;

    startDate: Date;
    endDate: Date;
    payDate: Date;
    date: string;

    UserRole = UserRole;
    validForm: boolean = false;
    isDataAvailable: boolean = false;


    constructor(private vacationService: VacationService,
                private userService: UserService,
                private fb: FormBuilder,
                private alert:AlertService,
                public loggedUser:LoggedUser) {
    }

    buildForm(): void {
        this.vacationForm = this.fb.group({
            'worker': [this.workedUser.id, Validators.required],
            'startDate': [this.startDate, Validators.required],
            'endDate': [this.endDate, Validators.required],
            'duration': [this.vacation.duration, Validators.required],
            'payment': [this.vacation.payment],
            'payDate': [this.payDate],
            'dayOffType': [this.vacation.dayOffType, Validators.required],
            'comment': [this.vacation.comment]
        })
    }

    ngOnInit() {
        this.getUsers();
        this.buildForm();
    }

    getVacations(date: string): void {
        this.vacationService.getVacations(date).subscribe(
            data => {
            this.vacations = data;
            this.isDataAvailable = !this.isDataAvailable;
        }, error => this.alert.errorHandler(error));
    }

    onSubmit(): void {
        this.isDataAvailable = false;
        if (this.startDate != null && this.startDate.toString().length != 0) {
            this.vacation.startDate = this.startDate.toString();
        } else {
            this.vacation.startDate = null;
        }
        if (this.endDate != null && this.endDate.toString().length != 0) {
            this.vacation.endDate = this.endDate.toString();
        } else {
            this.vacation.endDate = null;
        }
        if (this.payDate != null && this.payDate.toString().length != 0) {
            this.vacation.payDate = this.payDate.toString();
        } else {
            this.vacation.payDate = null;
        }
        this.vacation.userId = this.workedUser.id;
        if (this.vacation.id == null) {
            this.vacationService.addVacation(this.vacation).subscribe(
                data => {
                    this.onDateChange();
                    this.alert.successHandler("Added successfully");
                }, error => this.alert.errorHandler(error));
        } else {
            this.vacationService.editVacation(this.vacation).subscribe(
                data => {
                    this.onDateChange();
                    this.alert.successHandler("Edited successfully");
                },
                error => this.alert.errorHandler(error));
        }
    }

    deleteVacation(vacation: Vacation): void {
        this.vacationService.deleteVacation(vacation).subscribe(
            data => {
                this.onDateChange();
                this.alert.successHandler("Deleted successfully");
            }, error => this.alert.errorHandler(error));
    }

    loadDataInForm(vacation: Vacation): void {
        this.vacation = vacation;
        this.workedUser.id = vacation.userId;
        if (this.vacation.payDate != null) {
            this.payDate = new Date(this.vacation.payDate);
        }
        else {
            this.payDate = null;
        }
        if (this.vacation.endDate != null) {
            this.endDate = new Date(this.vacation.endDate);
        } else {
            this.endDate = null;
        }
        if (this.vacation.endDate != null) {
            this.startDate = new Date(this.vacation.startDate);
        } else {
            this.startDate = null;
        }
    }

    getUsers() {
        this.userService.getUsers().subscribe(
            data => {
                this.users = data;
            }, error => this.alert.errorHandler(error));
    }

    onChangeDayOffType(entry: number): void {
        this.vacation.dayOffType = entry;
    }

    permission(startDate: string) {
        let date = new Date();
        date.setMonth(date.getMonth() - 2);
        let vacationDate = new Date(startDate);
        return vacationDate < date;
    }

    onDateChange() {
        this.isDataAvailable = false;
        if (this.date != null) {
            this.getVacations(this.date.toString());
        } else {
            this.getVacations(new Date().toISOString().slice(0, 10));
        }
    }

    isEndDateValid() {
        let startDate = new Date(this.startDate);
        let endDate = new Date(this.endDate);
        this.validForm = startDate <= endDate;
        return this.validForm;
    }
}
