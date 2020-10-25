import { Injectable } from '@angular/core';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class IdentityService {

  LOCALSTORAGE_KEY = 'userData';

  constructor() { }

  storeIdentity(user: User): void {
    localStorage.setItem(this.LOCALSTORAGE_KEY, JSON.stringify(user));
  }

  getIdentity(): User {
    const userData = localStorage.getItem(this.LOCALSTORAGE_KEY);
    return JSON.parse(userData);
  }

  removeIdentity(): void {
    localStorage.removeItem(this.LOCALSTORAGE_KEY);
  }
}
