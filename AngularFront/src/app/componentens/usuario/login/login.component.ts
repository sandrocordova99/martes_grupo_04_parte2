import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, RouterOutlet, Router } from '@angular/router';
import { UsuarioService } from '../../../servicio/usuario.service';
import { UserDTO } from '../../../modelos/UserDTO';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  constructor(private usuarioServicio: UsuarioService, private router: Router) { }

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

  mensajeError: string | null = null;

  onLogin(): void {
    this.usuarioServicio.loginUsuario(this.userDTO).subscribe(
      {
        next: (response) => {
          const username = response.usuario; // Accede a 'usuario' desde la respuesta
          if (username) {
            // Guarda en localStorage con la clave 'usuario' para ser consistente
            localStorage.setItem('user', JSON.stringify({ usuario: username }));
            this.router.navigate(['/index']);
          } else {
            console.error('No se pudo obtener el usuario desde la respuesta del servidor.');
          }
        },
        error: (err) => {
          this.mensajeError = err.error.message;
          console.log(this.mensajeError);
        }
      }
    );
  }
  
  logout(): void {
    localStorage.removeItem('user');
    this.router.navigate(['/LoginUsuario']);
  }

}
