import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.scss'
})
export class SignUpComponent { 

 signupForm: FormGroup;
  profileImage: File | null = null; 
  previewUrl: string | null = null;

  constructor(private fb: FormBuilder) {
    this.signupForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.pattern(/^[0-9]{10}$/)]],
      address: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
      profilePicture: [null]
    }, { validator: this.passwordMatchValidator });
  }

  passwordMatchValidator(form: FormGroup) {
    return form.get('password')?.value === form.get('confirmPassword')?.value
      ? null : { mismatch: true };
  }



onFileChange(event: Event): void {
  const file = (event.target as HTMLInputElement).files?.[0];
  this.previewImage(file);
}

onDragOver(event: DragEvent): void {
  event.preventDefault();
}

onDrop(event: DragEvent): void {
  event.preventDefault();
  const file = event.dataTransfer?.files?.[0];
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
      return;
    }

    const formData = new FormData();
    Object.entries(this.signupForm.value).forEach(([key, value]) => {
      if (key === 'profilePicture' && this.profileImage) {
        formData.append(key, this.profileImage);
      } else {
        formData.append(key, value as string);
      }
    });

    // Send formData to backend
    console.log('Form submitted', formData);
  }
}

