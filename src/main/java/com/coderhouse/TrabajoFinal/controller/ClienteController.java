package com.coderhouse.TrabajoFinal.controller;

import com.coderhouse.TrabajoFinal.entities.Cliente;
import com.coderhouse.TrabajoFinal.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping("/obtenerCliente/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable(value = "id") Long clienteId) {
        Cliente cliente = clienteService.obtenerCliente(clienteId);
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clienteList = clienteService.obtenerTodosLosCLientes();
        return ResponseEntity.ok().body(clienteList);
    }

    @PostMapping("/crearCliente")
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.crearCliente(cliente);
        return ResponseEntity.ok().body(nuevoCliente);
    }

    @PutMapping("/actualizarCliente")
    public ResponseEntity<Cliente> updateCliente(@Valid @RequestBody Cliente cliente) {
        Cliente clienteModificado = clienteService.modificarCliente(cliente);
        return ResponseEntity.ok().body(clienteModificado);
    }

    @DeleteMapping("/borrarCliente/{id}")
    public String deleteCliente(@PathVariable(value = "id") Long clienteId) {
       return clienteService.borrarCliente(clienteId);
    }
}
