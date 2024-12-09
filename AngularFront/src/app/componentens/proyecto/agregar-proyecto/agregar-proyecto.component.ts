import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, RouterOutlet, Router } from '@angular/router';
import { ProyectoDTO } from '../../../modelos/ProyectoDTO';
import { UserDTO } from '../../../modelos/UserDTO';
import { ProyectoService } from '../../../servicio/proyecto.service';
import { error } from 'console';

@Component({
  selector: 'app-agregar-proyecto',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterModule, FormsModule],
  templateUrl: './agregar-proyecto.component.html',
  styleUrl: './agregar-proyecto.component.css'
})
export class AgregarProyectoComponent implements OnInit {

  constructor(private proyectoService: ProyectoService, private router: Router) { }
  
  username: string | null = null;

  ngOnInit(): void {
    this.username = localStorage.getItem('user');
  }



  nuevoProyecto: ProyectoDTO = {
    id: 0,
    nombre: '',
    usuarios: [], //se inicializa con el user de turno y es DTO
    tareas: [],
    colorId: 1
  }

  guardar(): void {
    const userLogeado = localStorage.getItem('user');
    if (userLogeado) {
      try {
        const usuario = JSON.parse(userLogeado) as { usuario: string };
        
        if (usuario.usuario) {
          const userDTO: UserDTO = {
            username: usuario.usuario, // Usa el atributo correcto
            password: '',
            isEnabled: false,
            accountNoExpired: false,
            accountNoLocked: false,
            credentialNoExpired: false,
            nombreCliente: '',
            dni: '',
            email: '',
            roles: []
          };
          // Agrega el usuario al proyecto
          this.nuevoProyecto.usuarios.push(userDTO);
          console.log('Usuario agregado al proyecto:', userDTO);
        } else {
          console.error('El username no está disponible en localStorage.');
        }
      } catch (error) {
        console.error('Error al parsear el JSON de localStorage:', error);
      }
    } else {
      console.error('No hay información de usuario en localStorage.');
    }
  
      this.proyectoService.agregarProyecto(this.nuevoProyecto).subscribe(
      () => {
        this.router.navigate(['/listarProyecto']);
        console.log('Usuario:: ', localStorage.getItem('user'));
      },
      error => {
        console.log("El error es: ", error);
      }
    )

  }

  


}
