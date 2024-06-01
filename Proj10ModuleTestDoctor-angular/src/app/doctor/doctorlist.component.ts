import { Component, OnInit } from '@angular/core';
import { HttpServiceService } from '../http-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-doctorlist',
  templateUrl: './doctorlist.component.html',
  styleUrls: ['./doctorlist.component.css']
})
export class DoctorlistComponent  implements OnInit{



  form:any={
    searchParams:{},
    list:[],
    preload:[],
    pageNo:0,
    deleteParams:{},
    message:''
  }



  constructor(private httpService :HttpServiceService,private router:Router){

  }


  ngOnInit(): void {
    this.preload();
    this.search();
    
    
  }

  search(){
   
    var self=this;
    this.httpService.post('http://localhost:8080/Doctor/search/'  +this.form.pageNo,this.form.searchParams,function(res:any){
      self.form.list=res.result.data;
    })

    }

    onCheckboxChange(doctorId:number){
      this.form.deleteParams.id=doctorId;

    }

    edit(page:any){
        this.router.navigateByUrl(page);

    }

    delete(){
      var self=this;
      this.httpService.get('http://localhost:8080/Doctor/delete/'+this.form.deleteParams.id,function(res:any){
             self.form.message =res.result.message; 
           //  self.form.pageNo=0;
             self.search();
      })

      

    }

    preload(){
      var self=this;
      this.httpService.get('http://localhost:8080/Doctor/preload',function(res:any){
      self.form.preload=res.result;
      })
    }

}
