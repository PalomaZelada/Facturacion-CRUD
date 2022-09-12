package com.coderhouse.TrabajoFinal.service;

import com.coderhouse.TrabajoFinal.entities.DetalleFactura;
import com.coderhouse.TrabajoFinal.entities.Producto;
import com.coderhouse.TrabajoFinal.repository.DetalleFacturaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DetalleFacturaServiceImpl implements  DetalleFacturaService{
    @Autowired
    DetalleFacturaRepository detalleFacturaRepository;
    @Autowired
    ProductoService productoService;

    @Override
    public DetalleFactura crearDetalleFactura(DetalleFactura detalleFactura) {

            Long totalParcialPorProducto = calcularTotalParcial(detalleFactura);
            detalleFactura.setProducto(productoService.obtenerProducto(detalleFactura.getProducto().getId()));
            detalleFactura.setTotalParcial(totalParcialPorProducto);
            log.info(detalleFactura.toString());
            detalleFacturaRepository.save(detalleFactura);

        return detalleFactura;
    }

    @Override
    public Long calcularTotalParcial(DetalleFactura detalleFactura) {
        Long totalParcial = 0L;
            Long cantidadProductos = detalleFactura.getCantidadProductos();
            Producto producto = productoService.obtenerProducto(detalleFactura.getProducto().getId());
            productoService.actualizarStock(producto.getId(), cantidadProductos);
            Long precioUnitarioProducto = producto.getPrecioUnitario();
            totalParcial = cantidadProductos * precioUnitarioProducto;


        return totalParcial;
    }

}
