import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import { LoginComponent } from './login/login.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { OtpValidationComponent } from './otp-validation/otp-validation.component';



const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },


  
  {
    path: 'login', component: LoginComponent
  },

  {
    path: 'register', component: SignUpComponent
  },
  {
    path: 'forgot-password', component: ForgetPasswordComponent
  },

  {
    path: 'reset-password', component: ResetPasswordComponent
  },

  {
    path:'otp-valid', component: OtpValidationComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
