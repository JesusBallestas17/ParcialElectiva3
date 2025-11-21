package com.example.TallerParcialSpringBootJPA.controller;

import com.example.TallerParcialSpringBootJPA.dto.AgregarProductoRequest;
import com.example.TallerParcialSpringBootJPA.entities.CarritoCompras;
import com.example.TallerParcialSpringBootJPA.entities.Producto;
import com.example.TallerParcialSpringBootJPA.security.UserPrincipal;
import com.example.TallerParcialSpringBootJPA.service.CarritoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "*")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    //Creacion de carrito
    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crearCarrito(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        try {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Integer idUsuario = userPrincipal.getId();

            CarritoCompras carrito = carritoService.generarNuevoCarrito(idUsuario);
            response.put("carrito", carrito);
            response.put("mensaje", "Carrito creado exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Hubo un error al crear carrito: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // añadir producto al carrito
    @PostMapping("/{idCarrito}/agregar-producto")
    public ResponseEntity<Map<String, Object>> agregarProducto(
            @PathVariable Integer idCarrito,
            @RequestBody AgregarProductoRequest request,
            Authentication authentication) {

        Map<String, Object> response = new HashMap<>();
        try {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Integer idUsuario = userPrincipal.getId();
            boolean agregado = carritoService.incluirProductoEnCarrito(
                    idCarrito,
                    request.getIdProducto(),
                    request.getCantidad(),
                    idUsuario
            );
            if (agregado) {
                response.put("mensaje", "Producto agregado al carrito exitosamente");
                response.put("success", true);
                return ResponseEntity.ok(response);
            } else {
                response.put("error", "No se pudo agregar el producto al carrito. Verifique su cantidad disponible.");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("error", "Hubo un error al agregar producto: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Listamos productos del carrito
    @GetMapping("/{idCarrito}/productos")
    public ResponseEntity<Map<String, Object>> listarProductosDelCarrito(
            @PathVariable Integer idCarrito,
            Authentication authentication) {

        Map<String, Object> response = new HashMap<>();
        try {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Integer idUsuario = userPrincipal.getId();

            // verificamos si el carrito es del cliente
            if (!carritoService.verificarPropiedadCarrito(idCarrito, idUsuario)) {
                response.put("error", "No tiene permisos para acceder a este carrito");
                return ResponseEntity.status(403).body(response);
            }

            List<Producto> productos = carritoService.obtenerProductosDelCarrito(idCarrito, idUsuario);
            response.put("productos", productos);
            response.put("idCarrito", idCarrito);
            response.put("total", productos.size());
            response.put("mensaje", "Productos del carrito " + idCarrito);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Hubo un error al obtener productos del carrito: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Listar carrito del cliente
    @GetMapping("/mis-carritos")
    public ResponseEntity<Map<String, Object>> listarMisCarritos(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        try {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Integer idUsuario = userPrincipal.getId();

            List<CarritoCompras> carritos = carritoService.obtenerPorUsuario(idUsuario);
            response.put("carritos", carritos);
            response.put("total", carritos.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error al obtener carritos: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}