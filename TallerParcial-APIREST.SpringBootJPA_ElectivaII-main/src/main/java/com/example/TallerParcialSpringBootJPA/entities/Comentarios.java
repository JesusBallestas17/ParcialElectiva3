package com.example.TallerParcialSpringBootJPA.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comentarios")
public class Comentarios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Integer idComentario;
    
    @Column(name = "comentario", nullable = false, columnDefinition = "TEXT")
    private String comentario;
    
    @Column(name = "fecha", nullable = false, length = 20)
    private String fecha;
    
    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    @JsonIgnoreProperties({"comentarios", "carritos", "ordenes"})
    private Producto producto;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"carritos", "ordenes", "contraseña"})
    private Usuario usuario;
    
    // Constructores
    public Comentarios() {
        this.fecha = "2024-01-01";
    }
    
    public Comentarios(String comentario, Producto producto, Usuario usuario) {
        this.comentario = comentario;
        this.producto = producto;
        this.usuario = usuario;
        this.fecha = "2024-01-01";
    }
    
    public Comentarios(String comentario, Producto producto, Usuario usuario, String fecha) {
        this.comentario = comentario;
        this.producto = producto;
        this.usuario = usuario;
        this.fecha = fecha;
    }
    
    // Getters y Setters
    public Integer getIdComentario() {
        return idComentario;
    }
    
    public void setIdComentario(Integer idComentario) {
        this.idComentario = idComentario;
    }
    
    public String getComentario() {
        return comentario;
    }
    
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public Producto getProducto() {
        return producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
