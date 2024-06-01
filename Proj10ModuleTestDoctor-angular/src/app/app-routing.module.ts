import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DoctorComponent } from './doctor/doctor.component';
import { DoctorlistComponent } from './doctor/doctorlist.component';

const routes: Routes = [

  {
    path:'doctorlist',
    component:DoctorlistComponent
  },
  {
    path:'doctor/:id',
    component:DoctorComponent
  },
  {
    path:'doctor',
    component:DoctorComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
