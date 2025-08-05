import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BaseComponent } from './home/base/base.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { AuthInterceptor } from './core/AuthInterceptor';
import { RouterModule } from '@angular/router';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    BaseComponent,

   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule, 
    RouterModule, 
    BrowserAnimationsModule,
   ToastrModule.forRoot({ 
      timeOut: 3000,
      preventDuplicates: true,
      positionClass: 'toast-bottom-right'
    }),
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
