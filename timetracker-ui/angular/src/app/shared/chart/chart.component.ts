import {Component, Input} from "@angular/core";

@Component({
    moduleId: module.id,
    selector: 'chart',
    templateUrl: './chart.component.html'
})
export class ChartComponent{
    @Input()
    public data: string[];
    @Input()
    public labels: number[];
    @Input()
    public legend: string[];
    public chartType: string = 'line';
    @Input()
    public options: any;
    @Input()
    public caption: string;

}
