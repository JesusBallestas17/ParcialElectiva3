package com.example.TallerParcialSpringBootJPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.TallerParcialSpringBootJPA.entities.CarritoCompras;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<CarritoCompras, Integer> {
    
    List<CarritoCompras> findByUsuarioIdUsuario(Integer idUsuario);
    
    Optional<CarritoCompras> findByIdCarritoAndUsuarioIdUsuario(Integer idCarrito, Integer idUsuario);
    
    boolean existsByIdCarritoAndUsuarioIdUsuario(Integer idCarrito, Integer idUsuario);
}