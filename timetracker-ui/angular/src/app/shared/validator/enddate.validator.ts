
export class EndDateValidator {

    static less(startDate: Date, endDate: Date) {
        if (new Date(startDate) < new Date(endDate)) {
            return {notLess: true}
        }
        return null;
    }
}