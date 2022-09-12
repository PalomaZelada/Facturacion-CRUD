package com.coderhouse.TrabajoFinal.service;

import com.coderhouse.TrabajoFinal.commons.*;
import com.coderhouse.TrabajoFinal.entities.Producto;
import com.coderhouse.TrabajoFinal.repository.ProductoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductoServiceImpl  implements ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    public Producto crearProducto(Producto producto) {
        Producto productoNuevo;
        try {
            productoNuevo = productoRepository.save(producto);
        } catch (Exception e) {
            throw new CreationDatabaseException(producto.toString());
        }

        return productoNuevo;
    }

    public Producto modificarProducto(Producto producto) {
        Producto productoModificado = productoRepository.getById(producto.getId());
        try {
            if (producto.getStock() != null) {
                productoModificado.setStock(producto.getStock());
            }
            if (producto.getNombre() != null) {
                productoModificado.setNombre(producto.getNombre());
            }
            if (producto.getMarca() != null) {
                productoModificado.setMarca(producto.getMarca());
            }
            if (producto.getDetalle() != null) {
                productoModificado.setDetalle(producto.getDetalle());
            }
            if (producto.getPrecioUnitario() != null) {
                productoModificado.setPrecioUnitario(producto.getPrecioUnitario());
            }
        } catch (Exception e) {
            throw new UpdateDatabaseException(" Producto con id" + producto.getId());
        }
        return productoRepository.save(productoModificado);

    }

    public String borrarProducto(Long id) {
        Producto producto = productoRepository.getById(id);

        try {
            productoRepository.deleteById(id);
        } catch (Exception e) {
            throw new DeleteDatabaseException(producto.toString());

        }
        return "Se va a borrar el Producto " + producto.toString();

    }

    public Producto obtenerProducto(Long id) {
        Producto producto;

        try {
            producto = productoRepository.findById(id).get();
        } catch (Exception e) {
            throw new GetDatabaseException("Producto por id  " + id);
        }

        return producto;
    }

    public List<Producto> obtenerProductos() {
        List<Producto> listaProductos;
        try{
            listaProductos = productoRepository.findAll();
        }catch (Exception e){
            throw new GetDatabaseException("todos los productos");
        }
        return listaProductos;
    }

    @Override
    public String restarStock(Producto producto) {
        Producto productoAEditar = productoRepository.getById(producto.getId());
        String respuesta = "";
        if (producto.getStock() != null) {
            Long antiguoStock = Long.valueOf(productoAEditar.getStock());
            log.info(antiguoStock.toString());
            Long stockARestar = Long.valueOf(producto.getStock());
            log.info(stockARestar.toString());
            Long nuevoStock = antiguoStock - stockARestar;

            if (nuevoStock < 0) {
                throw new StockException("No es posible realizar esta venta, ya que el Stock del producto "
                        + productoAEditar.getNombre() + " es de: " + productoAEditar.getStock() + ". La cantidad solicitada fue: "
                        + stockARestar);
            } else if (nuevoStock != antiguoStock) {
                productoAEditar.setStock(nuevoStock.toString());
                productoRepository.save(productoAEditar);
                log.info(productoAEditar.toString());
                respuesta = "El nuevo stock del producto " + productoAEditar.getNombre() + " es: " + nuevoStock;

            }
        }
        return respuesta;
    }

    @Override
    public Producto actualizarStock(Long idProducto, Long stockSolicitado) {
        Producto productoAEditar = productoRepository.getById(idProducto);
        Long stockActual = Long.valueOf(productoAEditar.getStock());
        Long restaStock = stockActual - stockSolicitado;

        if (restaStock < 0) {
            throw new StockException("No es posible realizar esta venta, ya que el Stock del producto "
                    + productoAEditar.getNombre() + " es de " + productoAEditar.getStock() + ". La cantidad solicitada fue "
                    + stockSolicitado);
        } else {
            productoAEditar.setStock(restaStock.toString());
            productoRepository.save(productoAEditar);
        }


        return productoAEditar;
    }

}
