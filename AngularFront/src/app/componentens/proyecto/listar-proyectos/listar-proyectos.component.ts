import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet, RouterModule, Router } from '@angular/router';
import { ProyectoService } from '../../../servicio/proyecto.service';
import { ProyectoDTO } from '../../../modelos/ProyectoDTO';
import { ColorEnum, getColorName } from '../../../modelos/ts';
import { ProyectoResponseDTO } from '../../../modelos/ProyectoResponseDTO';
@Component({
  selector: 'app-listar-proyectos',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterModule, FormsModule],
  templateUrl: './listar-proyectos.component.html',
  styleUrl: './listar-proyectos.component.css'
})
export class ListarProyectosComponent implements OnInit {

  ngOnInit(): void {
    this.obtenerProyectos();
  }

  idUsuario: number ;
 
  proyectos: ProyectoResponseDTO[] = []

  constructor(private proyectoService: ProyectoService, private router: Router) {
    
    const user = localStorage.getItem("user");
    this.idUsuario = Number(user);
   
  }
  ColorEnum = ColorEnum;

  // Mapa de colores CSS
  colorMap: { [key: number]: string } = {
    [ColorEnum.ROJO]: 'red',
    [ColorEnum.VERDE]: 'green',
    [ColorEnum.AMARILLO]: 'yellow',
    [ColorEnum.AZUL]: 'blue',
    [ColorEnum.CELESTE]: 'lightblue',
    [ColorEnum.TURQUESA]: 'turquoise',
  };


  obtenerProyectos(): void {
    if (this.idUsuario > 0) {
      this.proyectoService.listarProyectos(this.idUsuario).subscribe(
        (data) => {
          console.log("Proyectos recibidos: ", data);
          this.proyectos = data.proyecto;
          
        },
        (error) => {
          console.error("Error al obtener proyectos: ", error);
        }
      );
    } else {
      console.error("ID de usuario no válido. No se pueden obtener proyectos.");
    }
  }
}





  //final



