import {Metric} from "../bean/metric";

export const metrics: Metric[] =
    [
        new Metric('inprogress ticket', 'rp_inprogress_count', '', 'inprogress ticket count'),
        new Metric('closed ticket', 'rp_closed_count', '', 'closed ticket count'),
        new Metric('fixed ticket', 'rp_fixed_count', '', 'fixed ticket count'),
        new Metric('reopened count ticket', 'rp_reopened_count', '', 'reopened count ticket'),
        new Metric('fixed/closed ticket count', 'rp_fixed_closed_count', '%', 'fixed ticket count/closed ticket count'),
        new Metric('fixed/closed ticket time', 'rp_fixed_closed_time', '%', 'fixed ticket time/closed ticket time'),
        new Metric('inprogress/all count', 'rp_inprogress_all_count', '%', 'inprogress ticket count/(closed ticket count + inprogress tickets count)'),
        new Metric('inprogress/all time', 'rp_inprogress_all_time', '%', 'inprogress ticket time/(closed ticket time + inprogress tickets time)'),
        new Metric('avg time per resolved ticket', 'rp_avg_time_resolved', 'h', 'time spent on fixed ticket / fixed ticket count'),
        new Metric('avg time per unresolved ticket', 'rp_avg_time_unresolved', 'h', 'time spent on closed ticket - time spent on fixed ticket) / (closed ticket count - fixed tickets count)'),
        new Metric('logged time on inprogress ticket', 'rp_inprogress_logged_time', 'h', 'all logged time on inprogress ticket'),
        new Metric('user ticket time / full ticket time', 'rp_ticket_full_time', '%', 'time spent on closed ticket + time spent on inprogress ticket) / (time spent on inprogress ticket + time spent on closed ticket + other help time)'),
        new Metric('user time / full user time', 'rp_user_full_time', '%', 'time spent on inprogress ticket + time spent on closed ticket) / (time spent on inprogress ticket + dev time spent on closed ticket + user help time)'),
        new Metric('jira time / timetracker time', 'rp_jira_timetracker_time', '%', 'jira logged time / timetracker logged time'),
        new Metric('timetracker time / door time', 'rp_door_timetracker_time', '%', 'timetracker logged time / door time'),
        new Metric('worked days', 'rp_worked_days', '', 'worked days'),
        new Metric('fixed ticket time / original estimate', 'rp_fixed_ticket_original_estimate', '%', 'fixed ticket time / original estimate')
    ];