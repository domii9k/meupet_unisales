package com.meupet.api.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record VacinaAnimalDTO(
        @Positive Long id,
        @NotNull AnimalDTO idAnimal,
        @NotNull VacinasDTO idVacina,
        @NotNull LocalDate dataAplicacao,
        @NotNull LocalDate dataCadastro
) {}
