package com.meupet.api.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Persistent;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "historico_pa")
public class HistoricoPesoAltura extends RepresentationModel<HistoricoPesoAltura> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_animal", nullable = false)
    private Animal idAnimal;
    @Column(name = "peso", nullable = false)
    private double peso;
    @Column(name = "altura", nullable = false)
    private double altura;
    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @PrePersist
    protected void onCreate() {
        this.dataCadastro = LocalDate.now();
    }

}
