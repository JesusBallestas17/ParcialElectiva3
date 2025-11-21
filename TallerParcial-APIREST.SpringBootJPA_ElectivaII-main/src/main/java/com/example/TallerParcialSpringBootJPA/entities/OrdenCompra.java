package com.example.TallerParcialSpringBootJPA.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordenes_compra")
public class OrdenCompra {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden")
    private Integer idOrden;
    
    @Column(name = "subtotal", nullable = false)
    private Double subtotal;
    
    @Column(name = "impuestos", nullable = false)
    private Double impuestos;
    
    @Column(name = "envio", nullable = false)
    private Double envio;
    
    @Column(name = "total", nullable = false)
    private Double total;
    
    // Relaciones
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "orden_productos",
        joinColumns = @JoinColumn(name = "id_orden"),
        inverseJoinColumns = @JoinColumn(name = "id_producto")
    )
    @JsonIgnoreProperties({"comentarios", "carritos", "ordenes"})
    private List<Producto> productos;
    
    // Constructores
    public OrdenCompra() {}
    
    public OrdenCompra(Double subtotal, Double impuestos, Double envio, Double total) {
        this.subtotal = subtotal;
        this.impuestos = impuestos;
        this.envio = envio;
        this.total = total;
    }
    
    // Getters y Setters
    public Integer getIdOrden() {
        return idOrden;
    }
    
    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
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
    
    public Double getEnvio() {
        return envio;
    }
    
    public void setEnvio(Double envio) {
        this.envio = envio;
    }
    
    public Double getTotal() {
        return total;
    }
    
    public void setTotal(Double total) {
        this.total = total;
    }
    
    public List<Producto> getProductos() {
        return productos;
    }
    
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
