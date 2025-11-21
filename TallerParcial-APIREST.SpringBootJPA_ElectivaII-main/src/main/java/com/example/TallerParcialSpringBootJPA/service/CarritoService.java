package com.example.TallerParcialSpringBootJPA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.TallerParcialSpringBootJPA.entities.CarritoCompras;
import com.example.TallerParcialSpringBootJPA.entities.CarritoProducto;
import com.example.TallerParcialSpringBootJPA.entities.Producto;
import com.example.TallerParcialSpringBootJPA.entities.Usuario;
import com.example.TallerParcialSpringBootJPA.repository.CarritoProductoRepository;
import com.example.TallerParcialSpringBootJPA.repository.CarritoRepository;

import java.util.*;

@Service
@Transactional
public class CarritoService {

    @Autowired
    private CarritoRepository repositorioCarrito;

    @Autowired
    private CarritoProductoRepository repositorioCarritoProducto;

    @Autowired
    private ProductoService servicioProductos;

    @Autowired
    private UsuarioService servicioUsuarios;

    public List<CarritoCompras> obtenerTodos() {
        return repositorioCarrito.findAll();
    }

    public Optional<CarritoCompras> obtenerPorId(Integer identificador) {
        return repositorioCarrito.findById(identificador);
    }

    @Transactional(readOnly = true)
    public List<CarritoCompras> obtenerPorUsuario(Integer idUsuario) {
        List<CarritoCompras> resultados = repositorioCarrito.findByUsuarioIdUsuario(idUsuario);
        return resultados;
    }

    public Optional<CarritoCompras> obtenerPorCarritoYUsuario(Integer idCarrito, Integer idUsuario) {
        return repositorioCarrito.findByIdCarritoAndUsuarioIdUsuario(idCarrito, idUsuario);
    }

    public CarritoCompras generarNuevoCarrito(Integer idUsuario) {
        Optional<Usuario> usuario = servicioUsuarios.findById(idUsuario);
        if (usuario.isEmpty()) {
            throw new RuntimeException("El usuario no existe");
        }

        CarritoCompras nuevoCarrito = new CarritoCompras();
        nuevoCarrito.setUsuario(usuario.get());
        nuevoCarrito.setProductos(new ArrayList<>());
        nuevoCarrito.setSubtotal(0.0);
        nuevoCarrito.setImpuestos(0.0);
        return repositorioCarrito.save(nuevoCarrito);
    }

    @Transactional
    public boolean incluirProductoEnCarrito(Integer carritoId, Integer productoId, Integer cantidad, Integer usuarioId) {
        try {
            Optional<CarritoCompras> carrito = repositorioCarrito.findByIdCarritoAndUsuarioIdUsuario(carritoId, usuarioId);
            if (carrito.isEmpty()) {
                return false;
            }

            CarritoCompras carritoActual = carrito.get();
            Optional<Producto> producto = servicioProductos.findById(productoId);

            if (producto.isPresent()) {
                Producto productoEncontrado = producto.get();

                if (productoEncontrado.getStock() < cantidad) {
                    return false;
                }

                Optional<CarritoProducto> productoExistente =
                        repositorioCarritoProducto.findByCarritoIdCarritoAndProductoIdProducto(carritoId, productoId);

                if (productoExistente.isPresent()) {
                    CarritoProducto itemExistente = productoExistente.get();
                    int cantidadTotal = itemExistente.getCantidad() + cantidad;

                    if (productoEncontrado.getStock() >= cantidadTotal - itemExistente.getCantidad()) {
                        itemExistente.setCantidad(cantidadTotal);
                        repositorioCarritoProducto.save(itemExistente);
                        servicioProductos.descontarStock(productoId, cantidad);
                    } else {
                        return false;
                    }
                } else {
                    CarritoProducto nuevoItem = new CarritoProducto(carritoActual, productoEncontrado, cantidad);
                    repositorioCarritoProducto.save(nuevoItem);
                    servicioProductos.descontarStock(productoId, cantidad);
                }

                actualizarValoresTotales(carritoActual);
                repositorioCarrito.save(carritoActual);

                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<Producto> obtenerProductosDelCarrito(Integer carritoId, Integer usuarioId) {
        List<CarritoProducto> items = repositorioCarritoProducto.findByCarritoIdCarritoAndUsuarioId(carritoId, usuarioId);
        List<Producto> productos = new ArrayList<>();

        for (CarritoProducto item : items) {
            for (int i = 0; i < item.getCantidad(); i++) {
                productos.add(item.getProducto());
            }
        }

        return productos;
    }

    private void actualizarValoresTotales(CarritoCompras carrito) {
        double totalParcial = 0.0;

        List<CarritoProducto> items = repositorioCarritoProducto.findByCarritoIdCarrito(carrito.getIdCarrito());

        for (CarritoProducto item : items) {
            totalParcial += item.getProducto().getPrecio() * item.getCantidad();
        }

        carrito.setSubtotal(totalParcial);
        carrito.setImpuestos(totalParcial * 0.19);
    }

    public CarritoCompras guardarCarrito(CarritoCompras carrito) {
        return repositorioCarrito.save(carrito);
    }

    public void eliminarCarrito(Integer identificador) {
        repositorioCarrito.deleteById(identificador);
    }

    public boolean verificarPropiedadCarrito(Integer idCarrito, Integer idUsuario) {
        return repositorioCarrito.existsByIdCarritoAndUsuarioIdUsuario(idCarrito, idUsuario);
    }
}