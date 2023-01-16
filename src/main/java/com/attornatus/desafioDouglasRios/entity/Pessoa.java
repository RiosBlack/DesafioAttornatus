package com.attornatus.desafioDouglasRios.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Campo não informado")
    private String nome;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Campo não informado")
    private String sobrenome;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Campo não informado")
    @CPF(message = "CPF inválido")
    private String cpf;

    @Column(nullable = false)
    private Timestamp dataNascimento;

    @OneToMany (cascade = CascadeType.ALL)
    private List<Endereco> endereco;

}
