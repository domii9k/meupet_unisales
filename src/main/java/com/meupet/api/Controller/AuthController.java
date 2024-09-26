package com.meupet.api.Controller;

import com.meupet.api.Configuration.Security.TokenService;
import com.meupet.api.DTO.AuthDTO;
import com.meupet.api.DTO.LoginResponseDTO;
import com.meupet.api.DTO.UsuarioDTO;
import com.meupet.api.Model.User;
import com.meupet.api.Repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepository repository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/registro")
    public ResponseEntity registro(@RequestBody @Valid UsuarioDTO dto){
        if(this.repository.findByEmail(dto.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.senha());
        User newUser = new User(dto.nome(), dto.sexo(), dto.email(),encryptedPassword, dto.grupo());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
