package com.coderhouse.TrabajoFinal.controller;

import com.coderhouse.TrabajoFinal.entities.Factura;
import com.coderhouse.TrabajoFinal.service.FacturaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class FacturaController {
    @Autowired
    FacturaService facturaService;

    @GetMapping("/obtenerFactura/{id}")
    public ResponseEntity<Factura> getFactura(@PathVariable(value = "id") Long id) {

        Factura factura = facturaService.obtenerFactura(id);
        return ResponseEntity.ok().body(factura);
    }

    @GetMapping("/obtenerFacturaPorCliente/{id}")
    public ResponseEntity<List<Factura>> getFacturaByClientId(@PathVariable(value = "id") Long id) {

        List<Factura> facturaPorCliente = facturaService.obtenerFacturasPorCliente(id);
        return ResponseEntity.ok().body(facturaPorCliente);
    }

    @PostMapping("/crearFactura")
    public ResponseEntity<Factura> createFactura(@Valid @RequestBody Factura factura) {

        Factura nuevafactura = facturaService.crearNuevaFactura(factura);
        return ResponseEntity.ok().body(nuevafactura);
    }

}
