import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userId: string = '';

  constructor(private httpClient: HttpClient) {
  }

  registerUser() {
    this.httpClient.post<string>("http://localhost:8080/api/user/register", null).subscribe(data => {
      this.userId = data;
    });
  }

  getUserId(): string {
    return this.userId;
  }

  subscribeToUser(userId: string): Observable<boolean> {
    return this.httpClient.post<boolean>("http://localhost:8080/api/user/subscribe/" + userId, null);
  }

  unSubscribeUser(userId: string): Observable<boolean> {
    return this.httpClient.post<boolean>("http://localhost:8080/api/user/unSubscribe/" + userId, null);
  }
}
