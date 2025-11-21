package com.example.TallerParcialSpringBootJPA.controller;

import com.example.TallerParcialSpringBootJPA.entities.Producto;
import com.example.TallerParcialSpringBootJPA.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;
    
    //Listar productos con stock menor
    @GetMapping("/listar")
    public ResponseEntity<Map<String, Object>> listarProductosPorStock(@RequestParam Integer stockMaximo) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Producto> productos = productoService.findProductosByStockMenorQue(stockMaximo);
            response.put("productos", productos);
            response.put("mensaje", "Productos con stock menor a " + stockMaximo + " unidades");
            response.put("total", productos.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error al obtener productos: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    //Obtener todos los productos
    @GetMapping("/todos")
    public ResponseEntity<Map<String, Object>> listarTodosLosProductos() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Producto> productos = productoService.findAll();
            response.put("productos", productos);
            response.put("total", productos.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error al obtener todos los productos: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    //productos por Id
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerProductoPorId(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Producto producto = productoService.findById(id)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            response.put("producto", producto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Hubo un error al obtener su producto: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    //Crear productos
    @PostMapping
    public ResponseEntity<Map<String, Object>> crearProducto(@RequestBody Producto producto) {
        Map<String, Object> response = new HashMap<>();
        try {
            Producto nuevoProducto = productoService.save(producto);
            response.put("producto", nuevoProducto);
            response.put("mensaje", "Producto creado exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Hubo un error al crear su producto: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
