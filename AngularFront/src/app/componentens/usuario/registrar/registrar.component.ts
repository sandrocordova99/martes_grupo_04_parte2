import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet, RouterModule, Router } from '@angular/router';
import { UsuarioService } from '../../../servicio/usuario.service';
import { UserDTO } from '../../../modelos/UserDTO';

@Component({
  selector: 'app-registrar',
  standalone: true,
  imports: [CommonModule,RouterOutlet,RouterModule,FormsModule],
  templateUrl: './registrar.component.html',
  styleUrl: './registrar.component.css'
})
export class RegistrarComponent {


  constructor(private usuarioServicio : UsuarioService , private router : Router){}

  userDTO: UserDTO = {
    username: '', 
    password: '',
    isEnabled: true,
    accountNoExpired: true,
    accountNoLocked: true,
    credentialNoExpired: true,
    nombreCliente: '',
    dni: '',
    email: '',
    roles: []
  };

  mensaje: string = '';


  registrarUsuario(){
    if (!this.userDTO.username || !this.userDTO.password || !this.userDTO.email || !this.userDTO.dni || !this.userDTO.nombreCliente) {
      this.mensaje = 'Por favor, complete todos los campos obligatorios.';
      this.mensaje = '';
      return;
    }

    this.usuarioServicio.registrarUsuario(this.userDTO).subscribe({
      next: (response) => {
        this.mensaje = response 
        console.log(this.userDTO);
      } ,

      error: (err) => {
        this.mensaje = err;
        console.error(this.mensaje);
      }
      
    })
  }

}
