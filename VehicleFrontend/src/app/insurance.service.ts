import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
 import { Observable } from 'rxjs';



import { Insurance } from './insurance';



@Injectable({

 providedIn: 'root',

})

export class InsuranceService {

 private apiUrl = 'http://localhost:8080/api/v1/insurance/1'; // Adjust according to your backend URL



 constructor(private http: HttpClient) {}



 getInsuranceByVehicleId(vehicleId: string): Observable<Insurance> {

   return this.http.get<Insurance>(`${this.apiUrl}`);

 }

}