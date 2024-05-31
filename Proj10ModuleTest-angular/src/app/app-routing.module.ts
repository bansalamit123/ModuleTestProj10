import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserlistComponent } from './user/userlist.component';
import { UserComponent } from './user/user.component';

const routes: Routes = [
  {
    path:'userlist',
    component:UserlistComponent
  },
  {
    path:'user',
    component:UserComponent
  },
  {
    path:'user/:id',
      component:UserComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
