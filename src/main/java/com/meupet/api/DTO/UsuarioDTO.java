package com.meupet.api.DTO;

import com.meupet.api.Model.Grupo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(
        Long id,
        @NotBlank @Size(max = 150) String nome,
        @NotBlank @Size(max = 1) String sexo,
        @NotBlank @Email @Size(max = 150) String email,
        @NotBlank @Size(max = 10) String senha,
        @NotNull Grupo grupo
) {}
