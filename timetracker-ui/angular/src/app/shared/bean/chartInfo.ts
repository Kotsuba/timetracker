export class ChartInfo {
    private _labels;
    private _data;
    private _options;
    private _title;

    addChartInfo(labels, data, options, title?) {
        this._labels = labels;
        this._data = data;
        this._options = options;
        this._title = title;
    }

    get labels() {
        return this._labels;
    }

    set labels(value) {
        this._labels = value;
    }

    get data() {
        return this._data;
    }

    set data(value) {
        this._data = value;
    }

    get options() {
        return this._options;
    }

    set options(value) {
        this._options = value;
    }

    get title() {
        return this._title;
    }

    set title(value) {
        this._title = value;
    }
}
