/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adea.prueba.user;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import net.iharder.Base64;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Victor
 */
@Entity
@Table(name = "usuario")
public class UserEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "login", nullable = false)
    private String login;
    
    @Column(name = "password", nullable = false)
    @NotEmpty(message = "La contraseña es obligatoria.")
    private String password;
    
    @Column(name = "nombre", nullable = false)
    @NotEmpty(message = "El nombre es obligatoria.")
    private String nombre;
    
    @Column(name = "cliente", nullable = false)
    @NotNull(message = "Numero de cliente es obligatorio.")
    private Float cliente;
    
    @Column(name = "email", nullable = true)
    @Email(message = "Ingrese un email valid.")
    private String email;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "fecha_alta", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_alta;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "fecha_baja", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_baja;
    
    @Column(name = "status")
    private char status = 'A';
    
    @Column(name = "intentos")
    private double intentos = 0;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "fecha_revocado", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date fecha_revocado;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "fecha_vigencia", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date fecha_vigencia;
    
    @Column(name = "no_acceso", nullable = true)
    private Integer no_acceso;
    
    @Column(name = "apellido_paterno", nullable = true)
    private String apellido_paterno;
    
    @Column(name = "apellido_materno", nullable = true)
    private String apellido_materno;
    
    @Column(name = "area", nullable = true)
    private Integer area;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_modificacion;

    public UserEntity(Optional<UserEntity> user) {}
    
    @PrePersist
    private void prePersist(){

        this.fecha_alta = new Date();
        this.fecha_modificacion = new Date();
        this.login = this.generateUsernameLogin(this.nombre);
    }
    
    private String generateUsernameLogin(String nombre){
        String username = "";
        nombre = nombre.trim();
        String[] aux = nombre.split("\\s+");
        if(aux.length > 1){
            username += aux[0].charAt(0) + aux[1];
        } else {
            username += aux[0].charAt(0) + "User"+ String.valueOf((int) ((1000) * Math.random()));
        }
        return username.trim();
    }
    
    @PreUpdate
    public void preUpdate(){
        this.fecha_modificacion = new Date();
    }
    
    public UserEntity(){}
    
    public Long getId(){
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String encript = Base64.encodeBytes(password.getBytes());
        this.password = encript;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getCliente() {
        return cliente;
    }

    public void setCliente(Float cliente) {
        this.cliente = cliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public Date getFecha_baja() {
        return fecha_baja;
    }

    public void setFecha_baja(Date fecha_baja) {
        this.fecha_baja = fecha_baja;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public double getIntentos() {
        return intentos;
    }

    public void setIntentos(double intentos) {
        this.intentos = intentos;
    }

    public Date getFecha_revocado() {
        return fecha_revocado;
    }

    public void setFecha_revocado(Date fecha_revocado) {
        this.fecha_revocado = fecha_revocado;
    }

    public Date getFecha_vigencia() {
        return fecha_vigencia;
    }

    public void setFecha_vigencia(Date fecha_vigencia) {
        this.fecha_vigencia = fecha_vigencia;
    }

    public Integer getNo_acceso() {
        return no_acceso;
    }

    public void setNo_acceso(Integer no_acceso) {
        this.no_acceso = no_acceso;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

}
