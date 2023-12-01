import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  private baseUrl = 'http://localhost:4200/app';

  constructor(private httpClient: HttpClient) {}

  registerUser(data: any): Observable<any> {
    const url = `${this.baseUrl}/register`;

    return this.httpClient.post(url, data);
  }
  loginUser(data: any): Observable<any> {
    const url = `${this.baseUrl}/login`;

    return this.httpClient.post(url, data);
  }
}
