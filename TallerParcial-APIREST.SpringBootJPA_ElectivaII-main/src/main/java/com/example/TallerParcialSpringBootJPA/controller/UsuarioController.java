package com.example.TallerParcialSpringBootJPA.controller;

import com.example.TallerParcialSpringBootJPA.entities.Usuario;
import com.example.TallerParcialSpringBootJPA.security.UserPrincipal;
import com.example.TallerParcialSpringBootJPA.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    // Obtener perfil del usuario despues de iniciar sesion
    @GetMapping("/perfil")
    public ResponseEntity<Map<String, Object>> obtenerPerfil(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        try {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Usuario usuario = usuarioService.findById(userPrincipal.getId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            usuario.setContrasena(null);
            response.put("usuario", usuario);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error al obtener perfil: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    //Actualizar perfil del usuario
    @PutMapping("/perfil")
    public ResponseEntity<Map<String, Object>> actualizarPerfil(
            @RequestBody Usuario usuarioActualizado,
            Authentication authentication) {
        
        Map<String, Object> response = new HashMap<>();
        try {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Usuario usuarioExistente = usuarioService.findById(userPrincipal.getId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
            usuarioExistente.setDireccion(usuarioActualizado.getDireccion());
            usuarioExistente.setMetodoDePago(usuarioActualizado.getMetodoDePago());
            
            Usuario usuarioGuardado = usuarioService.update(usuarioExistente);
            usuarioGuardado.setContrasena(null);
            
            response.put("usuario", usuarioGuardado);
            response.put("mensaje", "Perfil actualizado exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error al actualizar perfil: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
