export class User{
    id:number;
    name:string;
    manager:number;
    coManager:number;
    login:string;
    viewManager:number;
    team:any[];
    constructor(name:string){
        this.name=name;
        this.coManager=null;
        this.manager=null;
        this.login=null;
        this.team=null;
        this.viewManager=null;
    }
}