/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adea.prueba.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Victor
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public boolean verifyUser(String nombre){
        return this.userRepository.existsByLogin(nombre);
    }
    
    public boolean verifyUserById(Long id){
        return this.userRepository.existsById(id);
    }
    
    public UserEntity save(UserEntity user){
        return this.userRepository.save(user);
    }
    
    public Optional<UserEntity> getUserById(Long id){
        return this.userRepository.findById(id);
    }
    
    public Optional<UserEntity> getUserByUsername(String name){
        return this.userRepository.findByLogin(name);
    }
    
    public ArrayList<UserEntity> findAll(){
        return (ArrayList<UserEntity>) this.userRepository.findAll();
    }
    
    public void removeUser(Long id){
        this.userRepository.deleteById(id);
    }
    
    public void intentos(Long id, double intentos){
        this.userRepository.updateIntentosByLogin(id, intentos);
    }
    
    public ArrayList<UserEntity> findByStatus(char status){
        return this.userRepository.findByStatus(status);
    }
    
    public ArrayList<UserEntity> findByCratedAtBetween(Date start, Date end){
        return this.userRepository.findByFechaAltaBetween(start, end);
    }
    
    public Long countByStatus(char status){
        return this.userRepository.countByStatus(status);
    }
    
    public Long count(){
        return this.userRepository.count();
    }
}
