package com.example.TallerParcialSpringBootJPA.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGeneratorBCrypt {
    
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // Contraseñas que necesitamos hashear según los datos proporcionados
        String[] passwords = {
            "Qwerty123",    // Juan Pérez
            "Pass456",      // Ana Gómez  
            "Segura789",    // Carlos Ruiz
            "Clave987",     // Sofía Martínez
            "Contra654"     // Diego Fernández
        };
        
        String[] usuarios = {
            "Juan Pérez (juan.perez@email.com)",
            "Ana Gómez (ana.gomez@email.com)",
            "Carlos Ruiz (carlos.ruiz@email.com)",
            "Sofía Martínez (sofia.martinez@email.com)",
            "Diego Fernández (diego.fernandez@email.com)"
        };
        
        System.out.println("=== HASHES BCrypt GENERADOS ===\n");
        
        for (int i = 0; i < passwords.length; i++) {
            String hash = encoder.encode(passwords[i]);
            System.out.println((i + 1) + ". " + usuarios[i]);
            System.out.println("   Contraseña: " + passwords[i]);
            System.out.println("   Hash BCrypt: " + hash);
            System.out.println();
        }
        
        System.out.println("=== PARA USUARIOS.SQL ===\n");
        
        for (int i = 0; i < passwords.length; i++) {
            String hash = encoder.encode(passwords[i]);
            System.out.println("-- Usuario " + (i + 1) + ": " + passwords[i] + " -> " + hash);
        }
    }
}
