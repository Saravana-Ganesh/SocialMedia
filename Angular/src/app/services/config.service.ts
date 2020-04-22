import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpParams } from '@angular/common/http';
import {Observable,throwError} from 'rxjs';
import {catchError,retry} from 'rxjs/operators'   

@Injectable({
  providedIn: 'root'
})
export class ConfigService {
  url = "http://localhost:8080";
  constructor(private http:HttpClient) { }
  headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');

  
  
  submitSignup(formData): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post<any>('Media/signup', formData, {headers: headers});
  }
  submitSignin(formData){
    return this.http.post<any>('Media/signin', formData, {headers: this.headers});
  }

  // , {headers: this.headers}
    
}
      