import { inject, PLATFORM_ID } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { isPlatformBrowser } from '@angular/common';

export const negateAuthGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const platformId = inject(PLATFORM_ID);

  // SSR-safe check for localStorage
  if (isPlatformBrowser(platformId)) {
    const token = localStorage.getItem('access_token');
    const role = localStorage.getItem('user_role');

    if (token && role) {
      switch (role) {
        case 'ADMIN':
          router.navigate(['/admin/dashboard']);
          break;
        case 'USER':
          router.navigate(['/user/dashboard']);
          break;
        default:
          router.navigate(['/auth/login']);
      } 
      console.log("negateAuthGuard:", { token, role });
      return false; 
    }
  }

  return true; 
};
