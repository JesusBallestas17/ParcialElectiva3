package com.example.TallerParcialSpringBootJPA.dto;

public class LoginRequest {
    private String correoElectronico;
    private String contrasena;
    
    public LoginRequest() {}
    
    public LoginRequest(String correoElectronico, String contraseña) {
        this.correoElectronico = correoElectronico;
        this.contrasena = contraseña;
    }


    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}