export class Vacation {
    id: number;
    userId: number;
    startDate: string;
    endDate: string;
    duration: number;
    payment: number;
    payDate: string;
    dayOffType: number;
    comment: string;

    constructor(userId: number, startDate: string, endDate: string,
        duration: number, payment: number,payDate:string, dayOffType: number, comment: string) {
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.payment = payment;
        this.payDate=payDate;
        this.dayOffType = dayOffType;
        this.comment = comment;
    }
}