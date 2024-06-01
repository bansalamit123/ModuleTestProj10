import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import{HttpClientModule} from'@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpServiceService } from './http-service.service';
import { DoctorComponent } from './doctor/doctor.component';
import { DoctorlistComponent } from './doctor/doctorlist.component';

@NgModule({
  declarations: [
    AppComponent,
    DoctorComponent,
    DoctorlistComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
    
  ],
  providers: [
    HttpServiceService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
