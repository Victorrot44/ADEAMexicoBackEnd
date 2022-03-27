/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adea.prueba.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Victor
 */
public interface UserRepository extends CrudRepository<UserEntity, Long>{
    
    public boolean existsByLogin(String login);
    
    Optional<UserEntity> findByLogin(String nombre);
    
    ArrayList<UserEntity> findByStatus(char status);
    
    long countByStatus(char status);
    
    @Transactional
    @Modifying()
    @Query(value = "SELECT * FROM usuario u WHERE u.fecha_alta BETWEEN :inicio AND :fin", nativeQuery = true)
    ArrayList<UserEntity> findByFechaAltaBetween(Date inicio, Date fin);
    
    @Transactional
    @Modifying()
    @Query(value = "UPDATE usuario u SET u.intentos = :intentos WHERE u.id = :id", nativeQuery = true)
    void updateIntentosByLogin(@Param("id") Long id, @Param("intentos") double intentos);
}
