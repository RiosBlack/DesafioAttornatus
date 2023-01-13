package com.attornatus.desafioDouglasRios.controller;

import com.attornatus.desafioDouglasRios.entity.dto.PessoaDTO;
import com.attornatus.desafioDouglasRios.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    PessoaService pessoaService;

    @GetMapping()
    public List<PessoaDTO> buscar() {return pessoaService.buscarTodos();}

    @GetMapping("/buscarCpf/{cpf}")
    public ResponseEntity buscarPessoaCpf(@PathVariable String cpf) {return pessoaService.buscarPessoaCpf(cpf);}

    @PostMapping()
    public ResponseEntity salvar(@RequestBody @Valid PessoaDTO pessoaDTO) {
        return pessoaService.salvar(pessoaDTO);
    }

    @PutMapping()
    public ResponseEntity atualizarPessoa(@RequestBody @Valid PessoaDTO pessoaDTO){
        return pessoaService.atualizar(pessoaDTO);
    }

}
