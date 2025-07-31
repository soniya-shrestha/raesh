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
 otpDetails!: FormGroup;
  email!: string;
  remainingTime = 180; // 3 minutes
  countdownInterval: any;
  otp: string = '';


 @ViewChildren('otpInput') otpInputs!: QueryList<ElementRef>;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const emailFromParams = params['email'];

      if (emailFromParams) {
        this.email = emailFromParams;
        localStorage.setItem('userEmail', this.email);
      } else {
        const storedEmail = localStorage.getItem('userEmail');
        if (storedEmail) {
          this.email = storedEmail;
        } else {
          console.error('Email not found in route or localStorage');
          this.router.navigate(['/email-entry']); // Redirect to enter email
        }
      }

      console.log('Email used for OTP:', this.email); // Debug log
    });

    this.otpDetails = this.fb.group({
      otp1: ['', [Validators.required, Validators.maxLength(1)]],
      otp2: ['', [Validators.required, Validators.maxLength(1)]],
      otp3: ['', [Validators.required, Validators.maxLength(1)]],
      otp4: ['', [Validators.required, Validators.maxLength(1)]],
      otp5: ['', [Validators.required, Validators.maxLength(1)]],
      otp6: ['', [Validators.required, Validators.maxLength(1)]]
    });

    this.startCountdown();
  }

  get otpControls() {
    return Object.keys(this.otpDetails.controls);
  }

  handlePaste(event: ClipboardEvent) {
    event.preventDefault();
    const pastedData = event.clipboardData?.getData('text') || '';

    if (pastedData.length === 6 && /^\d{6}$/.test(pastedData)) {
      pastedData.split('').forEach((char, index) => {
        if (this.otpInputs.get(index)) {
          this.otpInputs.get(index)!.nativeElement.value = char;
          this.otpDetails.controls[this.otpControls[index]].setValue(char);
        }
      });
      this.otpInputs.get(5)?.nativeElement.focus();
    }
  }

  otpCheck(): void {
    if (this.otpDetails.invalid) {
      console.warn('OTP form is invalid');
      return;
    }

    if (!this.email) {
      console.error('Email is undefined in otpCheck()');
      alert('Email is missing. Please try again.');
      return;
    }

    this.otp = this.otpControls
      .map(controlName => this.otpDetails.get(controlName)?.value)
      .join('');

    const payload = {
      otp: this.otp,
      email: this.email
    };

    console.log('Payload being sent to server:', payload);

    this.authService.otp(payload).subscribe({
      next: (res) => {
        console.log('OTP verification successful:', res);
        this.router.navigate(['/auth/login']);
      },
      error: (err) => {
        console.error('OTP verification failed:', err);
        alert('OTP verification failed. Please try again.');
      }
    });
  }


  startCountdown() {
    this.countdownInterval = setInterval(() => {
      if (this.remainingTime > 0) {
        this.remainingTime--;
      } else {
        clearInterval(this.countdownInterval);
      }
    }, 1000);
  }

formatTime(seconds: number): string {
  const min = Math.floor(seconds / 60);
  const sec = seconds % 60;
  return `${min}:${sec < 10 ? '0' + sec : sec}`;
}


  moveToNext(event: KeyboardEvent, index: number) {
    const input = event.target as HTMLInputElement;

    if (event.key >= '0' && event.key <= '9') {
      if (input.value.length === 1 && index < this.otpControls.length - 1) {
        this.otpInputs.get(index + 1)?.nativeElement.focus();
      }
    } else if (event.key === 'Backspace') {
      input.value = '';
      this.otpDetails.controls[this.otpControls[index]].setValue('');
      if (index > 0) {
        this.otpInputs.get(index - 1)?.nativeElement.focus();
      }
    }
  }
}