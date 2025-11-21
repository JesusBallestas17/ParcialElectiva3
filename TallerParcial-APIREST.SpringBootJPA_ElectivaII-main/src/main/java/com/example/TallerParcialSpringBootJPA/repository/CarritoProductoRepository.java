package com.example.TallerParcialSpringBootJPA.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.TallerParcialSpringBootJPA.entities.CarritoProducto;
import com.example.TallerParcialSpringBootJPA.entities.CarritoProductoId;

@Repository
public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, CarritoProductoId> {
    
    List<CarritoProducto> findByCarritoIdCarrito(Integer idCarrito);
    
    Optional<CarritoProducto> findByCarritoIdCarritoAndProductoIdProducto(Integer idCarrito, Integer idProducto);
    
    @Query("SELECT cp FROM CarritoProducto cp WHERE cp.carrito.idCarrito = :idCarrito AND cp.carrito.usuario.idUsuario = :idUsuario")
    List<CarritoProducto> findByCarritoIdCarritoAndUsuarioId(@Param("idCarrito") Integer idCarrito, @Param("idUsuario") Integer idUsuario);
}