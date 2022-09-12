package com.coderhouse.TrabajoFinal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "EMPRESA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {
    @Column(name = "id_Empresa")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    @Pattern(regexp="^[A-Za-z\\s.!#$%&'*+/=?^_`{|}~-]+",message = "El campo nombre debe ser una palabra")
    private String nombre;

    @Column(name = "direccion")
    @Pattern(regexp="^[A-Za-z0-9\\s]+",message = "El campo dirección debe ser un texto alfanumerico")
    @JsonIgnore
    private String direccion;

    @Column(name = "rubro")
    @Pattern(regexp="^[A-Za-z\\s]+",message = "El campo rubro debe ser un texto")
    private String rubro;

    @Column(name = "dni_Empresa")
    @Min(value = 10000000, message ="El dni debe tener un largo minimo de 8")
    @Max(value = 99999999, message = "El dni debe tener un largo maximo de 8")
    @JsonIgnore
    private Long dniEmpresa;

    @Column(name = "telefono")
    @Min(value = 100000000, message ="Telefono debe tener un largo minimo de 9")
    @Max(value = 999999999, message = "Telefono debe tener un largo maximo de 9")
    @JsonIgnore
    private Long telefono;
}

