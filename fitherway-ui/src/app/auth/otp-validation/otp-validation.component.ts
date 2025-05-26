import { Component, ElementRef, QueryList, ViewChildren } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-otp-validation',
  templateUrl: './otp-validation.component.html',
  styleUrl: './otp-validation.component.scss'
})
export class OtpValidationComponent {
 otpArray = new Array(6);
 otpValues: string[] = ['', '', '', '', '', ''];
remainingTime: number = 180; // e.g., 3 minutes

formatTime(seconds: number): string {
  const min = Math.floor(seconds / 60);
  const sec = seconds % 60;
  return `${min}:${sec < 10 ? '0' + sec : sec}`;
}

moveToNext(index: number, event: Event) {
  const input = event.target as HTMLInputElement;
  if (input.value && index < this.otpValues.length - 1) {
    const nextInput = document.querySelectorAll('input')[index + 1] as HTMLInputElement;
    nextInput.focus();
  }
}
}