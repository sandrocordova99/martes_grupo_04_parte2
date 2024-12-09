
/**   private String nombre;
    private Date fechaVencimiento;
    private String prioridad;
    private Long proyectoId;
    private String estado ; */
export interface TareaRequestDTO{
    fechaVencimiento : Date,
    prioridad : string,
    proyectoId : number,
    estado : string
}