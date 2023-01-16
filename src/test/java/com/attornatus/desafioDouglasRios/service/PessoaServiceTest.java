package com.attornatus.desafioDouglasRios.service;


import com.attornatus.desafioDouglasRios.entity.Endereco;
import com.attornatus.desafioDouglasRios.entity.dto.EnderecoDTO;
import com.attornatus.desafioDouglasRios.entity.dto.PessoaDTO;
import com.attornatus.desafioDouglasRios.repository.EnderecoRepository;
import com.attornatus.desafioDouglasRios.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
public class PessoaServiceTest {
    @Autowired
    PessoaService pessoaService;

    @Autowired
    EnderecoServico enderecoServico;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(PessoaServiceTest.class);

    @Test
    void salvar(){
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setLogradouro("rua do teste");
        enderecoDTO.setCep("00.000-000");
        enderecoDTO.setNumero("000");
        enderecoDTO.setCidade("Salvador");
        enderecoDTO.setIsEnderecoPrincipal(true);
        ResponseEntity enderecoSalvo = enderecoServico.salvar(enderecoDTO);
        log.info("Endereço foi salvo com sucesso");
        System.out.println(enderecoSalvo);

        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("Gabriel");
        pessoaDTO.setCpf("858.215.510-72");
        pessoaDTO.setSobrenome("Mota");
        pessoaDTO.setDataNascimento(Timestamp.valueOf("1992-04-10T14:15:46.196+00:00"));
        pessoaDTO.setEndereco((List<Endereco>) enderecoDTO);
        ResponseEntity pessoaSalva = pessoaService.salvar(pessoaDTO);
        log.info("Pessoa salva com sucesso");
        System.out.println(pessoaSalva);
    }

}
