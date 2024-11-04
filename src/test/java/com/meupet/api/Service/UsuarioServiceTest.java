package com.meupet.api.Service;

import com.meupet.api.DTO.UsuarioDTO;
import com.meupet.api.DTO.mapper.UsuarioMapper;
import com.meupet.api.Model.User;
import com.meupet.api.Model.Grupo;
import com.meupet.api.Repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    private User usuario;
    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {
        usuario = new User();
        usuario.setId(1L);
        usuario.setNome("Carlos");
        usuario.setSexo("M");
        usuario.setEmail("carlos@example.com");
        usuario.setSenha("senha123");
        usuario.setGrupo(Grupo.ADMIN);

        usuarioDTO = new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getSexo(), usuario.getEmail(),usuario.getSenha(), usuario.getGrupo());
    }

    @Test
    @DisplayName("Deve retornar uma lista de Usuários cadastrados")
    void listAll_DeveRetornarUmaListaDeUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
        when(usuarioMapper.convertToDto(usuario)).thenReturn(usuarioDTO);

        List<UsuarioDTO> resultado = usuarioService.listAll();

        assertEquals(1, resultado.size());
        assertEquals(usuarioDTO, resultado.get(0));
        verify(usuarioRepository, times(1)).findAll();
        verify(usuarioMapper, times(1)).convertToDto(usuario);
        verifyNoMoreInteractions(usuarioRepository);
    }

    @Test
    @DisplayName("Deve retornar um Usuário pelo ID")
    void findById_DeveRetornarUmUsuarioPeloID() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioMapper.convertToDto(usuario)).thenReturn(usuarioDTO);

        UsuarioDTO resultado = usuarioService.findById(1L);

        assertEquals(usuarioDTO, resultado);
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioMapper, times(1)).convertToDto(usuario);
        verifyNoMoreInteractions(usuarioRepository);
    }

    @Test
    @DisplayName("Deve lançar exceção se o Usuário não for encontrado")
    void findById_DeveLancarExcecaoSeUsuarioNaoEncontrado() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> usuarioService.findById(1L));
        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(usuarioRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(usuarioRepository);
    }

    @Test
    @DisplayName("Deve atualizar um Usuário existente")
    void update_DeveAtualizarUmUsuarioExistente() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioMapper.convertToDto(usuario)).thenReturn(usuarioDTO);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        UsuarioDTO resultado = usuarioService.update(1L, usuarioDTO);

        assertEquals(usuarioDTO, resultado);
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).save(usuario);
        verify(usuarioMapper, times(1)).convertToDto(usuario);
        verifyNoMoreInteractions(usuarioRepository);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atualizar um Usuário inexistente")
    void update_DeveLancarExcecaoSeUsuarioNaoEncontrado() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> usuarioService.update(1L, usuarioDTO));
        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(usuarioRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(usuarioRepository);
    }

    @Test
    @DisplayName("Deve deletar um Usuário existente")
    void delete_DeveDeletarUmUsuarioExistente() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        usuarioService.delete(1L);

        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).delete(usuario);
        verifyNoMoreInteractions(usuarioRepository);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar um Usuário inexistente")
    void delete_DeveLancarExcecaoSeUsuarioNaoEncontrado() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> usuarioService.delete(1L));
        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(usuarioRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(usuarioRepository);
    }
}
