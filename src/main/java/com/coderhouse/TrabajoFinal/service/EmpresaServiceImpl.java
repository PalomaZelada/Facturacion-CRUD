package com.coderhouse.TrabajoFinal.service;

import com.coderhouse.TrabajoFinal.commons.CreationDatabaseException;
import com.coderhouse.TrabajoFinal.commons.DeleteDatabaseException;
import com.coderhouse.TrabajoFinal.commons.UpdateDatabaseException;
import com.coderhouse.TrabajoFinal.entities.Empresa;
import com.coderhouse.TrabajoFinal.repository.EmpresaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmpresaServiceImpl implements  EmpresaService{
@Autowired
EmpresaRepository empresaRepository;
    public Empresa crearEmpresa(Empresa empresa) {
        Empresa empresaNueva;
        try {
            empresaNueva = empresaRepository.save(empresa);
        } catch (Exception e) {
            throw new CreationDatabaseException(empresa.toString());

        }

        return empresaNueva;
    }

    public Empresa modificarEmpresa(Empresa empresa) {
        Empresa empresaModificada = empresaRepository.getById(empresa.getId());
        try {
            if (empresa.getNombre() != null) {
                empresaModificada.setNombre(empresa.getNombre());
            }
            if (empresa.getDireccion() != null) {
                empresaModificada.setDireccion(empresa.getDireccion());
            }
            if (empresa.getRubro() != null) {
                empresaModificada.setRubro(empresa.getRubro());
            }
            if (empresa.getDniEmpresa() != null) {
                empresaModificada.setDniEmpresa(empresa.getDniEmpresa());
            }
            if (empresa.getTelefono() != null) {
                empresaModificada.setTelefono(empresa.getTelefono());
            }
        } catch (Exception e) {
            throw new UpdateDatabaseException("Empresa con id" + empresa.getId());
        }
        return empresaRepository.save(empresaModificada);

    }

    public String borrarEmpresa(Long id) {
        Empresa empresa = empresaRepository.getById(id);
        try {
            empresaRepository.deleteById(id);
        }catch (Exception e){
            throw new DeleteDatabaseException(empresa.toString());
        }
        return "Se va a borrar el Empresa " + empresa.toString();

    }

    public Empresa obtenerEmpresa(Long id) {
        return empresaRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Empresa> obtenerEmpresas() {
        return empresaRepository.findAll();
    }
}
