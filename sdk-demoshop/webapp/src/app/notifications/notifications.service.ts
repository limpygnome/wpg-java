import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment'

@Injectable()
export class NotificationsService {

    constructor(
        private http: HttpClient
    ) { }

    fetch()
    {
        return this.http.get(environment.baseUrl + "/notification");
    }

    delete(id)
    {
        return this.http.delete(environment.baseUrl + "/notification/" + id);
    }

    deleteAll()
    {
        return this.http.delete(environment.baseUrl + "/notification");
    }

}
