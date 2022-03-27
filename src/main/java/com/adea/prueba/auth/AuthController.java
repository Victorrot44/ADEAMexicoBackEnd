package com.adea.prueba.auth;

import com.adea.prueba.services.encryptionBase64Service;
import com.adea.prueba.user.UserEntity;
import com.adea.prueba.user.UserService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
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
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    public encryptionBase64Service base64Service;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody FormLogin inputs) {
        Map<String, Object> response = new HashMap<>();
        
        if (!this.userService.verifyUser(inputs.getUsername())) {
            response.put("status", 401);
            response.put("error_title", "Unauthorized");
            response.put("message_error", "El usuario no existe.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        
        Optional<UserEntity> user = this.userService.getUserByUsername(inputs.getUsername());
        
        Date fecha_actual = new Date();
        Date fecha_baja = user.get().getFecha_baja();
        Date fecha_vigencia = user.get().getFecha_vigencia();
        
        if(fecha_baja != null){
            this.userService.intentos(user.get().getId(), (user.get().getIntentos() + 1));
                
            response.put("status", 401);
            response.put("error_title", "Unauthorized");
            response.put("message_error", "El usuario ha sido dado de baja.");
                
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        
        if(fecha_vigencia != null){
            if(!(fecha_vigencia.compareTo(fecha_actual) == 1)){
                this.userService.intentos(user.get().getId(), (user.get().getIntentos() + 1));
                
                response.put("status", 401);
                response.put("error_title", "Unauthorized");
                response.put("message_error", "La cuenta del usuario ha expirado.");
                
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        }
        
        String passwordHash = user.get().getPassword();
        
        if(!inputs.getPassword().equals(base64Service.decode(passwordHash))){
            
            this.userService.intentos(user.get().getId(), (user.get().getIntentos() + 1));
                
            response.put("status", 401);
            response.put("error_title", "Unauthorized");
            response.put("message_error", "La contraseña es erronea.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        
        Map<String, String> userdata = new HashMap<>();
         
        userdata.put("id", String.valueOf(user.get().getId()));
        userdata.put("username", user.get().getLogin());
        userdata.put("nombre", user.get().getNombre());
        userdata.put("apellido_paterno", user.get().getApellido_paterno());
        userdata.put("apellido_materno", user.get().getApellido_materno());
        
        response.put("user", userdata);
         
        return ResponseEntity.ok(response);
    }
}
