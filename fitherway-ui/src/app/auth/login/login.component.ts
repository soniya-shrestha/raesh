import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { ToastService } from '../../service/toast.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss' 
})
export class LoginComponent {
  loginForm: FormGroup;
  isLoading = false;
  hidePassword = true; 
   passwordVisibility: boolean = false;
  isPasswordFocused: boolean = false;

  constructor(
    private router: Router,
    private authService: AuthService,
     private toast: ToastService,
  ) {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required])
    });
  }

  onSubmit() {
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      return;
    }

    this.isLoading = true; 
    localStorage.removeItem('access_token');
   
     this.authService.login(this.loginForm.value).subscribe({ 
      next: (response: any) => {
      if (response.status) {
        const rawRole = response.data.user?.role?.role;
        const roleMatch = rawRole?.match(/name=(\w+)/);
        const role = roleMatch ? roleMatch[1] : null;

        localStorage.setItem('access_token', response.data.accessToken);
        localStorage.setItem('user_role', role);
        localStorage.setItem('fullName', response.data.user?.userName);

        if (role) { 
          this.toast.showSuccess("Login Successfully!");
          this.redirectUser(role);
        } else {
          this.toast.showError("User role not found.");
          this.isLoading = false;
        }
      } else {
        this.toast.showError("Invalid credentials");
        this.isLoading = false;
      }
    },
    error: (err) => {
      this.toast.showError(err.error?.message || 'Login failed. Please try again.');
      this.isLoading = false;
    }
  });
  } 

    togglePasswordVisibility(): void {
    this.passwordVisibility = !this.passwordVisibility;
  }

  private redirectUser(role: string) {
    switch (role) {
      case 'ADMIN':
        this.router.navigate(['/admin/dashboard']);
        break;;
      case 'USER':
        this.router.navigate(['/user/dashboard']);
        break;
      default:
        this.router.navigate(['/']);
    }
  } 

  navigateToSignup(){
     this.router.navigate(['/quiz-session']);
  }
}
