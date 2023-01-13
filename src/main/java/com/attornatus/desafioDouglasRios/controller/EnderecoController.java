package com.attornatus.desafioDouglasRios.controller;

import com.attornatus.desafioDouglasRios.entity.dto.EnderecoDTO;
import com.attornatus.desafioDouglasRios.service.EnderecoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    EnderecoServico enderecoServico;

    @GetMapping()
    public List<EnderecoDTO> buscar() {return enderecoServico.buscarTodos();}

    @GetMapping("/buscarCep/{cep}")
    public ResponseEntity buscarCep(@PathVariable String cep) {return enderecoServico.buscarCep(cep);}


}
