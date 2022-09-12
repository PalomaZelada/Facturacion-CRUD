package com.coderhouse.TrabajoFinal.service;

import com.coderhouse.TrabajoFinal.entities.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Cliente crearCliente(Cliente cliente);

    Cliente modificarCliente(Cliente cliente);

    String borrarCliente(Long id);

    Cliente obtenerCliente(Long id);

    List<Cliente> obtenerTodosLosCLientes();

    Optional<Cliente> obtenerClientePorId (Long id);

}
