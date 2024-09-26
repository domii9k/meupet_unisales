package com.meupet.api.Model;


import lombok.Getter;

@Getter
public enum Grupo {

    ADMIN("ADMIN"),
    PROPRIETARIO("PROPRIETARIO"),
    VETERINARIO("VETERINARIO"),
    SECRETARIO("SECRETARIO");

    private final String descricao;

    Grupo(String descricao) {
        this.descricao = descricao;
    }

}
