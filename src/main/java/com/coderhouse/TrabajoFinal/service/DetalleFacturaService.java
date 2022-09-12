package com.coderhouse.TrabajoFinal.service;

import com.coderhouse.TrabajoFinal.entities.DetalleFactura;

public interface DetalleFacturaService {

    DetalleFactura crearDetalleFactura(DetalleFactura detalleFactura);

    Long calcularTotalParcial(DetalleFactura detalleFactura);

}
