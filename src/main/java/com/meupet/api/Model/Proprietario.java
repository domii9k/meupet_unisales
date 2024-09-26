package com.meupet.api.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "proprietario")
public class Proprietario extends RepresentationModel<Proprietario> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;
    @Column(name = "sexo", nullable = false, length = 1)
    private char sexo;
    @Column(name = "cpf", unique = true, nullable = false, length = 14)
    private String cpf;
    @Column(name = "email", unique = true, nullable = false, length = 150)
    private String email;
    @Column(name = "celular", unique = true, length = 15)
    private String celular;
}
