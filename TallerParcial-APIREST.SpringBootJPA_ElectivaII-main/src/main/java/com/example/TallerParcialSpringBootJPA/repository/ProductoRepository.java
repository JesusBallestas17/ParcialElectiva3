package com.example.TallerParcialSpringBootJPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.TallerParcialSpringBootJPA.entities.Producto;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    
    @Query("SELECT p FROM Producto p WHERE p.stock < :cantidad")
    List<Producto> findProductosByStockMenorQue(@Param("cantidad") Integer cantidad);
    
    List<Producto> findByStockLessThan(Integer stock);
}
