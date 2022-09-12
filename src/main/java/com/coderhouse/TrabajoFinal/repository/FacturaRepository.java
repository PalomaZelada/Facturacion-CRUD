package com.coderhouse.TrabajoFinal.repository;

import com.coderhouse.TrabajoFinal.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
}


