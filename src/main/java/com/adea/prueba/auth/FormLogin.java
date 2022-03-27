/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adea.prueba.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Victor
 */
public class FormLogin {
    @NotEmpty(message = "El nombre es obligatorio.")
    private String username;

    @Length(min = 4, message = "La contraseña debe de ser mayor a 3 caracteres")
    @NotBlank(message = "The La contraseña es obligatoria.")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
