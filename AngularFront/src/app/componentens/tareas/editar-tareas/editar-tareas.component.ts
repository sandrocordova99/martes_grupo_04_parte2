import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, RouterOutlet, Router } from '@angular/router';
import { TareaRequestDTO } from '../../../modelos/TareaRequestDTO';
import { TareasService } from '../../../servicio/tareas.service';

@Component({
  selector: 'app-editar-tareas',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterModule, FormsModule],
  templateUrl: './editar-tareas.component.html',
  styleUrl: './editar-tareas.component.css'
})
export class EditarTareasComponent implements OnInit {


  idUsuario: number;
  id = JSON.parse(localStorage.getItem('idTarea') || '0');
  tareasNueva: TareaRequestDTO = {
    nombre: '',
    fechaVencimiento: new Date(),
    prioridad: '',
    proyectoId: 0,
    estado: ''
  };
  mensaje = " ";

  constructor(private tareaService: TareasService, private router: Router) {
    const user = localStorage.getItem("user");
    this.idUsuario = Number(user);

    const proyectoId = localStorage.getItem(this.id);
    this.tareasNueva.proyectoId = Number(proyectoId);
  }
  ngOnInit(): void {
    this.obtenerTarea();
  }

   obtenerTarea(): void {

    this.tareaService.obtenerTareaPorId(this.idUsuario, this.id).subscribe(
      tarea => {
        this.tareasNueva = tarea.tarea;
        this.mensaje = tarea.message;
       
      }, error => {
          console.log("error: ", error)
          this.mensaje = error.error?.message;
      }
    )

  }

  actualizarTarea(): void {
    this.tareaService.actualizarTarea( this.idUsuario , this.id, this.tareasNueva).subscribe(
      data => {
        this.router.navigate(['/listarProyecto']);
        this.mensaje = data.message;
        console.log("Tarea : " , data.tarea);
      },
      error => {
        console.error("Error al actualizar tarea", error);
        this.mensaje = error.erro?.message;
        console.error("Id", this.id);
        console.error("Tarea", this.tareasNueva);
        console.error("User", this.idUsuario);
      }
    );
  }

  



  
  }




//final

