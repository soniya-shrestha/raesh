import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.scss'
})
export class SignUpComponent { 
  constructor(private route: Router){}

  onClick(){
  this.route.navigate(['auth/otp-valid']);
  }
}
