package com.example.TallerParcialSpringBootJPA.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class CarritoProductoId implements Serializable {
    
    private Integer idCarrito;
    private Integer idProducto;
    

    public CarritoProductoId() {}
    
    public CarritoProductoId(Integer idCarrito, Integer idProducto) {
        this.idCarrito = idCarrito;
        this.idProducto = idProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarritoProductoId that = (CarritoProductoId) o;
        return Objects.equals(idCarrito, that.idCarrito) &&
                Objects.equals(idProducto, that.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCarrito, idProducto);
    }

    public Integer getIdCarrito() {
        return idCarrito;
    }
    
    public void setIdCarrito(Integer idCarrito) {
        this.idCarrito = idCarrito;
    }
    
    public Integer getIdProducto() {
        return idProducto;
    }
    
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
    

}