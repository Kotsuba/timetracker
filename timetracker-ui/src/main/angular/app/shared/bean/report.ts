export class Report {
    id: number;
    date: string;
    userId: number;
    inProgressTicketCount: number;
    closedTicketCount: number;
    fixedTicketCount: number;
    reopenedTicketCount: number;
    fixedClosedTicketCount: number;
    fixedClosedTicketTime: number;
    inProgressAllTicketCount: number;
    inProgressAllTicketTime: number;
    inProgressLoggedTime:number;
    avgTimeResolvedTicket: number;
    avgTimeUnResolvedTicket: number;
    ticketFullTime: number;
    userFullTime: number;
    jiraTimeTrackerTime: number;
    doorTimeTrackerTime: number;
    tickets:string;
    workedDays:number;
    workedDates:string;
    fixedTicketTimeOriginalEstimate:number;
}