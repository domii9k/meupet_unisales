package com.meupet.api.Service;

import com.meupet.api.DTO.HistoricoPesoAlturaDTO;
import com.meupet.api.DTO.mapper.HistoricoPesoAlturaMapper;
import com.meupet.api.Model.HistoricoPesoAltura;
import com.meupet.api.Repository.HistoricoPesoAlturaRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public class HistoricoPesoAlturaService {

    private final HistoricoPesoAlturaRepository repository;
    private final HistoricoPesoAlturaMapper mapper;

    public HistoricoPesoAlturaService(HistoricoPesoAlturaRepository repository, HistoricoPesoAlturaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<HistoricoPesoAlturaDTO> listAll() {
        return repository.findAll().stream()
                .map(mapper::convertToDto)
                .toList();
    }

    public HistoricoPesoAlturaDTO findById(@Positive Long id) {
        return mapper.convertToDto(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Histórico de Peso/Altura não encontrado")));
    }

    public HistoricoPesoAlturaDTO create(@Valid HistoricoPesoAlturaDTO dto) {
        HistoricoPesoAltura entity = mapper.convertToEntity(dto);
        return mapper.convertToDto(repository.save(entity));
    }

    public HistoricoPesoAlturaDTO update(@Positive Long id, @Valid HistoricoPesoAlturaDTO dto) {
        return repository.findById(id)
                .map(registroEncontrado -> {
                    BeanUtils.copyProperties(dto, registroEncontrado);
                    return mapper.convertToDto(repository.save(registroEncontrado));
                }).orElseThrow(() -> new RuntimeException("Histórico de Peso/Altura não encontrado"));
    }

    public void delete(@Positive Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Histórico de Peso/Altura não encontrado")));
    }
}
