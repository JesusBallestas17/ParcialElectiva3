package com.example.TallerParcialSpringBootJPA.service;

import com.example.TallerParcialSpringBootJPA.entities.Producto;
import com.example.TallerParcialSpringBootJPA.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;
    
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }
    
    public Optional<Producto> findById(Integer id) {
        return productoRepository.findById(id);
    }
    
    public List<Producto> findProductosByStockMenorQue(Integer cantidad) {
        return productoRepository.findProductosByStockMenorQue(cantidad);
    }
    
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }
    
    public Producto update(Producto producto) {
        return productoRepository.save(producto);
    }
    
    public void deleteById(Integer id) {
        productoRepository.deleteById(id);
    }

    public boolean descontarStock(Integer idProducto, Integer cantidad) {
        Optional<Producto> productoOpt = productoRepository.findById(idProducto);
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            if (producto.getStock() >= cantidad) {
                producto.setStock(producto.getStock() - cantidad);
                productoRepository.save(producto);
                return true;
            }
        }
        return false;
    }
    

    public void restaurarStock(Integer idProducto, Integer cantidad) {
        Optional<Producto> productoOpt = productoRepository.findById(idProducto);
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            producto.setStock(producto.getStock() + cantidad);
            productoRepository.save(producto);
        }
    }
}
