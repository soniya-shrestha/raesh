import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { ToastService } from '../../service/toast.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.scss'
})
export class SignUpComponent {

  signupForm: FormGroup;
  profileImage: File | null = null;
  previewUrl: string | null = null; 
  passwordVisible = false;        
  confirmPasswordVisible = false;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router,  private toast: ToastService ) {
    this.signupForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.pattern(/^[0-9]{10}$/)]],
      address: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
      profilePicture: [null]
    }, { validators: this.passwordMatchValidator });
  }

  passwordMatchValidator(form: FormGroup) {
    return form.get('password')?.value === form.get('confirmPassword')?.value
      ? null : { mismatch: true };
  }

  onFileChange(event: Event): void {
    const file = (event.target as HTMLInputElement).files?.[0];
    this.profileImage = file || null;
    this.previewImage(file);
  }

  onDragOver(event: DragEvent): void {
    event.preventDefault();
  }

  onDrop(event: DragEvent): void {
    event.preventDefault();
    const file = event.dataTransfer?.files?.[0];
    this.profileImage = file || null;
    this.previewImage(file);
  }

  private previewImage(file: File | undefined) {
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        this.previewUrl = reader.result as string;
      };
      reader.readAsDataURL(file);
    }
  }

  onSubmit() {
    if (this.signupForm.invalid) { 
      this.signupForm.markAllAsTouched(); 
      this.toast.showError('Please fix form errors before submitting.');

      return;
    }

    const formValues = this.signupForm.value;
    const formData = new FormData();

    // Append required fields only
    formData.append('userName', formValues.username);
    formData.append('email', formValues.email);
    formData.append('phone', formValues.phone);
    formData.append('address', formValues.address);
    formData.append('password', formValues.password);

    if (this.profileImage) {
      formData.append('profilePic', this.profileImage);
    } 

     const quizId = localStorage.getItem('quizId');
  if (quizId) {
    formData.append('quizId', quizId);
  } else {
      this.toast.showError('Quiz ID not found. Please take the quiz first.');
    return;
  }

    this.authService.registerStudent(formData).subscribe({
      next: (response) => {
        console.log('Registration successful', response); 
        localStorage.setItem('userEmail', formValues.email);
        this.router.navigate(['/auth/otp-valid']); 
      },
      error: (error) => {
        console.error('Registration failed', error);
        this.toast.showError(error.error?.message || 'Registration failed. Please try again.');

      }
    });
  }
}