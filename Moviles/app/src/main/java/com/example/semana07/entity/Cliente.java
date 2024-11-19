package com.example.semana07.entity;

public class Cliente {

    //   private int id;
   // private String  descripcion;

    private String dni;
    private String email;
    private String nombre_cliente;

    public Cliente() {
        this.dni = dni;
        this.email = email;
        this.nombre_cliente = nombre_cliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }
    //  private String password;
  //    private String user_name;

}
