import {ScopeUtil} from "../util/scope.util";
import {Scope} from "../bean/scope";

export const scopes: Scope[] =
    [
        new Scope('Week', ScopeUtil.getWeek()),
        new Scope('Month', ScopeUtil.getMonth()),
        new Scope('Three Month', ScopeUtil.getThreeMonth()),
        new Scope('Half-year', ScopeUtil.getHalfYear()),
        new Scope('Year', ScopeUtil.getYear())
    ];
