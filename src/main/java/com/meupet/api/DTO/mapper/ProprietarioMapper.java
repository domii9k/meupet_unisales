package com.meupet.api.DTO.mapper;

import com.meupet.api.DTO.ProprietarioDTO;
import com.meupet.api.Model.Proprietario;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProprietarioMapper {

    public ProprietarioDTO convertToDto(Proprietario proprietario) {
        if (proprietario == null) {
            return null;
        }
        return new ProprietarioDTO(
                proprietario.getId(),
                proprietario.getNome(),
                proprietario.getSexo(),
                proprietario.getCpf(),
                proprietario.getEmail(),
                proprietario.getCelular()
        );
    }

    public Proprietario convertToEntity(ProprietarioDTO dto) {
        if (dto == null) {
            return null;
        }
        Proprietario proprietario = new Proprietario();
        if (dto.id() != null) {
            proprietario.setId(dto.id());
        }
        BeanUtils.copyProperties(dto, proprietario);
        return proprietario;
    }
}
