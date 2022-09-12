package com.coderhouse.TrabajoFinal.service;

import com.coderhouse.TrabajoFinal.entities.Empresa;

import java.util.List;

public interface EmpresaService {

    Empresa crearEmpresa(Empresa empresa);

    Empresa modificarEmpresa(Empresa empresa);

    String borrarEmpresa(Long id);

    Empresa obtenerEmpresa(Long id);

    List<Empresa> obtenerEmpresas();
}
