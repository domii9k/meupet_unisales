package com.meupet.api.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "sexo", nullable = false, length = 1)
    private String sexo;

    @Column(name = "email", unique = true, nullable = false, length = 150)
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "grupo", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)  // Caso ainda não esteja anotado
    private Grupo grupo;

    public User(String nome, String sexo, String email, String senha, Grupo grupo) {
        this.nome = nome;
        this.sexo = sexo;
        this.email = email;
        this.senha = senha;
        this.grupo = grupo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.grupo == Grupo.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_VETERINARIO"), new SimpleGrantedAuthority("ROLE_PROPRIETARIO"));
        } else if (this.grupo == Grupo.VETERINARIO) {
            return List.of(new SimpleGrantedAuthority("ROLE_VETERINARIO"), new SimpleGrantedAuthority("ROLE_PROPRIETARIO"));
        } else if (this.grupo == Grupo.SECRETARIO) {
            return List.of(new SimpleGrantedAuthority("ROLE_SECRETARIO"), new SimpleGrantedAuthority("ROLE_PROPRIETARIO"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_PROPRIETARIO"));
        }
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
