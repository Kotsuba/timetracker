export class ChartUtil {

    public static getOptionsChartTicketCount(title?: string) {
        return {
            elements: {
                line: {
                    tension: 0,
                    fill: false
                },
            },
            legend: {position: 'bottom'},
            title: {
                display: true,
                text: title != null ? title : ''
            },
            scaleShowVerticalLines: false,
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                        min: 0
                    }
                }]
            }
        };
    }

    public static getOptionsChartAvgTimeTicket(title?: string) {
        return {
            elements: {
                line: {
                    tension: 0,
                    fill: false
                }
            },
            legend: {position: 'bottom'},
            title: {
                display: true,
                text: title != null ? title : ''
            },
            scaleShowVerticalLines: false,
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                        min: 0,
                        callback: function (value, index, values) {
                            return value + ' h';
                        },
                    }
                }]
            }
        };
    }

    public static getOptionsChartOtherMetric(title?: string) {
        return {
            elements: {
                line: {
                    tension: 0,//interpolation
                    fill: false //background line
                }
            },
            legend: {position: 'bottom'},
            title: {
                display: true,
                text: title != null ? title : ''
            },
            scaleShowVerticalLines: false,
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                        min: 0,
                        callback: function (value, index, values) {
                            return value + ' %';
                        },
                    }
                }],
            }
        };
    }

    public static getOptionsChartByMetric(title?: string) {
        return {
            elements: {
                line: {
                    tension: 0,
                    fill: false
                }
            },
            legend: {position: 'bottom'},
            title: {
                display: true,
                text: title != null ? title : ''
            },
            scaleShowVerticalLines: false,
            responsive: true,
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                        min: 0,
                    }
                }]
            }
        };
    }

    public static getOptionsChartPerformance(title?: string) {
        return {
            elements: {
                line: {
                    tension: 0
                }
            },
            legend: {position: 'right'},
            title: {
                display: true,
                text: title != null ? title : ''
            },
            scaleShowVerticalLines: false,
            responsive: true,
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                        min: 0,
                    }
                }]
            }
        };
    }
}