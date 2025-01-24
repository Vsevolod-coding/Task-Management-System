import { Injectable } from '@angular/core';

const TOKEN_KEY = 'token'; // Константа для ключа токена
const USER_KEY = 'user';   // Константа для ключа пользователя

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  static saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.setItem(TOKEN_KEY, token);
    console.log(`${token} SAVED TOKEN`); // Лог токена
  }

  static saveUser(user: any): void {
    window.localStorage.removeItem(USER_KEY);
    console.log(JSON.stringify(user) + " SIMPLIFIED [USER]");
    window.localStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  static getToken(): string | null {
    const token = localStorage.getItem(TOKEN_KEY);
    return token;
  }

  static getUser(): any {
    const user = localStorage.getItem(USER_KEY);
    return user ? JSON.parse(user) : null;
  }

  static getUserRole(): string {
    const user = this.getUser();
    return user ? user.role : '';
  }

  static isAdminLoggedIn(): boolean {
    const role: string = this.getUserRole();
    return role === 'ADMIN';
  }

  static isEmployeeLoggedIn(): boolean {
    const role: string = this.getUserRole();
    return role === 'EMPLOYEE';
  }

  static getUserId(): string | null {
    const user = this.getUser();
    return user ? user.id : null;
  }

  static hasToken(): boolean {
    return !!this.getToken();
  }

  static signout(): void {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.removeItem(USER_KEY);
  }
}
