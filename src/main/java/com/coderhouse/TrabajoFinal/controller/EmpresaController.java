package com.coderhouse.TrabajoFinal.controller;

import com.coderhouse.TrabajoFinal.entities.Empresa;
import com.coderhouse.TrabajoFinal.service.EmpresaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @GetMapping("/obtenerEmpresa/{id}")
    public ResponseEntity<Empresa> getEmpresa(@PathVariable(value = "id") Long empresaId) {
        Empresa empresa = empresaService.obtenerEmpresa(empresaId);
        return ResponseEntity.ok().body(empresa);
    }


    @PostMapping("/crearEmpresa")
    public ResponseEntity<Empresa> createEmpresa(@Valid @RequestBody Empresa empresa) {
        Empresa nuevaEmpresa = empresaService.crearEmpresa(empresa);
        return ResponseEntity.ok().body(nuevaEmpresa);
    }

    @PutMapping("/actualizarEmpresa")
    public ResponseEntity<Empresa> updateEmpresa(@Valid @RequestBody Empresa empresa) {
        Empresa empresaModificada = empresaService.modificarEmpresa(empresa);
        return ResponseEntity.ok().body(empresaModificada);
    }

    @DeleteMapping("/borrarEmpresa/{id}")
    public String deleteEmpresa(@PathVariable(value = "id") Long empresaId) {
       return empresaService.borrarEmpresa(empresaId);
    }

    @GetMapping("/empresas")
    public ResponseEntity<List<Empresa>> getAllEmpresas() {
        List<Empresa> empresaList = empresaService.obtenerEmpresas();
        return ResponseEntity.ok().body(empresaList);
    }
}
