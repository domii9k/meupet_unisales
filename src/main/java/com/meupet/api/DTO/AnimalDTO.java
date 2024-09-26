package com.meupet.api.DTO;

import com.meupet.api.Model.Proprietario;
import jakarta.validation.constraints.*;

public record AnimalDTO(
        @Positive Long id,
        @NotBlank String nome,
        @NotNull Integer idade,
        @NotNull char sexo,
        @NotBlank String especie,
        @NotBlank String raca,
        @NotNull Proprietario proprietario
        ) {}
