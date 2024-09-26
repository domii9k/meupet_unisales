package com.meupet.api.DTO.mapper;

import com.meupet.api.DTO.VacinasDTO;
import com.meupet.api.Model.Vacinas;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class VacinasMapper {

    public VacinasDTO convertToDto(Vacinas vacinas) {
        if (vacinas == null) {
            return null;
        }
        return new VacinasDTO(
                vacinas.getId(),
                vacinas.getNome(),
                vacinas.getDescricao()
        );
    }

    public Vacinas convertToEntity(VacinasDTO dto) {
        if (dto == null) {
            return null;
        }
        Vacinas vacinas = new Vacinas();
        if (dto.id() != null) {
            vacinas.setId(dto.id());
        }
        BeanUtils.copyProperties(dto, vacinas);
        return vacinas;
    }
}
