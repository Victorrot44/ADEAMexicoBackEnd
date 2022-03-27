/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adea.prueba.user;

import com.adea.prueba.services.encryptionBase64Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.el.MethodNotFoundException;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Victor
 */
@RestController
@RequestMapping("/api")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    
    @Autowired
    public encryptionBase64Service base64Service;

    @GetMapping("/users")
    public ResponseEntity<?> index (){
        ArrayList<UserEntity> users = this.userService.findAll();
        return ResponseEntity.ok(users);
    }
    
    @PostMapping("/users/filter")
    public ResponseEntity<?> filterByDate (@RequestBody FormFechas inputs){
        ArrayList<UserEntity> users = this.userService.findByCratedAtBetween(inputs.getStart(), inputs.getEnd());
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/users/status/{status}")
    public ResponseEntity<?> getByStatus (@PathVariable("status") char status){
        ArrayList<UserEntity> users = this.userService.findByStatus(status);
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/users/count")
    public ResponseEntity<?> countUser(){
        Map<String, Object> response = new HashMap<>();
        Long total = this.userService.count();
        Long activos = this.userService.countByStatus('A');
        Long inactivos = this.userService.countByStatus('B');
        Long revocados = this.userService.countByStatus('R');
        response.put("status", 200);
        response.put("all_users", total);
        response.put("active_users", activos);
        response.put("inactive_users", inactivos);
        response.put("revoked_users", revocados);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/user")
    public ResponseEntity<?> save(@Valid @RequestBody UserEntity user) throws MethodArgumentNotValidException{
        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.CREATED;
        UserEntity record = this.userService.save(user);
        response.put("status", 201);
        response.put("message", "Usuario Creado con Exito.");
        // response.put("user", record);
        return new ResponseEntity<>(response, status);
    }
    
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        if(!this.userService.verifyUserById(id)){
            throw new MethodNotFoundException("El Usuario no Existe.");
        }
        Map<String, Object> response = new HashMap<>();
        Optional<UserEntity> user = this.userService.getUserById(id);
        response.put("status", 200);
        response.put("user", user);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(!this.userService.verifyUserById(id)){
            throw new MethodNotFoundException("El Usuario no Existe.");
        }
        
        Map<String, Object> response = new HashMap<>();
        this.userService.removeUser(id);
        response.put("status", 200);
        response.put("message", "Usuario eliminado satisfactoriamente.");
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/user/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id, 
            @Valid @RequestBody FormUser inputs
    ) throws MethodArgumentNotValidException{
        if(!this.userService.verifyUserById(id)){
            throw new MethodNotFoundException("El Usuario no Existe.");
        }
        
        Optional<UserEntity> optional = this.userService.getUserById(id);
        UserEntity user = optional.get();
                
        user.setNombre(inputs.getNombre());
        user.setApellido_paterno(inputs.getApellido_paterno());
        user.setApellido_materno(inputs.getApellido_materno());
        user.setLogin(inputs.getLogin());
        if(! "".equals(inputs.getPassword())){
            user.setPassword(inputs.getPassword());
        }
        user.setEmail(inputs.getEmail());
        user.setCliente(inputs.getCliente());
        user.setArea(inputs.getArea());
        user.setFecha_vigencia(inputs.getFecha_vigencia());
        user.setStatus(inputs.getStatus());
        
        this.userService.save(user);

        Map<String, Object> response = new HashMap<>();
        
        // response.put("user", recod);
        response.put("status", 200);
        response.put("message", "Usuario actualizado satisfactoriamente.");
        return ResponseEntity.ok(response);
    }
}