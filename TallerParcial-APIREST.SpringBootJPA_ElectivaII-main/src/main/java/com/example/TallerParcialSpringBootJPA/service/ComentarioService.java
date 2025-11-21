package com.example.TallerParcialSpringBootJPA.service;

import com.example.TallerParcialSpringBootJPA.entities.Comentarios;
import com.example.TallerParcialSpringBootJPA.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {
    
    @Autowired
    private ComentarioRepository comentarioRepository;
    
    public List<Comentarios> findAll() {
        return comentarioRepository.findAll();
    }
    
    public Optional<Comentarios> findById(Integer id) {
        return comentarioRepository.findById(id);
    }
    
    public List<Comentarios> findComentariosByFechaDesde(String fecha) {
        return comentarioRepository.findComentariosByFechaDesde(fecha);
    }
    
    public List<Comentarios> findByProductoId(Integer idProducto) {
        return comentarioRepository.findByProductoIdProducto(idProducto);
    }
    
    public Comentarios save(Comentarios comentario) {
        return comentarioRepository.save(comentario);
    }
    
    public Comentarios update(Comentarios comentario) {
        return comentarioRepository.save(comentario);
    }
    
    public void deleteById(Integer id) {
        comentarioRepository.deleteById(id);
    }
}
