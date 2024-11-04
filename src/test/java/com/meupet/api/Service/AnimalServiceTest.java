package com.meupet.api.Service;

import com.meupet.api.DTO.AnimalDTO;
import com.meupet.api.DTO.mapper.AnimalMapper;
import com.meupet.api.Model.Animal;
import com.meupet.api.Model.Proprietario;
import com.meupet.api.Repository.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnimalServiceTest {

    @InjectMocks
    AnimalService animalService;
    @Mock
    AnimalRepository animalRepository;
    @Mock
    AnimalMapper animalMapper;

    AnimalDTO animalDTO;
    Animal animal;
    Proprietario proprietario;

    @BeforeEach
    void setUp() {
        proprietario = new Proprietario();
        proprietario.setId(1L);
        proprietario.setNome("Nome do Proprietario");
        proprietario.setCelular("9393933");
        proprietario.setCpf("3333333");
        proprietario.setEmail("email@gmail.com");
        proprietario.setSexo('F');

        animal = new Animal();
        animal.setId(1L);
        animal.setNome("Nome do Animal");
        animal.setIdade(2);
        animal.setSexo('M');
        animal.setEspecie("Canino");
        animal.setRaca("Labrador");
        animal.setProprietario(proprietario);

        animalDTO = new AnimalDTO(1L, "Nome do Animal", 2, 'M', "Canino", "Labrador", proprietario);
    }

    @Test
    @DisplayName("Deve retornar uma lista de animais convertidos para DTO")
    void listAll_DeveRetornarUmaListaDeAnimais() {
        when(animalRepository.findAll()).thenReturn(List.of(animal));
        when(animalMapper.convertToDto(animal)).thenReturn(animalDTO);

        List<AnimalDTO> resultado = animalService.listAll();

        assertEquals(1, resultado.size());
        assertEquals(animalDTO, resultado.get(0));
        verify(animalRepository, times(1)).findAll();
        verify(animalMapper, times(1)).convertToDto(animal);
    }

    @Test
    @DisplayName("Deve retornar um animal pelo ID")
    void findById_DeveRetornarUmAnimalPeloID() {

        when(animalRepository.findById(1L)).thenReturn(Optional.of(animal));
        when(animalMapper.convertToDto(animal)).thenReturn(animalDTO);

        AnimalDTO resultado = animalService.findById(1L);

        assertEquals(animalDTO, resultado);
        verify(animalMapper, times(1)).convertToDto(animal);
        verify(animalRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve retornar uma excecao ao nao encontrar o id do animal")
    void findById_DeveRetornarUmaExcecao_CasoNaoEncontrado() {

        when(animalRepository.findById(1L)).thenThrow(new RuntimeException("objeto nao encontrado"));

        RuntimeException e = assertThrows(RuntimeException.class, () -> {
           animalService.findById(1L);
        });
        assertEquals("objeto nao encontrado", e.getMessage());
        verify(animalRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(animalRepository);
    }

    @Test
    @DisplayName("Deve retornar um novo animal criado")
    void create_DeveCriarUmNovoAnimal() {
        when(animalMapper.convertToEntity(animalDTO)).thenReturn(animal);
        when(animalRepository.save(animal)).thenReturn(animal);
        when(animalMapper.convertToDto(animal)).thenReturn(animalDTO);

        AnimalDTO resultado = animalService.create(animalDTO);

        assertEquals(animalDTO, resultado);
        verify(animalMapper, times(1)).convertToEntity(animalDTO);
        verify(animalMapper, times(1)).convertToDto(animal);
        verify(animalRepository, times(1)).save(animal);
    }

    @Test
    @DisplayName("Deve retornar um animal existente atualizado")
    void update_DeveAlterarOsDadosDoAnimal() {
        when(animalRepository.findById(1L)).thenReturn(Optional.of(animal));
        when(animalRepository.save(animal)).thenReturn(animal);
        when(animalMapper.convertToDto(animal)).thenReturn(animalDTO);

        AnimalDTO resultado = animalService.update(1L, animalDTO);

        assertEquals(animalDTO, resultado);
        verify(animalRepository, times(1)).findById(1L);
        verify(animalMapper, times(1)).convertToDto(animal);
        verify(animalRepository, times(1)).save(animal);
    }


    @Test
    @DisplayName("Deve deletar um animal")
    void delete_DeveDeletarUmAnimal() {
        when(animalRepository.findById(1L)).thenReturn(Optional.of(animal));

        animalService.delete(1L);

        verify(animalRepository, times(1)).findById(1L);
        verify(animalRepository, times(1)).delete(animal);
    }
}