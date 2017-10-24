import {Pipe, PipeTransform} from '@angular/core';
import {TypeVacation} from "../bean/typevacation";

@Pipe({
    name: 'dayOffTypeFormatter'
})
export class DayOffTypePipe implements PipeTransform {
    transform(value: number, args?: any): string {

        switch (value) {
            case TypeVacation.PAID_VACATION:
                return "Paid vacation";
            case TypeVacation.UNPAID_VACATION:
                return "Unpaid vacation";
            case TypeVacation.ILLNESS:
                return "Illness";
        }
    }
}