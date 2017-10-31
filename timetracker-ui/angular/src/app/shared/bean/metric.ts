export class Metric {
    name:string;
    column:string;
    dimension: string;
    description:string;

    constructor(name: string, column: string, dimension: string, description: string) {
        this.name = name;
        this.column = column;
        this.dimension = dimension;
        this.description = description;
    }
}
