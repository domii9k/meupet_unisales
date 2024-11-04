package com.meupet.api.Service;

import com.meupet.api.DTO.VacinasDTO;
import com.meupet.api.DTO.mapper.VacinasMapper;
import com.meupet.api.Model.Vacinas;
import com.meupet.api.Repository.VacinasRepository;
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
class VacinasServiceTest {

    @InjectMocks
    private VacinasService vacinasService;

    @Mock
    private VacinasRepository vacinasRepository;

    @Mock
    private VacinasMapper vacinasMapper;

    private Vacinas vacina;
    private VacinasDTO vacinaDto;

    @BeforeEach
    void setUp() {
        vacina = new Vacinas();
        vacina.setId(1L);
        vacina.setNome("Antirrábica");
        vacina.setDescricao("Vacina contra raiva");

        vacinaDto = new VacinasDTO(vacina.getId(), vacina.getNome(), vacina.getDescricao());
    }

    @Test
    @DisplayName("Deve listar todas as vacinas")
    void listAll_DeveRetornarTodasAsVacinas() {
        when(vacinasRepository.findAll()).thenReturn(List.of(vacina));
        when(vacinasMapper.convertToDto(vacina)).thenReturn(vacinaDto);

        List<VacinasDTO> resultado = vacinasService.listAll();

        assertEquals(1, resultado.size());
        assertEquals(vacinaDto, resultado.get(0));
        verify(vacinasRepository, times(1)).findAll();
        verify(vacinasMapper, times(1)).convertToDto(vacina);
    }

    @Test
    @DisplayName("Deve buscar vacina por ID")
    void findById_DeveRetornarVacinaPorId() {
        when(vacinasRepository.findById(1L)).thenReturn(Optional.of(vacina));
        when(vacinasMapper.convertToDto(vacina)).thenReturn(vacinaDto);

        VacinasDTO resultado = vacinasService.findById(1L);

        assertNotNull(resultado);
        assertEquals(vacinaDto, resultado);
        verify(vacinasRepository, times(1)).findById(1L);
        verify(vacinasMapper, times(1)).convertToDto(vacina);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar vacina por ID inexistente")
    void findById_DeveLancarExcecaoQuandoVacinaNaoEncontrada() {
        when(vacinasRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> vacinasService.findById(1L));

        assertEquals("Vacina não encontrada", exception.getMessage());
        verify(vacinasRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve criar uma nova vacina")
    void create_DeveCriarVacina() {
        when(vacinasMapper.convertToEntity(vacinaDto)).thenReturn(vacina);
        when(vacinasRepository.save(vacina)).thenReturn(vacina);
        when(vacinasMapper.convertToDto(vacina)).thenReturn(vacinaDto);

        VacinasDTO resultado = vacinasService.create(vacinaDto);

        assertNotNull(resultado);
        assertEquals(vacinaDto, resultado);
        verify(vacinasMapper, times(1)).convertToEntity(vacinaDto);
        verify(vacinasRepository, times(1)).save(vacina);
        verify(vacinasMapper, times(1)).convertToDto(vacina);
    }

    @Test
    @DisplayName("Deve atualizar uma vacina existente")
    void update_DeveAtualizarVacina() {
        when(vacinasRepository.findById(1L)).thenReturn(Optional.of(vacina));
        when(vacinasRepository.save(vacina)).thenReturn(vacina);
        when(vacinasMapper.convertToDto(vacina)).thenReturn(vacinaDto);

        VacinasDTO resultado = vacinasService.update(1L, vacinaDto);

        assertEquals(vacinaDto, resultado);
        verify(vacinasRepository, times(1)).findById(1L);
        verify(vacinasRepository, times(1)).save(vacina);
        verify(vacinasMapper, times(1)).convertToDto(vacina);
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar vacina inexistente")
    void update_DeveLancarExcecaoQuandoVacinaNaoEncontrada() {
        when(vacinasRepository.findById(1L)).thenReturn(Optional.of(vacina));

        Exception exception = assertThrows(RuntimeException.class, () -> vacinasService.update(1L, vacinaDto));

        assertEquals("Vacina não encontrada", exception.getMessage());
        verify(vacinasRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve deletar uma vacina existente")
    void delete_DeveDeletarVacina() {
        when(vacinasRepository.findById(1L)).thenReturn(Optional.of(vacina));

        vacinasService.delete(1L);

        verify(vacinasRepository, times(1)).findById(1L);
        verify(vacinasRepository, times(1)).delete(vacina);
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar vacina inexistente")
    void delete_DeveLancarExcecaoQuandoVacinaNaoEncontrada() {
        when(vacinasRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> vacinasService.delete(1L));

        assertEquals("Vacina não encontrada", exception.getMessage());
        verify(vacinasRepository, times(1)).findById(1L);
    }
}
