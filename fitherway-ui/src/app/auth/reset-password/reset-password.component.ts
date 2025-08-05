import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.scss'
})
export class ResetPasswordComponent {
  changeForm: FormGroup;
  errorMessage: string = '';

  constructor(private fb: FormBuilder) {
    this.changeForm = this.fb.group({
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required]]
    });
  }

  onSubmit() {
    if (this.changeForm.valid) {
      const { password, confirmPassword } = this.changeForm.value;
      if (password === confirmPassword) {
        console.log('Password changed to:', password);
        // Replace with API call
      } else {
        this.errorMessage = 'Passwords do not match';
      }
    }
  }
}
