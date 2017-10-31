import {Component, OnInit, ViewChild} from '@angular/core';
import {AlertService} from '../../shared/service/alert.service';
import {LoggedUser} from '../../shared/service/loggedUser.service';
import {User} from '../../shared/bean/user';
import {UserService} from '../user/user.service';
import {UserRole} from '../../shared/bean/userRole';
import {PerformanceByUserComponent} from './performanceByUser/performanceByUser.component';

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
    this.user = this.loggedUser.user;
    this.isUserSelected = true;
  }

  onUserChange() {
    if (this.performanceByUser != null) {
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
