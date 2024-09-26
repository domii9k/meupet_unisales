package com.meupet.api.Service;

import com.meupet.api.DTO.ProprietarioDTO;
import com.meupet.api.DTO.mapper.ProprietarioMapper;
import com.meupet.api.Model.Proprietario;
import com.meupet.api.Repository.ProprietarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public class ProprietarioService {

    private final ProprietarioRepository repository;
    private final ProprietarioMapper mapper;

    public ProprietarioService(ProprietarioRepository repository, ProprietarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ProprietarioDTO> listAll() {
        return repository.findAll().stream()
                .map(mapper::convertToDto)
                .toList();
    }

    public ProprietarioDTO findById(@Positive Long id) {
        return mapper.convertToDto(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proprietário não encontrado")));
    }

    public ProprietarioDTO create(@Valid ProprietarioDTO dto) {
        Proprietario entity = mapper.convertToEntity(dto);
        return mapper.convertToDto(repository.save(entity));
    }

    public ProprietarioDTO update(@Positive Long id, @Valid ProprietarioDTO dto) {
        return repository.findById(id)
                .map(registroEncontrado -> {
                    BeanUtils.copyProperties(dto, registroEncontrado);
                    return mapper.convertToDto(repository.save(registroEncontrado));
                }).orElseThrow(() -> new RuntimeException("Proprietário não encontrado"));
    }

    public void delete(@Positive Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proprietário não encontrado")));
    }
}
