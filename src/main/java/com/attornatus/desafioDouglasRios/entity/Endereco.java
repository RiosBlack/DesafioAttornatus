package com.attornatus.desafioDouglasRios.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Não pode estar vazio")
    private String logradouro;

    @Column(nullable = false)
    @NotBlank(message = "Não pode estar vazio")
    private String cep;

    @Column(nullable = false)
    @NotBlank(message = "Não pode estar vazio")
    private int numero;

    @Column(nullable = false)
    @NotBlank(message = "Não pode estar vazio")
    private String cidade;

    @Column(nullable = false)
    @NotBlank(message = "Não pode estar vazio")
    private Boolean isEnderecoPrincipal;
}
