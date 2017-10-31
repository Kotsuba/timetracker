import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Http, HttpModule} from '@angular/http';

import {VacationComponent} from './timetracker/vacation/vacation.component';
import {VacationService} from './timetracker/vacation/vacation.service';
import {UserService} from './timetracker/user/user.service';

import {AuthorizationComponent} from './timetracker/authorization/authorization.component';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from './shared/guard/authguard';
import {NguiDatetimePickerModule} from '@ngui/datetime-picker';
import {UserPipe} from './shared/pipe/user.pipe';
import {DayOffTypePipe} from './shared/pipe/dayofftype.pipe';
import {ReportComponent} from './timetracker/reporting/report.component';
import {ReportService} from './timetracker/reporting/report.service';
import {ChartsModule} from 'ng2-charts';
import {AppComponent} from './app.component';
import {ChartComponent} from './shared/chart/chart.component';
import {PushNotificationsService, SimpleNotificationsModule} from 'angular2-notifications';
import {AuthService} from './timetracker/authorization/authorization.service';
import {PerformanceComponent} from './timetracker/performance/performance.component';
import {UtilService} from './shared/service/util.service';
import {CookieService} from 'angular2-cookie/core';
import {AlertService} from './shared/service/alert.service';
import {LoggedUser} from './shared/service/loggedUser.service';
import {AdminGuard} from './shared/guard/adminguard';
import {ReportByUserComponent} from './timetracker/reporting/reportByUser/reportByUser.component';
import {ReportByTeamComponent} from './timetracker/reporting/reportByTeam/reportByTeam.component';
import {PerformanceByUserComponent} from './timetracker/performance/performanceByUser/performanceByUser.component';


const appRoutes: Routes = [
  {path: 'login', component: AuthorizationComponent},
  {path: 'logout', component: AuthorizationComponent},
  {path: 'vacation', component: VacationComponent, canActivate: [AuthGuard, AdminGuard]},
  {path: 'report', component: ReportComponent, canActivate: [AuthGuard, AdminGuard]},
  {path: 'report/user/:userId', component: ReportByUserComponent, canActivate: [AuthGuard, AdminGuard]},
  {path: 'report/team/:userId', component: ReportByTeamComponent, canActivate: [AuthGuard, AdminGuard]},
  {path: 'performance', component: PerformanceComponent, canActivate: [AuthGuard]},
  {path: 'performance/user/:userId', component: PerformanceByUserComponent, canActivate: [AuthGuard]},
  {path: '**', redirectTo: 'login'}
];


@NgModule({
  imports: [
    NguiDatetimePickerModule,
    BrowserModule,
    HttpModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    ChartsModule,
    SimpleNotificationsModule.forRoot(),
  ],
  declarations: [
    VacationComponent,
    ReportComponent,
    DayOffTypePipe,
    UserPipe,
    AppComponent,
    AuthorizationComponent,
    ChartComponent,
    PerformanceComponent,
    ReportByUserComponent,
    ReportByTeamComponent,
    PerformanceByUserComponent
  ],
  providers: [
    VacationService,
    UserService,
    AuthService,
    ReportService,
    AuthGuard,
    AdminGuard,
    UtilService,
    PushNotificationsService,
    CookieService,
    AlertService,
    LoggedUser
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
}
