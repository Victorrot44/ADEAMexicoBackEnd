/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adea.prueba.services;

import java.io.IOException;
import net.iharder.Base64;
import org.springframework.stereotype.Service;

/**
 *
 * @author Victor
 */
@Service
public class encryptionBase64Service {
    public String encode(String text){
        String base64 = Base64.encodeBytes(text.getBytes());
        return base64;
    }
    
    public String decode(String base64){
        try {
            byte[] value = Base64.decode(base64);
            return new String(value);
        } catch (IOException e) {
        }
        return null;
    }
}
