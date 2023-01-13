package com.attornatus.desafioDouglasRios.entity.dto;

import com.attornatus.desafioDouglasRios.entity.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PessoaDTO {

    private String nome;

    private String sobrenome;

    private String cpf;

    private Timestamp dataNascimento;

    private Endereco endereco;
}
