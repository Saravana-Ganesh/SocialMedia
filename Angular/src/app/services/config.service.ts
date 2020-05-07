import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpParams } from '@angular/common/http';
import {Observable,throwError} from 'rxjs';
import {catchError,retry} from 'rxjs/operators'   
import { analyzeAndValidateNgModules } from '@angular/compiler';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {
  load = false;
  url = "http://localhost:8080";
  private overAllSharedData: any;
  constructor(private http:HttpClient) { }
  headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
  
  setOverAllSharedData(value){
    console.log('inside service')
    this.overAllSharedData = value;
    this.load = true;
  }
  getOverAllSharedData(){
   return this.overAllSharedData;
  }
  
  submitSignup(formData): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post<any>('Media/signup', formData, {headers: headers});
  }
  submitSignin(formData){
    return this.http.post<any>('Media/signin', formData, {headers: this.headers});
  }
  
  findFriends(data){
    return this.http.post<any>('Media/findFriends',data , {headers: this.headers});
  }
  addFriend(data){
    return this.http.post<any>('Media/addFriend',data , {headers: this.headers});
  }
  
  loadHomeContent(data){
    return this.http.post<any>('Media/home',data , {headers: this.headers});
  }

  deleteFriendRequest(data){
    return this.http.post<any>('Media/deleteFriendRequest',data , {headers: this.headers});
  }
  acceptFriendRequest(data){
    return this.http.post<any>('Media/acceptFriendRequest',data , {headers: this.headers});
  }
  viewSentRequests(data){
    return this.http.post<any>('Media/viewSentRequests',data , {headers: this.headers});
  }

  // , {headers: this.headers}
    
}
      