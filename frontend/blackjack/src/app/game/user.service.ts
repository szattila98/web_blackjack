import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../core/models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(public http: HttpClient) { }

  getUser(userId: string): Observable<User> {
    return this.http.get<User>(`/api/user/${userId}`);
  }

  refillCurrency(userId: string, amount: number): Observable<User> {
    return this.http.post<User>(`/api/user/${userId}/currency/${amount}`, null);
  }
}
