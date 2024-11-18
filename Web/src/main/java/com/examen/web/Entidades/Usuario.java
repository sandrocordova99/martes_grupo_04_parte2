package com.examen.web.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tb_Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String contraseña;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    private String foto;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Tareas> tareas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Etiqueta> etiquetas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Estadisticas> estadisticas;

}
