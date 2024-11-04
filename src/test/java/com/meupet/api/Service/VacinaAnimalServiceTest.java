package com.meupet.api.Service;

import com.meupet.api.DTO.VacinaAnimalDTO;
import com.meupet.api.DTO.mapper.AnimalMapper;
import com.meupet.api.DTO.mapper.VacinaAnimalMapper;
import com.meupet.api.DTO.mapper.VacinasMapper;
import com.meupet.api.Model.Animal;
import com.meupet.api.Model.VacinaAnimal;
import com.meupet.api.Model.Vacinas;
import com.meupet.api.Repository.VacinaAnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VacinaAnimalServiceTest {

    @InjectMocks
    private VacinaAnimalService vacinaAnimalService;
    @Mock
    private VacinaAnimalRepository vacinaAnimalRepository;
    @Mock
    private VacinaAnimalMapper vacinaAnimalMapper;
    @Mock
    AnimalMapper animalMapper;
    @Mock
    VacinasMapper vacinasMapper;

    private VacinaAnimal vacinaAnimal;
    private VacinaAnimalDTO vacinaAnimalDTO;

    @BeforeEach
    void setUp() {
        Animal animal = new Animal();
        animal.setId(1L);

        Vacinas vacina = new Vacinas();
        vacina.setId(1L);

        vacinaAnimal = new VacinaAnimal();
        vacinaAnimal.setId(1L);
        vacinaAnimal.setIdAnimal(animal);
        vacinaAnimal.setIdVacina(vacina);
        vacinaAnimal.setDataAplicacao(LocalDate.now());
        vacinaAnimal.setDataCadastro(LocalDate.now());

        vacinaAnimalDTO = new VacinaAnimalDTO(vacinaAnimal.getId(),animalMapper.convertToDto(animal), vacinasMapper.convertToDto(vacina), vacinaAnimal.getDataAplicacao(), vacinaAnimal.getDataCadastro());
    }

    @Test
    @DisplayName("Deve retornar uma lista de Vacinas aplicadas aos Animais")
    void listAll_DeveRetornarListaDeVacinaAnimal() {
        when(vacinaAnimalRepository.findAll()).thenReturn(List.of(vacinaAnimal));
        when(vacinaAnimalMapper.convertToDto(vacinaAnimal)).thenReturn(vacinaAnimalDTO);

        List<VacinaAnimalDTO> resultado = vacinaAnimalService.listAll();

        assertEquals(1, resultado.size());
        assertEquals(vacinaAnimalDTO, resultado.get(0));
        verify(vacinaAnimalRepository, times(1)).findAll();
        verify(vacinaAnimalMapper, times(1)).convertToDto(vacinaAnimal);
        verifyNoMoreInteractions(vacinaAnimalRepository);
    }

    @Test
    @DisplayName("Deve retornar uma VacinaAnimal pelo ID")
    void findById_DeveRetornarVacinaAnimalPeloID() {
        when(vacinaAnimalRepository.findById(1L)).thenReturn(Optional.of(vacinaAnimal));
        when(vacinaAnimalMapper.convertToDto(vacinaAnimal)).thenReturn(vacinaAnimalDTO);

        VacinaAnimalDTO resultado = vacinaAnimalService.findById(1L);

        assertEquals(vacinaAnimalDTO, resultado);
        verify(vacinaAnimalRepository, times(1)).findById(1L);
        verify(vacinaAnimalMapper, times(1)).convertToDto(vacinaAnimal);
        verifyNoMoreInteractions(vacinaAnimalRepository);
    }

    @Test
    @DisplayName("Deve lançar exceção se a VacinaAnimal não for encontrada")
    void findById_DeveLancarExcecaoSeVacinaAnimalNaoEncontrada() {
        when(vacinaAnimalRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> vacinaAnimalService.findById(1L));
        assertEquals("VacinaAnimal não encontrada", exception.getMessage());
        verify(vacinaAnimalRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(vacinaAnimalRepository);
    }

    @Test
    @DisplayName("Deve criar uma nova VacinaAnimal")
    void create_DeveCriarNovaVacinaAnimal() {
        when(vacinaAnimalMapper.convertToEntity(vacinaAnimalDTO)).thenReturn(vacinaAnimal);
        when(vacinaAnimalRepository.save(vacinaAnimal)).thenReturn(vacinaAnimal);
        when(vacinaAnimalMapper.convertToDto(vacinaAnimal)).thenReturn(vacinaAnimalDTO);

        VacinaAnimalDTO resultado = vacinaAnimalService.create(vacinaAnimalDTO);

        assertEquals(vacinaAnimalDTO, resultado);
        verify(vacinaAnimalMapper, times(1)).convertToEntity(vacinaAnimalDTO);
        verify(vacinaAnimalRepository, times(1)).save(vacinaAnimal);
        verify(vacinaAnimalMapper, times(1)).convertToDto(vacinaAnimal);
        verifyNoMoreInteractions(vacinaAnimalRepository);
    }

    @Test
    @DisplayName("Deve atualizar uma VacinaAnimal existente")
    void update_DeveAtualizarVacinaAnimalExistente() {
        when(vacinaAnimalRepository.findById(1L)).thenReturn(Optional.of(vacinaAnimal));
        when(vacinaAnimalMapper.convertToDto(vacinaAnimal)).thenReturn(vacinaAnimalDTO);
        when(vacinaAnimalRepository.save(vacinaAnimal)).thenReturn(vacinaAnimal);

        VacinaAnimalDTO resultado = vacinaAnimalService.update(1L, vacinaAnimalDTO);

        assertEquals(vacinaAnimalDTO, resultado);
        verify(vacinaAnimalRepository, times(1)).findById(1L);
        verify(vacinaAnimalRepository, times(1)).save(vacinaAnimal);
        verify(vacinaAnimalMapper, times(1)).convertToDto(vacinaAnimal);
        verifyNoMoreInteractions(vacinaAnimalRepository);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atualizar uma VacinaAnimal inexistente")
    void update_DeveLancarExcecaoSeVacinaAnimalNaoEncontrada() {
        when(vacinaAnimalRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> vacinaAnimalService.update(1L, vacinaAnimalDTO));
        assertEquals("VacinaAnimal não encontrada", exception.getMessage());
        verify(vacinaAnimalRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(vacinaAnimalRepository);
    }

    @Test
    @DisplayName("Deve deletar uma VacinaAnimal existente")
    void delete_DeveDeletarVacinaAnimalExistente() {
        when(vacinaAnimalRepository.findById(1L)).thenReturn(Optional.of(vacinaAnimal));

        vacinaAnimalService.delete(1L);

        verify(vacinaAnimalRepository, times(1)).findById(1L);
        verify(vacinaAnimalRepository, times(1)).delete(vacinaAnimal);
        verifyNoMoreInteractions(vacinaAnimalRepository);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar uma VacinaAnimal inexistente")
    void delete_DeveLancarExcecaoSeVacinaAnimalNaoEncontrada() {
        when(vacinaAnimalRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> vacinaAnimalService.delete(1L));
        assertEquals("VacinaAnimal não encontrada", exception.getMessage());
        verify(vacinaAnimalRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(vacinaAnimalRepository);
    }
}
