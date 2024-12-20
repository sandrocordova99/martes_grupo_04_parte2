import { Routes } from '@angular/router';
import { LoginComponent } from './componentens/usuario/login/login.component';
import path from 'path';
import { RegistrarComponent } from './componentens/usuario/registrar/registrar.component';
import { AppComponent } from './app.component';
import { AuthGuard } from './guards/auth.guard';
import { IndexComponent } from './componentens/usuario/index/index.component';
import { ListarProyectosComponent } from './componentens/proyecto/listar-proyectos/listar-proyectos.component';
import { AgregarTareaComponent } from './componentens/proyecto/agregar-tarea/agregar-tarea.component';
import { AgregarProyectoComponent } from './componentens/proyecto/agregar-proyecto/agregar-proyecto.component';

export const routes: Routes = [
    {path: 'LoginUsuario' ,   component:LoginComponent},
    {path: 'RegistrarUsuario' ,   component:RegistrarComponent },
    {path: 'agregarProyecto' ,   component:AgregarProyectoComponent },
    {path: 'listarProyecto' ,   component:ListarProyectosComponent , canActivate: [AuthGuard]},
    {path: 'agregarTarea' ,   component:AgregarTareaComponent , canActivate: [AuthGuard]},
    {path: 'index' ,   component:IndexComponent , canActivate: [AuthGuard]}
];
