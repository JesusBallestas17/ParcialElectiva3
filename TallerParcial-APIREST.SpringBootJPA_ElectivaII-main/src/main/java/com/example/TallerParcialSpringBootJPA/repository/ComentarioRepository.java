package com.example.TallerParcialSpringBootJPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.TallerParcialSpringBootJPA.entities.Comentarios;
import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentarios, Integer> {
    
    @Query("SELECT c FROM Comentarios c WHERE c.fecha >= :fecha")
    List<Comentarios> findComentariosByFechaDesde(@Param("fecha") String fecha);
    
    List<Comentarios> findByFechaGreaterThanEqual(String fecha);
    
    List<Comentarios> findByProductoIdProducto(Integer idProducto);
}
