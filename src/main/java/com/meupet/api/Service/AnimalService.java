package com.meupet.api.Service;

import com.meupet.api.DTO.AnimalDTO;
import com.meupet.api.DTO.mapper.AnimalMapper;
import com.meupet.api.Model.Animal;
import com.meupet.api.Repository.AnimalRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public class AnimalService {

    private final AnimalRepository repository;
    private final AnimalMapper mapper;

    public AnimalService(AnimalRepository repository, AnimalMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<AnimalDTO> listAll() {
        List<Animal> list = repository.findAll();
        return list.stream()
                .map(mapper::convertToDto)
                .toList();
    }

    public AnimalDTO findById(@Positive Long id){return mapper.convertToDto(repository.findById(id).orElseThrow(()->new RuntimeException("objeto nao encontrado")));}

    public AnimalDTO create(@Valid AnimalDTO dto){return mapper.convertToDto(repository.save(mapper.convertToEntity(dto))); }

    public AnimalDTO update(@Positive Long id, @Valid AnimalDTO dto){
        return repository.findById(id)
                .map(registroEncontrado -> {
                    BeanUtils.copyProperties(dto, registroEncontrado);
                    return mapper.convertToDto(repository.save(registroEncontrado));
                }).orElseThrow(() -> new RuntimeException("nao encontrado"));
    }

    public void delete(@Positive Long id){repository.delete(repository.findById(id).orElseThrow(() -> new RuntimeException("nao encontrado")));}
}
