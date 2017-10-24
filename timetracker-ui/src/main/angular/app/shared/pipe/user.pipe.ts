import {Pipe, PipeTransform} from "@angular/core";
import {User} from "../bean/user";

@Pipe({
    name: 'userFormatter'
})
export class UserPipe implements PipeTransform {
    transform(userId: number, users?: User[]): string {
        if(users!=null) {
            for (let i = 0; i < users.length; i++) {
                if (users[i].id == userId) {
                    return users[i].name;
                }
            }
        }
        return userId.toString();
    }
}
