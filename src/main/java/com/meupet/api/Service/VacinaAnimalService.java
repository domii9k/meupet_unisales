package com.meupet.api.Service;

import com.meupet.api.DTO.VacinaAnimalDTO;
import com.meupet.api.DTO.mapper.VacinaAnimalMapper;
import com.meupet.api.Model.VacinaAnimal;
import com.meupet.api.Repository.VacinaAnimalRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public class VacinaAnimalService {

    private final VacinaAnimalRepository repository;
    private final VacinaAnimalMapper mapper;

    public VacinaAnimalService(VacinaAnimalRepository repository, VacinaAnimalMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<VacinaAnimalDTO> listAll() {
        return repository.findAll().stream()
                .map(mapper::convertToDto)
                .toList();
    }

    public VacinaAnimalDTO findById(@Positive Long id) {
        return mapper.convertToDto(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("VacinaAnimal não encontrada")));
    }

    public VacinaAnimalDTO create(@Valid VacinaAnimalDTO dto) {
        VacinaAnimal entity = mapper.convertToEntity(dto);
        return mapper.convertToDto(repository.save(entity));
    }

    public VacinaAnimalDTO update(@Positive Long id, @Valid VacinaAnimalDTO dto) {
        return repository.findById(id)
                .map(registroEncontrado -> {
                    BeanUtils.copyProperties(dto, registroEncontrado);
                    return mapper.convertToDto(repository.save(registroEncontrado));
                }).orElseThrow(() -> new RuntimeException("VacinaAnimal não encontrada"));
    }

    public void delete(@Positive Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("VacinaAnimal não encontrada")));
    }
}
