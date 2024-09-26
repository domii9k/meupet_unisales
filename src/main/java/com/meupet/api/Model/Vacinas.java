package com.meupet.api.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "vacinas")
public class Vacinas extends RepresentationModel<Vacinas> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false, length = 150)
    private String nome;
    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;
}
