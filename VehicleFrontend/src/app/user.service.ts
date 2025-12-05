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

  constructor(private http: HttpClient) {}

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

  login(username: string, password: string, role: string): Observable<any> {
    const loginData = {
      name: username,
      password: password,
      role: role,
    };
    return this.http.post(`${this.baseUrl}/admin/login`, loginData);
  }

  /**
   * Update the password of the user
   * @param payload Object containing `userId`, `oldPassword`, and `newPassword`
   */
  updatePassword(payload: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/admin/updatePassword`, payload);
  }
}
