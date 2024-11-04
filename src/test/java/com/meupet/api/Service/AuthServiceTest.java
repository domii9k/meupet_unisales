package com.meupet.api.Service;

import com.meupet.api.Model.Grupo;
import com.meupet.api.Model.User;
import com.meupet.api.Repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UsuarioRepository usuarioRepository;

    private User usuario;

    @BeforeEach
    void setUp() {
        usuario = new User();
        usuario.setId(1L);
        usuario.setNome("Adele");
        usuario.setEmail("adele@gmail.com");
        usuario.setSenha("senha123");
        usuario.setSexo("F");
        usuario.setGrupo(Grupo.SECRETARIO);
    }

    @Test
    @DisplayName("Deve carregar usuário pelo email com sucesso")
    void loadUserByUsername_DeveCarregarUsuarioComSucesso() {
        when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(usuario);

        UserDetails resultado = authService.loadUserByUsername(usuario.getEmail());

        assertNotNull(resultado);
        assertEquals(usuario.getEmail(), resultado.getUsername());
        verify(usuarioRepository, times(1)).findByEmail(usuario.getEmail());
    }
}
