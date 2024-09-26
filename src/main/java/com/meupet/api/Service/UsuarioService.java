package com.meupet.api.Service;

import com.meupet.api.DTO.UsuarioDTO;
import com.meupet.api.DTO.mapper.UsuarioMapper;
import com.meupet.api.Repository.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<UsuarioDTO> listAll() {
        return repository.findAll().stream()
                .map(mapper::convertToDto)
                .toList();
    }

    public UsuarioDTO findById(@Positive Long id) {
        return mapper.convertToDto(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado")));
    }

    public UsuarioDTO update(@Positive Long id, @Valid UsuarioDTO dto) {
        return repository.findById(id)
                .map(registroEncontrado -> {
                    BeanUtils.copyProperties(dto, registroEncontrado);
                    return mapper.convertToDto(repository.save(registroEncontrado));
                }).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public void delete(@Positive Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado")));
    }
}
