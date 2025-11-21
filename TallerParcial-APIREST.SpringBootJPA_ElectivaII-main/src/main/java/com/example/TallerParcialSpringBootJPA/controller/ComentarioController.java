package com.example.TallerParcialSpringBootJPA.controller;

import com.example.TallerParcialSpringBootJPA.entities.Comentarios;
import com.example.TallerParcialSpringBootJPA.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comentarios")
@CrossOrigin(origins = "*")
public class ComentarioController {
    
    @Autowired
    private ComentarioService comentarioService;
    
    //Listar comentarios desde la fecha ingresada
    @GetMapping("/listar")
    public ResponseEntity<Map<String, Object>> listarComentariosPorFecha(@RequestParam String fechaDesde) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Comentarios> comentarios = comentarioService.findComentariosByFechaDesde(fechaDesde);
            response.put("comentarios", comentarios);
            response.put("mensaje", "Comentarios desde la fecha " + fechaDesde);
            response.put("total", comentarios.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Hubo un error al comentarios: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    //Obtener comentarios
    @GetMapping("/todos")
    public ResponseEntity<Map<String, Object>> listarTodosLosComentarios() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Comentarios> comentarios = comentarioService.findAll();
            response.put("comentarios", comentarios);
            response.put("total", comentarios.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Hubo un error al comentarios: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    //Obtener comentarios de producto
    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<Map<String, Object>> listarComentariosPorProducto(@PathVariable Integer idProducto) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Comentarios> comentarios = comentarioService.findByProductoId(idProducto);
            response.put("comentarios", comentarios);
            response.put("mensaje", "Comentarios del producto ID " + idProducto);
            response.put("total", comentarios.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Hubo un error al obtener comentarios: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    //nuevo comentario
    @PostMapping
    public ResponseEntity<Map<String, Object>> crearComentario(@RequestBody Comentarios comentario) {
        Map<String, Object> response = new HashMap<>();
        try {
            Comentarios nuevoComentario = comentarioService.save(comentario);
            response.put("comentario", nuevoComentario);
            response.put("mensaje", "Comentario creado exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Hubo un error al crear su comentario: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
