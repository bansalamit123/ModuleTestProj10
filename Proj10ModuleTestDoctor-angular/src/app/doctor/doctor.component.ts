import { Component, OnInit } from '@angular/core';
import { HttpServiceService } from '../http-service.service';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.css']
})
export class DoctorComponent implements OnInit {



  form={

    id:null,
    doctorName:'',
   mobileNumber:'',
    expertise:'',
     dob:''
    
    
   
 }
 fileToUpload:any=null;


  constructor(private httpService:HttpServiceService,private httpClient:HttpClient,private route:ActivatedRoute){
    var self=this;
    httpService.getPathVariable(route,function(params:any){
             self.form.id=params["id"];
    })

  }


  ngOnInit(): void {

    if(this.form.id&&this.form.id>0){
      this.display();

    }
   
  }

  display(){
    var self=this;
    this.httpService.get('http://localhost:8080/Doctor/get/'+this.form.id,function(res:any){
          self.form=res.result.data;

    })
  }

  myFile(){
    var self=this;
    const fromData=new FormData();
    fromData.append('file',this.fileToUpload);
     this.httpClient.post('http://localhost:8080/Doctor/profilePic/'+this.form.id,fromData).subscribe(data=>{
  
    })
   }

   save(){
    var self=this;
    this.httpService.post('http://localhost:8080/Doctor/save/',this.form,function(res:any){
       self.form.id= res.result.data;
       self.myFile();
    })
   }
  
   onFileSelect(event:any){
       this.fileToUpload=event.target.files.item(0);
  
  
   }

}
