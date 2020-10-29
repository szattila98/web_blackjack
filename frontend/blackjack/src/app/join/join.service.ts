import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../core/models/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JoinService {

  constructor(public http: HttpClient) { }

  joinUser(name: string): Observable<User> {
    return this.http.post<User>('/api/user', { inputValue: name });
  }
}
