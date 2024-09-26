package com.meupet.api.DTO.mapper;

import com.meupet.api.DTO.VacinaAnimalDTO;
import com.meupet.api.Model.VacinaAnimal;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class VacinaAnimalMapper {

    private final AnimalMapper animalMapper;
    private final VacinasMapper vacinasMapper;

    public VacinaAnimalMapper(AnimalMapper animalMapper, VacinasMapper vacinasMapper) {
        this.animalMapper = animalMapper;
        this.vacinasMapper = vacinasMapper;
    }

    public VacinaAnimalDTO convertToDto(VacinaAnimal vacinaAnimal) {
        if (vacinaAnimal == null) {
            return null;
        }
        return new VacinaAnimalDTO(
                vacinaAnimal.getId(),
                animalMapper.convertToDto(vacinaAnimal.getIdAnimal()),
                vacinasMapper.convertToDto(vacinaAnimal.getIdVacina()),
                vacinaAnimal.getDataAplicacao(),
                vacinaAnimal.getDataCadastro()
        );
    }

    public VacinaAnimal convertToEntity(VacinaAnimalDTO dto) {
        if (dto == null) {
            return null;
        }
        VacinaAnimal vacinaAnimal = new VacinaAnimal();
        if (dto.id() != null) {
            vacinaAnimal.setId(dto.id());
        }
        BeanUtils.copyProperties(dto, vacinaAnimal);
        vacinaAnimal.setIdAnimal(animalMapper.convertToEntity(dto.idAnimal()));
        vacinaAnimal.setIdVacina(vacinasMapper.convertToEntity(dto.idVacina()));
        return vacinaAnimal;
    }
}
