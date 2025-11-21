package com.example.TallerParcialSpringBootJPA.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrito_productos")
public class CarritoProducto {
    
    @EmbeddedId
    private CarritoProductoId id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idCarrito")
    @JoinColumn(name = "id_carrito")
    @JsonIgnoreProperties({"productos", "carritoProductos"})
    private CarritoCompras carrito;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idProducto")
    @JoinColumn(name = "id_producto")
    @JsonIgnoreProperties({"comentarios", "carritos", "ordenes"})
    private Producto producto;
    
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad = 1;
    public CarritoProducto() {}
    
    public CarritoProducto(CarritoCompras carrito, Producto producto, Integer cantidad) {
        this.id = new CarritoProductoId(carrito.getIdCarrito(), producto.getIdProducto());
        this.carrito = carrito;
        this.producto = producto;
        this.cantidad = cantidad;
    }
    

    public CarritoProductoId getId() {
        return id;
    }
    
    public void setId(CarritoProductoId id) {
        this.id = id;
    }
    
    public CarritoCompras getCarrito() {
        return carrito;
    }
    
    public void setCarrito(CarritoCompras carrito) {
        this.carrito = carrito;
    }
    
    public Producto getProducto() {
        return producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public Integer getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}