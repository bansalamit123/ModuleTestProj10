import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {

  constructor(private httpClient:HttpClient,private router:Router) {
    
   }

  post(endpoint:any,bean:any,callback:any){
    return this.httpClient.post(endpoint,bean).subscribe(data=>{
      callback(data);
    })
  }

  get(endpoint:any,callback:any){ 
    return this.httpClient.get(endpoint).subscribe(data=>{
      callback(data);
    })
  }

  getPathVariable(route:ActivatedRoute,callback:any){
    route.params.subscribe(params=>{
      callback(params)
    })

  }   
}
