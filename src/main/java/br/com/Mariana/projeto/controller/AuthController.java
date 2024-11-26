package br.com.Mariana.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Mariana.projeto.dto.AcessDTO;
import br.com.Mariana.projeto.dto.AuthenticationDTO;
import br.com.Mariana.projeto.entity.UsuarioEntity;
import br.com.Mariana.projeto.repository.UsuarioRepository;
import br.com.Mariana.projeto.service.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
@CrossOrigin
public class AuthController {    

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO auth) {

        
        UsuarioEntity user = repository.findByLogin(auth.getUsername());
        
        if (user == null) {
            
            user = new UsuarioEntity();
            user.setLogin(auth.getUsername());
            user.setSenha(new BCryptPasswordEncoder().encode(auth.getPassword()));
            user.setNome(auth.getUsername()); 
            user.setEmail(auth.getUsername()); 
            repository.save(user);
        } else {
           
            boolean passwordMatches = new BCryptPasswordEncoder().matches(auth.getPassword(), user.getSenha());
            if (!passwordMatches) {
                return ResponseEntity.badRequest().body("Credenciais inválidas.");
            }
        }

  
        var token = tokenService.generateToken(user);

        return ResponseEntity.ok(new AcessDTO(token));
    }

}
