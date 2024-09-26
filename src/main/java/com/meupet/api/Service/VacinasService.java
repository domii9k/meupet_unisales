package com.meupet.api.Service;

import com.meupet.api.DTO.VacinasDTO;
import com.meupet.api.DTO.mapper.VacinasMapper;
import com.meupet.api.Model.Vacinas;
import com.meupet.api.Repository.VacinasRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public class VacinasService {

    private final VacinasRepository repository;
    private final VacinasMapper mapper;

    public VacinasService(VacinasRepository repository, VacinasMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<VacinasDTO> listAll() {
        return repository.findAll().stream()
                .map(mapper::convertToDto)
                .toList();
    }

    public VacinasDTO findById(@Positive Long id) {
        return mapper.convertToDto(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacina não encontrada")));
    }

    public VacinasDTO create(@Valid VacinasDTO dto) {
        Vacinas entity = mapper.convertToEntity(dto);
        return mapper.convertToDto(repository.save(entity));
    }

    public VacinasDTO update(@Positive Long id, @Valid VacinasDTO dto) {
        return repository.findById(id)
                .map(registroEncontrado -> {
                    BeanUtils.copyProperties(dto, registroEncontrado);
                    return mapper.convertToDto(repository.save(registroEncontrado));
                }).orElseThrow(() -> new RuntimeException("Vacina não encontrada"));
    }

    public void delete(@Positive Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacina não encontrada")));
    }
}
