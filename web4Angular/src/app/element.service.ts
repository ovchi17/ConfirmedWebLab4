import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ElementService {
  private baseUrl = 'http://localhost:4200/app';

  constructor(private httpClient: HttpClient) {}

  addElement(element: any): Observable<any> {
    const url = `${this.baseUrl}/add`;

    return this.httpClient.post(url, element);
  }
  getAllElements(element: any): Observable<any> {
    const url = `${this.baseUrl}/getAll`;

    return this.httpClient.get(url, element);
  }

  clearAllElements(element: any): Observable<any> {
    const url = `${this.baseUrl}/clearAll`;

    return this.httpClient.post(url, element);
  }
}
