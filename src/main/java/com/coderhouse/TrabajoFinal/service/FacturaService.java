package com.coderhouse.TrabajoFinal.service;

import com.coderhouse.TrabajoFinal.entities.DetalleFactura;
import com.coderhouse.TrabajoFinal.entities.Factura;

import java.util.List;

public interface FacturaService {
    Factura obtenerFactura (Long id);

    Factura crearNuevaFactura (Factura factura);

    List<Factura> obtenerFacturasPorCliente(Long clienteId);

    Long calcularTotalFactura (List<DetalleFactura> detalleFacturas);
}
