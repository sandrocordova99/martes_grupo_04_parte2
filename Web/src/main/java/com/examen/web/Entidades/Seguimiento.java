package com.examen.web.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "tb_Seguimiento")
public class Seguimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha = LocalDateTime.now();
    private String comentario;

    @Enumerated(EnumType.STRING)
    private Estado estadoActual;

    @ManyToOne
    @JoinColumn(name = "tarea_id", nullable = false)
    private Tareas tarea;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Getters y Setters

    public enum Estado {
        PENDIENTE, EN_PROGRESO, COMPLETADA
    }
}