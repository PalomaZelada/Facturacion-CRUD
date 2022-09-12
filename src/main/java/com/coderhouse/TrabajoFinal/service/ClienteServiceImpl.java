package com.coderhouse.TrabajoFinal.service;


import com.coderhouse.TrabajoFinal.commons.CreationDatabaseException;
import com.coderhouse.TrabajoFinal.commons.DeleteDatabaseException;
import com.coderhouse.TrabajoFinal.commons.GetDatabaseException;
import com.coderhouse.TrabajoFinal.commons.UpdateDatabaseException;
import com.coderhouse.TrabajoFinal.entities.Cliente;
import com.coderhouse.TrabajoFinal.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClienteServiceImpl  implements ClienteService{
    @Autowired
    ClienteRepository clienteRepository;

    public Cliente crearCliente(Cliente cliente) {
        Cliente clienteNuevo;

        try {
            clienteNuevo = clienteRepository.save(cliente);

        } catch (Exception e) {
            throw new CreationDatabaseException(cliente.toString());

        }

        return clienteNuevo;
    }

    public Cliente modificarCliente(Cliente cliente) {
        Cliente clienteModificado = clienteRepository.getById(cliente.getId());
        try {
            if (cliente.getNombre() != null) {
                clienteModificado.setNombre(cliente.getNombre());
            }
            if (cliente.getApellido() != null) {
                clienteModificado.setApellido(cliente.getApellido());
            }
            if (cliente.getDni() != null) {
                clienteModificado.setDni(cliente.getDni());
            }
            if (cliente.getDireccion() != null) {
                clienteModificado.setDireccion(cliente.getDireccion());
            }
            if (cliente.getTelefono() != null) {
                clienteModificado.setTelefono(cliente.getTelefono());
            }
        } catch (Exception e) {
            throw new UpdateDatabaseException(" Cliente con id " + cliente.getId());
        }

        return clienteRepository.save(clienteModificado);

    }

    public String borrarCliente(Long id) {
        Cliente cliente = clienteRepository.getById(id);
        try {
            clienteRepository.deleteById(id);

        } catch (Exception e) {
            throw new DeleteDatabaseException(cliente.toString());
        }
        return "El cliente " + cliente.toString() + " ha sido eliminado";
    }


        public Cliente obtenerCliente (Long id){
        Cliente cliente;
        try{
            cliente = clienteRepository.findById(id).orElseThrow(RuntimeException::new);
        }catch (Exception e){
            throw new GetDatabaseException(" cliente por id" + id);
        }
            return cliente;
        }

        public List<Cliente> obtenerTodosLosCLientes () {
            List<Cliente> listaClientes;
            try {
                listaClientes = clienteRepository.findAll();
            } catch (Exception e) {
                throw new GetDatabaseException(" todos los Clientes");
            }
            return listaClientes;
        }

        @Override
        public Optional<Cliente> obtenerClientePorId (Long id){
            return clienteRepository.findById(id);
        }
    }
