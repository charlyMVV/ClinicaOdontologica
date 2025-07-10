// src/app/guards/auth.guard.ts
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(): boolean {
    const usuarioLogueado = sessionStorage.getItem('usuario');
    if (usuarioLogueado) {
      return true;
    } else {
      this.router.navigate(['/']); 
      return false;
    }
  }
}
