import {ScopeUtil} from "../util/scope.util";
import {Scope} from "../bean/scope";

export const scopes: Scope[] =
    [
        new Scope('SCOPE.WEEK', ScopeUtil.getWeek()),
        new Scope('SCOPE.MONTH', ScopeUtil.getMonth()),
        new Scope('SCOPE.THREE_MONTH', ScopeUtil.getThreeMonth()),
        new Scope('SCOPE.HALF_YEAR', ScopeUtil.getHalfYear()),
        new Scope('SCOPE.YEAR', ScopeUtil.getYear())
    ];