package com.coderhouse.TrabajoFinal.service;

import com.coderhouse.TrabajoFinal.entities.Producto;

import java.util.List;

public interface ProductoService {

    Producto crearProducto(Producto producto);

    Producto modificarProducto(Producto producto);

    String borrarProducto(Long id);

    Producto obtenerProducto(Long id);

    List<Producto> obtenerProductos();

    String restarStock(Producto producto);

    Producto actualizarStock(Long idProducto, Long stockSolicitado);
}
