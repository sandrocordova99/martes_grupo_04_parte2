import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserDTO } from '../modelos/UserDTO';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http:HttpClient) { }

  url = "http://localhost:8082/api/v1/auth"

  loginUsuario(usuario: UserDTO): Observable<any>{
    return this.http.post<any>(this.url + "/login" , usuario);
  }

}
