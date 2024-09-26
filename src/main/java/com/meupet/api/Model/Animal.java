package com.meupet.api.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "animal")
public class Animal extends RepresentationModel<Animal> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome", nullable = false, length = 150)
    private String nome;
    @Column(name = "idade", nullable = false)
    private Integer idade;
    @Column(name = "sexo", nullable = false, length = 1)
    private char sexo;
    @Column(name = "especie", nullable = false, length = 50)
    private String especie;
    @Column(name = "raca", nullable = false, length = 50)
    private String raca;
    @ManyToOne
    @JoinColumn(name = "id_proprietario", nullable = false)
    private Proprietario proprietario;

}
