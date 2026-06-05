import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './componets/login/login.component';
import { UsuariosList } from './componets/usuarios-administrador/usuarios-list';
import { AuthGuard } from './guards/auth-guard';
import { Menu } from './componets/menu/menu.component';
import { HistoriaClinica } from './componets/historia-clinica/historia-clinica';
import { ConfirmarSalidaGuard } from './guards/confirmar-salida-guard';
import { Mishc } from './componets/mishc/mishc';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'administrador', component: UsuariosList, canActivate: [AuthGuard], canDeactivate: [ConfirmarSalidaGuard], }, //  protege esta ruta
  {path : 'menu', component: Menu, canActivate: [AuthGuard]},
  {path : 'hc/:curp', component: HistoriaClinica, canActivate : [AuthGuard], canDeactivate: [ConfirmarSalidaGuard]},
  {path : 'hc', component: HistoriaClinica, canActivate : [AuthGuard], canDeactivate: [ConfirmarSalidaGuard]},
  {path : 'mishc', component: Mishc, canActivate: [AuthGuard], canDeactivate: [ConfirmarSalidaGuard]},

  { path: '**', redirectTo: '', pathMatch: 'full' }
];



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
