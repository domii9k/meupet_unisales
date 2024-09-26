package com.meupet.api.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "vacina_animal")
public class VacinaAnimal extends RepresentationModel<VacinaAnimal>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_animal", nullable = false)
    private Animal idAnimal;
    @ManyToOne
    @JoinColumn(name = "id_vacina", nullable = false)
    private Vacinas idVacina;
    @Column(name = "data_aplicacao", nullable = false)
    private LocalDate dataAplicacao;
    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;
}
