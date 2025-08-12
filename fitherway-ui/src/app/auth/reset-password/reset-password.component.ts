import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.scss'
})
export class ResetPasswordComponent {
 changeForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private userService: AuthService,
    private toastr: ToastrService
  ) {
    this.changeForm = this.fb.group({
      oldPassword: ['', [Validators.required]],
      newPassword: ['', [Validators.required, Validators.minLength(6)]],
      confirmNewPassword: ['', [Validators.required]]
    });
  }

  onSubmit() {
    const { oldPassword, newPassword, confirmNewPassword } = this.changeForm.value;

    if (newPassword !== confirmNewPassword) {
      this.toastr.error('New passwords do not match', 'Validation Error');
      return;
    }

    const requestBody = {
      oldPassword,
      newPassword,
      confirmNewPassword
    };

    this.userService.changePassword(requestBody).subscribe({
      next: () => {
        this.toastr.success('Password changed successfully!', 'Success');
        this.changeForm.reset();
      },
      error: (err) => {
        const message = err.error?.message || 'Something went wrong.';
        this.toastr.error(message, 'Error');
      }
    });
  }
}
