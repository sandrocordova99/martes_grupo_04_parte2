import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet, RouterModule, Router } from '@angular/router';
import { ProyectoService } from '../../../servicio/proyecto.service';
import { ProyectoDTO } from '../../../modelos/ProyectoDTO';
import { ColorEnum, getColorName } from '../../../modelos/ts';
@Component({
  selector: 'app-listar-proyectos',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterModule, FormsModule],
  templateUrl: './listar-proyectos.component.html',
  styleUrl: './listar-proyectos.component.css'
})
export class ListarProyectosComponent implements OnInit {


  constructor(private proyectoService: ProyectoService, private router: Router) { }

  proyectoDTO?: ProyectoDTO[] = [];
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

  ngOnInit(): void {
    this.obtenerProyectos();
  }



  obtenerProyectos(): void {
    this.proyectoService.listarProyecto().subscribe(
      (data: any) => {
        // Verificamos si el objeto tiene la propiedad 'lista'
        if (data.lista && Array.isArray(data.lista)) {
          this.proyectoDTO = data.lista.map((proyecto: ProyectoDTO) => ({
            ...proyecto,
            usuarios: Array.from(proyecto.usuarios || []),
            tareas: Array.from(proyecto.tareas || []),
            // color: getColorName(proyecto.colorId)
          }));
          console.log('Datos procesados:', this.proyectoDTO);
        } else {
          console.error('Estructura inesperada de datos:', data);
        }
      },
      (error) => {
        console.error('Error al recibir datos:', error);
      }
    );
  }

  crearProyecto(): void {
    console.log("Creando un nuevo Proyecto");
    this.router.navigate(['/agregarProyecto']);
  }







  //final
}


