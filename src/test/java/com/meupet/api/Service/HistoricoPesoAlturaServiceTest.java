package com.meupet.api.Service;

import com.meupet.api.DTO.AnimalDTO;
import com.meupet.api.DTO.HistoricoPesoAlturaDTO;
import com.meupet.api.DTO.mapper.AnimalMapper;
import com.meupet.api.DTO.mapper.HistoricoPesoAlturaMapper;
import com.meupet.api.Model.Animal;
import com.meupet.api.Model.HistoricoPesoAltura;
import com.meupet.api.Model.Proprietario;
import com.meupet.api.Repository.HistoricoPesoAlturaRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HistoricoPesoAlturaServiceTest {

    @InjectMocks
    HistoricoPesoAlturaService historicoPesoAlturaService;
    @Mock
    HistoricoPesoAlturaMapper historicoPesoAlturaMapper;
    @Mock
    HistoricoPesoAlturaRepository historicoPesoAlturaRepository;
    @Mock
    AnimalMapper animalMapper;

    HistoricoPesoAlturaDTO dto;
    Animal animal;
    HistoricoPesoAltura historicoPesoAltura;
    Proprietario proprietario;

    @BeforeEach
    void setUp() {
        historicoPesoAltura = new HistoricoPesoAltura();
        animal = new Animal();
        LocalDate hoje = LocalDate.now();

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

        historicoPesoAltura.setId(1L);
        historicoPesoAltura.setPeso(12.5);
        historicoPesoAltura.setAltura(1.5);
        historicoPesoAltura.setDataCadastro(hoje);
        historicoPesoAltura.setIdAnimal(animal);

        dto = new HistoricoPesoAlturaDTO(historicoPesoAltura.getId(), animalMapper.convertToDto(animal), historicoPesoAltura.getPeso(), historicoPesoAltura.getAltura(), historicoPesoAltura.getDataCadastro());
    }

    @Test
    @DisplayName("Deve retornar uma lista de historicos de peso e altura de animais")
    void listAll_DeveRetornarUmaListaDeCadastrosExistentes() {
        when(historicoPesoAlturaRepository.findAll()).thenReturn(List.of(historicoPesoAltura));
        when(historicoPesoAlturaMapper.convertToDto(historicoPesoAltura)).thenReturn(dto);

        List<HistoricoPesoAlturaDTO> resultado = historicoPesoAlturaService.listAll();

        assertEquals(dto, resultado.get(0)); // garante que retorna o primeiro objeto
        assertEquals(1, resultado.size()); // garante que retorna somente um objeto
        verify(historicoPesoAlturaMapper, times(1)).convertToDto(historicoPesoAltura);
        verify(historicoPesoAlturaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar um Historico pelo ID")
    void findById_DeveRetornarUmHistoricoPeloID() {
        when(historicoPesoAlturaRepository.findById(1L)).thenReturn(Optional.of(historicoPesoAltura));
        when(historicoPesoAlturaMapper.convertToDto(historicoPesoAltura)).thenReturn(dto);

        HistoricoPesoAlturaDTO resultado = historicoPesoAlturaService.findById(1L);

        assertEquals(dto, resultado);
        verify(historicoPesoAlturaMapper, times(1)).convertToDto(historicoPesoAltura);
        verify(historicoPesoAlturaRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(historicoPesoAlturaRepository);
    }

    @Test //valido também para update e delete: metodos que necessitam buscar um cadastro antes de realizar suas funcoes
    @DisplayName("Deve retornar uma excecao caso nao encontre o Historico")
    void findById_DeveRetornarUmaExcecaoCasoHistoricoNaoEncontrado(){
        when(historicoPesoAlturaRepository.findById(1L)).thenThrow(new RuntimeException("Histórico de Peso/Altura não encontrado"));

        RuntimeException e = assertThrows(RuntimeException.class, () -> {
            historicoPesoAlturaService.findById(1L);
        });
        assertEquals("Histórico de Peso/Altura não encontrado", e.getMessage());
        verify(historicoPesoAlturaRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(historicoPesoAlturaRepository);

    }

    @Test
    @DisplayName("Deve cadastrar um novo item")
    void create_DeveRealizarUmNovoCadastro() {
        when(historicoPesoAlturaMapper.convertToEntity(dto)).thenReturn(historicoPesoAltura); //converte a requisicao para entidade
        when(historicoPesoAlturaRepository.save(historicoPesoAltura)).thenReturn(historicoPesoAltura); // salva a entidade no banco de dados
        when(historicoPesoAlturaMapper.convertToDto(historicoPesoAltura)).thenReturn(dto); // retorna a nova entidade convertida para DTO

        HistoricoPesoAlturaDTO resultado = historicoPesoAlturaService.create(dto);

        assertEquals(dto, resultado);
        verify(historicoPesoAlturaMapper, times(1)).convertToEntity(dto);
        verify(historicoPesoAlturaMapper, times(1)).convertToDto(historicoPesoAltura);
        verify(historicoPesoAlturaRepository, times(1)).save(historicoPesoAltura);
        verifyNoMoreInteractions(historicoPesoAlturaRepository);
    }

    @Test
    @DisplayName("Deve alterar os dados de um Historico existente")
    void update_DeveAlterarOsDadosDeUmHistorico() {
        when(historicoPesoAlturaRepository.findById(1L)).thenReturn(Optional.of(historicoPesoAltura));
        when(historicoPesoAlturaRepository.save(historicoPesoAltura)).thenReturn(historicoPesoAltura);
        when(historicoPesoAlturaMapper.convertToDto(historicoPesoAltura)).thenReturn(dto);

        HistoricoPesoAlturaDTO resultado = historicoPesoAlturaService.update(1L, dto);

        assertEquals(dto, resultado);
        verify(historicoPesoAlturaMapper, times(1)).convertToDto(historicoPesoAltura);
        verify(historicoPesoAlturaRepository, times(1)).findById(1L);
        verify(historicoPesoAlturaRepository, times(1)).save(historicoPesoAltura);
        verifyNoMoreInteractions(historicoPesoAlturaRepository);
    }

    @Test
    @DisplayName("Deve deletar um Historico existente")
    void delete_DeveDeletarUmHistorico() {
        when(historicoPesoAlturaRepository.findById(1L)).thenReturn(Optional.of(historicoPesoAltura));

        historicoPesoAlturaService.delete(1L);

        verify(historicoPesoAlturaRepository, times(1)).findById(1L);
        verify(historicoPesoAlturaRepository, times(1)).delete(historicoPesoAltura);
        verifyNoMoreInteractions(historicoPesoAlturaRepository);
    }
}