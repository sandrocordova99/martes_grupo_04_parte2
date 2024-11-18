package com.examen.web.Repositorio;

import com.examen.web.Entidades.Tareas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareasRepository extends JpaRepository<Tareas,Long> {
}
