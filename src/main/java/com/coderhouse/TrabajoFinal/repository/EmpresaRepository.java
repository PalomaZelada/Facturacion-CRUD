package com.coderhouse.TrabajoFinal.repository;

import com.coderhouse.TrabajoFinal.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
