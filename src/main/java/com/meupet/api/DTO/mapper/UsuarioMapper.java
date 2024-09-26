package com.meupet.api.DTO.mapper;

import com.meupet.api.DTO.UsuarioDTO;
import com.meupet.api.Model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO convertToDto(User user) {
        if (user == null) {
            return null;
        }
        return new UsuarioDTO(
                user.getId(),
                user.getNome(),
                user.getSexo(),
                user.getEmail(),
                user.getSenha(),
                user.getGrupo()
        );
    }

    public User convertToEntity(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        if (dto.id() != null) {
            user.setId(dto.id());
        }
        BeanUtils.copyProperties(dto, user);
        return user;
    }
}
