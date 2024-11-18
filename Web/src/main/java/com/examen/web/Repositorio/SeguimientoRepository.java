package com.examen.web.Repositorio;

import com.examen.web.Entidades.Seguimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeguimientoRepository extends JpaRepository<Seguimiento,Long> {
}
