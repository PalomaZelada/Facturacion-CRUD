package com.coderhouse.TrabajoFinal.service;

import com.coderhouse.TrabajoFinal.commons.BasicException;
import com.coderhouse.TrabajoFinal.commons.GetDatabaseException;
import com.coderhouse.TrabajoFinal.entities.*;
import com.coderhouse.TrabajoFinal.repository.FacturaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FacturaServiceImpl implements FacturaService {
    @Autowired
    FacturaRepository facturaRepository;
    @Autowired
    ProductoService productoService;
    @Autowired
    ClienteService clienteService;
    @Autowired
    EmpresaService empresaService;
    @Autowired
    DetalleFacturaService detalleFacturaService;

    @Override
    public Factura obtenerFactura(Long id) {

        Factura factura;
        try {

            factura = facturaRepository.findById(id).get();

        } catch (Exception e) {
            throw new GetDatabaseException("Factura por id " + id);
        }

        return factura;
    }

    @Override
    public Factura crearNuevaFactura(Factura factura) {
        Factura nuevaFactura = new Factura();
        Factura facturaCreada = new Factura();
        log.info("Creando nueva factura " + factura);

        if (factura.getDate() != null) {
            nuevaFactura.setDate(factura.getDate());
        }
        if (factura.getTipoFactura() != null) {
            nuevaFactura.setTipoFactura(factura.getTipoFactura());
        }
        if (factura.getTotalFactura() == null) {
            //Calcular total factura
            Long totalFactura = calcularTotalFactura(factura.getDetalleFacturas());
            log.info("Total factura: " + totalFactura);
            nuevaFactura.setTotalFactura(totalFactura);
        }
        if (factura.getCliente().getId() != null) {
            Optional<Cliente> cliente = clienteService.obtenerClientePorId(factura.getCliente().getId());
            if (cliente.isPresent()) {
                log.info("Obteniendo cliente existente");
            } else {
                log.info("Creando cliente nuevo");
                Cliente nuevoCliente = new Cliente();
                nuevoCliente.setNombre("Nuevo");
                nuevoCliente.setApellido("Cliente");
                nuevoCliente.setDni(10000001L);
                nuevoCliente.setDireccion("Direccion Cliente");
                nuevoCliente.setTelefono(999999998L);
                log.info(nuevoCliente.toString());
                cliente = Optional.ofNullable(clienteService.crearCliente(nuevoCliente));
            }
            nuevaFactura.setCliente(cliente.get());
        }

        if (factura.getEmpresa() == null) {
            List<Empresa> empresa = empresaService.obtenerEmpresas();
            log.info("Informacion: " + empresa);
            nuevaFactura.setEmpresa(empresa.get(0));

        }
        facturaCreada = facturaRepository.save(nuevaFactura);
        log.info("Obteniendo Id factura para asociar a Detalle Factura");
        if (factura.getDetalleFacturas() != null) {
            log.info("Creando detalle factura");
            log.info(factura.getDetalleFacturas().toString());
            List<DetalleFactura> detalleFacturaList = new ArrayList<>();
            for (DetalleFactura detalleFactura : factura.getDetalleFacturas()) {
                detalleFactura.setFactura(facturaCreada);
                DetalleFactura nuevoDetalleFactura = detalleFacturaService.crearDetalleFactura(detalleFactura);
                detalleFacturaList.add(nuevoDetalleFactura);

            }

            nuevaFactura.setDetalleFacturas(detalleFacturaList);
        }

        return facturaCreada;
    }

    @Override
    public List<Factura> obtenerFacturasPorCliente(Long clienteId) {
        List<Factura> todasFacturas = facturaRepository.findAll();
        List<Factura> facturaPorCliente = new ArrayList<>();
        try {
            for (Factura factura : todasFacturas) {
                if (factura.getCliente().getId() == clienteId) {

                    facturaPorCliente.add(factura);
                }
            }
        } catch (Exception e) {
            throw new GetDatabaseException("Factura por id cliente " + clienteId);
        }


        return facturaPorCliente;
    }

    @Override
    public Long calcularTotalFactura(List<DetalleFactura> detalleFacturas) {
        Long totalFactura = 0L;
        try {

            for (DetalleFactura detalleFactura : detalleFacturas) {

                Producto datosProducto = productoService.obtenerProducto(detalleFactura.getProducto().getId());
                Long valorUnitario = datosProducto.getPrecioUnitario();
                Long cantidadProducto = detalleFactura.getCantidadProductos();
                log.info("Valor unitario del producto: " + datosProducto.getNombre() + " es " + valorUnitario + " y la cantidad comprada es de: " + cantidadProducto);
                totalFactura += valorUnitario * cantidadProducto;

            }
        } catch (Exception e) {
            throw new BasicException("No es posible calcular el total Factura ");
        }

        return totalFactura;

    }


}
