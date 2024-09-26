package com.meupet.api.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record HistoricoPesoAlturaDTO(
        @Positive Long id,
        @NotNull AnimalDTO idAnimal,
        @Positive double peso,
        @Positive double altura,
        @NotNull LocalDate dataCadastro
) {}
