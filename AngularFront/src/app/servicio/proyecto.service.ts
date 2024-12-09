import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProyectoDTO } from '../modelos/ProyectoDTO';
import { AgregarTareas } from '../modelos/AgregarTareas';

@Injectable({
  providedIn: 'root'
})
export class ProyectoService {

  constructor(private http : HttpClient) { }

   url = "http://localhost:8082/api/v1/proyecto"

   agregarProyecto(proyectoDTO : ProyectoDTO):Observable<any>{
      return this.http.post<any>(this.url + "/agregar" , proyectoDTO)
   }

   listarProyecto():Observable<any>{
    return this.http.get<any>(this.url + "/listar" )
 }

 agregarTarea(agregarTareas : AgregarTareas):Observable<any>{
  return this.http.post<any>(this.url + "/agregarTarea" , agregarTareas);
}





}
