import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ElementService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private httpClient: HttpClient) {}

  addElement(element: any): Observable<any> {
    const url = `${this.baseUrl}/add`;

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem("sessionId")}`
    });

    return this.httpClient.post(url, element, { headers });

  }
  getAllElements(element: any): Observable<any> {
    const url = `${this.baseUrl}/list`;

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem("sessionId")}`
    });

    return this.httpClient.post(url, element, { headers });
  }

  clearAllElements(element: any): Observable<any> {
    const url = `${this.baseUrl}/clearAll`;

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem("sessionId")}`
    });

    return this.httpClient.post(url, element, { headers });
  }
}
