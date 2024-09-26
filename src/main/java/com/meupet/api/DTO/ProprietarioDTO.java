package com.meupet.api.DTO;

import jakarta.validation.constraints.*;

public record ProprietarioDTO(
         @Positive Long id,
        @NotBlank @Size(max = 150) String nome,
        @NotNull char sexo,
        @NotBlank @Size(max = 14) String cpf,
        @NotBlank @Email @Size(max = 150) String email,
        @NotBlank String celular
) {}
