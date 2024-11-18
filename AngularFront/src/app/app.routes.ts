import { Routes } from '@angular/router';
import { LoginComponent } from './componentens/usuario/login/login.component';
import path from 'path';
import { RegistrarComponent } from './componentens/usuario/registrar/registrar.component';
import { AppComponent } from './app.component';
import { AuthGuard } from './guards/auth.guard';
import { IndexComponent } from './componentens/usuario/index/index.component';

export const routes: Routes = [
    {path: '' , redirectTo:'LoginUsuario' , pathMatch:'full'},
    {path: 'LoginUsuario' ,   component:LoginComponent},
    {path: 'RegistrarUsuario' ,   component:RegistrarComponent },
    {path: 'index' ,   component:IndexComponent , canActivate: [AuthGuard]}
];
