import { TareaDTO } from "./TareaDTO";
import { UserDTO } from "./UserDTO";

export interface ProyectoDTO{

     id: number;
     nombre: string;
     usuarios: UserDTO[];
     tareas: TareaDTO[];
     colorId: number;


}