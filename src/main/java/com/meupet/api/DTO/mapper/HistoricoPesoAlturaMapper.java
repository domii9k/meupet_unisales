package com.meupet.api.DTO.mapper;

import com.meupet.api.DTO.HistoricoPesoAlturaDTO;
import com.meupet.api.Model.HistoricoPesoAltura;
import com.meupet.api.Model.Animal;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class HistoricoPesoAlturaMapper {

    private final AnimalMapper animalMapper;

    public HistoricoPesoAlturaMapper(AnimalMapper animalMapper) {
        this.animalMapper = animalMapper;
    }

    public HistoricoPesoAlturaDTO convertToDto(HistoricoPesoAltura historico) {
        if (historico == null) {
            return null;
        }
        return new HistoricoPesoAlturaDTO(
                historico.getId(),
                animalMapper.convertToDto(historico.getIdAnimal()),
                historico.getPeso(),
                historico.getAltura(),
                historico.getDataCadastro()
        );
    }

    public HistoricoPesoAltura convertToEntity(HistoricoPesoAlturaDTO dto) {
        if (dto == null) {
            return null;
        }
        HistoricoPesoAltura historico = new HistoricoPesoAltura();
        if (dto.id() != null) {
            historico.setId(dto.id());
        }
        BeanUtils.copyProperties(dto, historico);
        if (dto.idAnimal() != null) {
            Animal animal = animalMapper.convertToEntity(dto.idAnimal());
            historico.setIdAnimal(animal);
        }
        return historico;
    }
}
