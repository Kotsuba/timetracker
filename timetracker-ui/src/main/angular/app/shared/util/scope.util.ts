
export class ScopeUtil {
    static getWeek(){
        let date=new Date();
        date.setDate(date.getDate()-7);
        return ScopeUtil.format(date);
    }
    static getMonth(){
        let date=new Date();
        date.setMonth(date.getMonth()-1);
        return ScopeUtil.format(date);
    }
    static getThreeMonth(){
        let date=new Date();
        date.setMonth(date.getMonth()-3);
        return ScopeUtil.format(date);
    }
    static getHalfYear(){
        let date=new Date();
        date.setMonth(date.getMonth()-6);
        return ScopeUtil.format(date);
    }
    static getYear(){
        let date=new Date();
        date.setDate(date.getMonth()-12);
        return ScopeUtil.format(date);
    }

    private static format(date:Date){
        return date.toISOString().slice(0, 10)
    }

}
