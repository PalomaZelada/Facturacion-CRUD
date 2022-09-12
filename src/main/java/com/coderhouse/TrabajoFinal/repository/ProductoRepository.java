package com.coderhouse.TrabajoFinal.repository;

import com.coderhouse.TrabajoFinal.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}