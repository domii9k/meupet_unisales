package com.meupet.api.DTO.mapper;

import com.meupet.api.DTO.AnimalDTO;
import com.meupet.api.Model.Animal;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AnimalMapper {

    public AnimalDTO convertToDto(Animal animal) {
        if (animal == null) {
            return null;
        }
        return new AnimalDTO(
                animal.getId(),
                animal.getNome(),
                animal.getIdade(),
                animal.getSexo(),
                animal.getEspecie(),
                animal.getRaca(),
                animal.getProprietario()
        );
    }

    public Animal convertToEntity(AnimalDTO dto) {
        if (dto == null) {
            return null;
        }
        Animal animal = new Animal();
        if (dto.id() != null) {
            animal.setId(dto.id());
        }
        BeanUtils.copyProperties(dto, animal);
        return animal;
    }
}
