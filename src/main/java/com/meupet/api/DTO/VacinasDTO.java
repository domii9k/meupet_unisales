package com.meupet.api.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record VacinasDTO(
         Long id,
        @NotBlank @Size(max = 150) String nome,
        @NotBlank @Size(max = 500) String descricao
) {}
