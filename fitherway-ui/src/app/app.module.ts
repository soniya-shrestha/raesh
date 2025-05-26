import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BaseComponent } from './home/base/base.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { AuthInterceptor } from './core/AuthInterceptor';
import { DashboardComponent } from './Admin/dashboard/dashboard.component';
import { UserManagementComponent } from './Admin/user-management/user-management.component';

@NgModule({
  declarations: [
    AppComponent,
    BaseComponent,
    DashboardComponent,
    UserManagementComponent

   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [
    provideClientHydration(), 
    {
     provide: HTTP_INTERCEPTORS,
     useClass: AuthInterceptor,
      multi: true
    }
   
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
