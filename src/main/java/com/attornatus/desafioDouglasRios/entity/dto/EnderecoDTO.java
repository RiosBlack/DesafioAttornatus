package com.attornatus.desafioDouglasRios.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoDTO {

    private String logradouro;

    private String cep;

    private int numero;

    private String cidade;

    private Boolean isEnderecoPrincipal;
}
