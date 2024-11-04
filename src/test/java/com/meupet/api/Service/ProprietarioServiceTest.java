package com.meupet.api.Service;

import com.meupet.api.DTO.ProprietarioDTO;
import com.meupet.api.DTO.mapper.ProprietarioMapper;
import com.meupet.api.Model.Proprietario;
import com.meupet.api.Repository.ProprietarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProprietarioServiceTest {

    @InjectMocks
    ProprietarioService proprietarioService;
    @Mock
    ProprietarioMapper proprietarioMapper;
    @Mock
    ProprietarioRepository proprietarioRepository;

    Proprietario proprietario;
    ProprietarioDTO proprietarioDTO;

    @BeforeEach
    void setUp() {
        proprietario = new Proprietario();
        proprietario.setId(1L);
        proprietario.setSexo('F');
        proprietario.setNome("Adele");
        proprietario.setEmail("adele@gmail.com");
        proprietario.setCpf("111.111.111-11");
        proprietario.setCelular("279999-9999");

        proprietarioDTO = new ProprietarioDTO(proprietario.getId(), proprietario.getNome(), proprietario.getSexo(), proprietario.getCpf(), proprietario.getEmail(), proprietario.getCelular());
    }

    @Test
    @DisplayName("Deve retornar uma lista de Proprietarios cadastrados")
    void listAll_DeveRetornarUmaListaDeProprietarios() {
        when(proprietarioRepository.findAll()).thenReturn(List.of(proprietario));
        when(proprietarioMapper.convertToDto(proprietario)).thenReturn(proprietarioDTO);

        List<ProprietarioDTO> resultado = proprietarioService.listAll();

        assertEquals(1, resultado.size());
        assertEquals(proprietarioDTO, resultado.get(0));
        verify(proprietarioRepository, times(1)).findAll();
        verify(proprietarioMapper, times(1)).convertToDto(proprietario);
        verifyNoMoreInteractions(proprietarioRepository);
    }

    @Test
    @DisplayName("Deve retornar um proprietario pelo ID")
    void findById_DeveRetornarUmProprietarioPeloID() {
        when(proprietarioRepository.findById(1L)).thenReturn(Optional.of(proprietario));
        when(proprietarioMapper.convertToDto(proprietario)).thenReturn(proprietarioDTO);

        ProprietarioDTO resultado = proprietarioService.findById(1L);

        assertEquals(proprietarioDTO, resultado);
        verify(proprietarioRepository, times(1)).findById(1L);
        verify(proprietarioMapper, times(1)).convertToDto(proprietario);
        verifyNoMoreInteractions(proprietarioRepository);
    }

    @Test
    @DisplayName("Deve criar um novo proprietario")
    void create_DeveCriarUmNovoProprietario() {
        when(proprietarioMapper.convertToEntity(proprietarioDTO)).thenReturn(proprietario);
        when(proprietarioRepository.save(proprietario)).thenReturn(proprietario);
        when(proprietarioMapper.convertToDto(proprietario)).thenReturn(proprietarioDTO);

        ProprietarioDTO resultado = proprietarioService.create(proprietarioDTO);

        assertEquals(proprietarioDTO, resultado);
        verify(proprietarioMapper, times(1)).convertToEntity(proprietarioDTO);
        verify(proprietarioRepository, times(1)).save(proprietario);
        verify(proprietarioMapper, times(1)).convertToDto(proprietario);
        verifyNoMoreInteractions(proprietarioRepository, proprietarioMapper);
    }

    @Test
    @DisplayName("Deve atualizar um proprietario existente")
    void update_DeveAtualizarUmProprietarioExistente() {
        when(proprietarioRepository.findById(1L)).thenReturn(Optional.of(proprietario));
        when(proprietarioRepository.save(proprietario)).thenReturn(proprietario);
        when(proprietarioMapper.convertToDto(proprietario)).thenReturn(proprietarioDTO);

        ProprietarioDTO resultado = proprietarioService.update(1L, proprietarioDTO);

        assertEquals(proprietarioDTO, resultado);
        verify(proprietarioRepository, times(1)).findById(1L);
        verify(proprietarioRepository, times(1)).save(proprietario);
        verify(proprietarioMapper, times(1)).convertToDto(proprietario);
        verifyNoMoreInteractions(proprietarioRepository, proprietarioMapper);
    }

    @Test
    @DisplayName("Deve excluir um proprietario pelo ID")
    void delete_DeveExcluirUmProprietarioPeloID() {
        when(proprietarioRepository.findById(1L)).thenReturn(Optional.of(proprietario));
        doNothing().when(proprietarioRepository).delete(proprietario);

        proprietarioService.delete(1L);

        verify(proprietarioRepository, times(1)).findById(1L);
        verify(proprietarioRepository, times(1)).delete(proprietario);
        verifyNoMoreInteractions(proprietarioRepository);
    }
}