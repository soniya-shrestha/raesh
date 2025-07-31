import { inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const platformId = inject(PLATFORM_ID);

  if (isPlatformBrowser(platformId)) {
    const token = localStorage.getItem('access_token');
    const rolesString = localStorage.getItem('user_role');

    // try {
    //   const roles = JSON.parse(rolesString || '[]');
    //   const role = roles.length > 0 ? roles[0].role : null;

      if (token && rolesString) {
        return true;
      }
    // } catch (error) {
    //   console.error('Invalid user_role format:', error);
    // }
  }

  router.navigate(['/auth/login']);
  return false;
};
