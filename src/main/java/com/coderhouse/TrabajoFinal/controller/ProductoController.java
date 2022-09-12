package com.coderhouse.TrabajoFinal.controller;

import com.coderhouse.TrabajoFinal.entities.Producto;
import com.coderhouse.TrabajoFinal.service.ProductoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @GetMapping("/obtenerProducto/{id}")
    public ResponseEntity<Producto> getProducto(@PathVariable(value = "id") Long productoId) {
        Producto producto = productoService.obtenerProducto(productoId);
        return ResponseEntity.ok().body(producto);
    }


    @PostMapping("/crearProducto")
    public ResponseEntity<Producto> createProducto(@Valid @RequestBody Producto producto) {
        Producto nuevoProducto = productoService.crearProducto(producto);
        return ResponseEntity.ok().body(nuevoProducto);
    }

    @PutMapping("/actualizarProducto")
    public ResponseEntity<Producto> updateProducto(@Valid @RequestBody Producto producto) {
        Producto productoModificado = productoService.modificarProducto(producto);
        return ResponseEntity.ok().body(productoModificado);
    }

    @DeleteMapping("/borrarProducto/{id}")
    public String deleteProducto(@PathVariable(value = "id") Long productoId) {
       return productoService.borrarProducto(productoId);
    }

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> getAlProductos() {
        List<Producto> productoList = productoService.obtenerProductos();
        return ResponseEntity.ok().body(productoList);
    }

    @PutMapping("/restarStock")
    public ResponseEntity<String> updateStock(@Valid @RequestBody Producto producto) {
        Producto productoModificado = producto;
        String respuesta = productoService.restarStock(productoModificado);
        return ResponseEntity.ok().body(respuesta);
    }
}
