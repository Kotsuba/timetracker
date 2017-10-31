export class ApiConstants {
    private static API = 'timetracker/api';
    public static VACATION: string = ApiConstants.API + '/vacations';
    public static USERS: string = ApiConstants.API + '/users';
    public static USER: string = ApiConstants.USERS + '/user';
    public static REPORT: string = ApiConstants.API + '/reports';
    public static REPORT_MAX_VALUE: string = ApiConstants.REPORT + '/max';
    public static AUTH: string = ApiConstants.API + '/auth';
}