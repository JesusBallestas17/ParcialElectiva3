package com.example.TallerParcialSpringBootJPA.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "carritos_compras")
public class CarritoCompras {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito")
    private Integer idCarrito;
    
    @Column(name = "subtotal", nullable = false)
    private Double subtotal;
    
    @Column(name = "impuestos", nullable = false)
    private Double impuestos;
    
    // Relaciones
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"carritos", "comentarios", "contraseña"})
    private Usuario usuario;
    
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CarritoProducto> carritoProductos;
    
    // Mantenemos esta relación para compatibilidad (deprecated)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "carrito_productos",
        joinColumns = @JoinColumn(name = "id_carrito"),
        inverseJoinColumns = @JoinColumn(name = "id_producto")
    )
    @JsonIgnoreProperties({"comentarios", "carritos", "ordenes"})
    private List<Producto> productos;

    public CarritoCompras() {}
    
    public CarritoCompras(Usuario usuario, Double subtotal, Double impuestos) {
        this.usuario = usuario;
        this.subtotal = subtotal;
        this.impuestos = impuestos;
    }

    public Integer getIdCarrito() {
        return idCarrito;
    }
    
    public void setIdCarrito(Integer idCarrito) {
        this.idCarrito = idCarrito;
    }
    
    public Double getSubtotal() {
        return subtotal;
    }
    
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
    
    public Double getImpuestos() {
        return impuestos;
    }
    
    public void setImpuestos(Double impuestos) {
        this.impuestos = impuestos;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public List<Producto> getProductos() {
        return productos;
    }
    
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
    public List<CarritoProducto> getCarritoProductos() {
        return carritoProductos;
    }
    
    public void setCarritoProductos(List<CarritoProducto> carritoProductos) {
        this.carritoProductos = carritoProductos;
    }
}
