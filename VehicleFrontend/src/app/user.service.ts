import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Insurance } from './insurance.model';
import { UserWriter } from './user-writer.model';
@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getUserById(UserwriterId: String): Observable<any> {
    return this.http.get(
      `${this.baseUrl}/admin/searchUserWriterByID/${UserwriterId}`
    );
  }

  registerUser(userwriter: UserWriter): Observable<any> {
    return this.http.post<any>(
      `${this.baseUrl}/admin/insertUserwriter`,
      userwriter
    );
  }

  addInsurance(insurance: Insurance): Observable<any> {
    return this.http.post<any>(
      `${this.baseUrl}/vehicle/addInsurance`,
      insurance
    );
  }

  getInsuranceById(VehicleNo: String): Observable<any> {
    console.log(VehicleNo);
    return this.http.get(`${this.baseUrl}/vehicle/getVehicle/${VehicleNo}`);
  }

  getInsurancehistory(VehicleNo: String): Observable<any> {
    return this.http.get(`${this.baseUrl}/vehicle/getHistory/${VehicleNo}`);
  }

  deleteUser(UserwriterId: String): Observable<any> {
    return this.http.get(
      `${this.baseUrl}/admin/deleteUserWriter/${UserwriterId}`
    );
  }

  // Legacy login method (kept for backward compatibility)
  login(username: string, password: string, role: string): Observable<any> {
    const loginData = {
      name: username,
      password: password,
      role: role,
    };
    return this.http.post(`${this.baseUrl}/admin/login`, loginData);
  }

  // New JWT login method
  jwtLogin(username: string, password: string): Observable<any> {
    const loginData = {
      username: username,
      password: password,
    };
    return this.http.post(`${this.baseUrl}/auth/login`, loginData);
  }

  // Token management methods
  saveToken(token: string): void {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  saveUserData(username: string, role: string, underwriterId: string): void {
    localStorage.setItem('username', username);
    localStorage.setItem('role', role);
    localStorage.setItem('underwriterId', underwriterId);
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    localStorage.removeItem('role');
    localStorage.removeItem('underwriterId');
    sessionStorage.clear();
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  getRole(): string | null {
    return localStorage.getItem('role');
  }

  getUsername(): string | null {
    return localStorage.getItem('username');
  }

  getUnderwriterId(): string | null {
    return localStorage.getItem('underwriterId');
  }

  /**
   * Update the password of the user
   * @param payload Object containing `userId`, `oldPassword`, and `newPassword`
   */
  updatePassword(payload: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/admin/updatePassword`, payload);
  }
}

