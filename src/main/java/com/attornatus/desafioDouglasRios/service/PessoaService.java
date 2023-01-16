package com.attornatus.desafioDouglasRios.service;

import com.attornatus.desafioDouglasRios.entity.Endereco;
import com.attornatus.desafioDouglasRios.entity.Pessoa;
import com.attornatus.desafioDouglasRios.entity.dto.PessoaDTO;
import com.attornatus.desafioDouglasRios.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    ObjectMapper mapper = new ObjectMapper();

    public List<PessoaDTO> buscarTodos() {
        List<Pessoa> listaPessoa = pessoaRepository.findAll();
        List<PessoaDTO> listaPessoaDTO = new ArrayList<>();
        for (Pessoa pessoa : listaPessoa){
            PessoaDTO pessoaDTO = mapper.convertValue(pessoa, PessoaDTO.class);
            listaPessoaDTO.add(pessoaDTO);
        }
        return listaPessoaDTO;
    }

    public ResponseEntity buscarPessoaCpf(String cpf) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(cpf);
        if (pessoa.isEmpty()){
            return new ResponseEntity("Cpf não encontrado", HttpStatus.BAD_REQUEST);
        }
        Pessoa pessoaGet = pessoa.get();
        PessoaDTO pessoaDTO = mapper.convertValue(pessoaGet, PessoaDTO.class);
        return new ResponseEntity(pessoaDTO, HttpStatus.OK);
    }

    public ResponseEntity salvar(PessoaDTO pessoaDTO){
        Pessoa pessoa = mapper.convertValue(pessoaDTO, Pessoa.class);
        List<Endereco> enderecoConvertido = new ArrayList<>();
            for (Endereco endereco: pessoaDTO.getEndereco()){
                Endereco enderecoParaConversao = mapper.convertValue(endereco, Endereco.class);
                enderecoConvertido.add(enderecoParaConversao);
            }
            if (pessoa == null) {
                return new ResponseEntity("O objeto pessoa não pode ser null ou vazio", HttpStatus.BAD_REQUEST);
            }
            pessoa.setEndereco(enderecoConvertido);
            Pessoa pessoaSalva = pessoaRepository.save(pessoa);
            return new ResponseEntity("A Pessoa " + pessoaSalva.getNome() + " foi salva com sucesso", HttpStatus.CREATED);

    }


    public ResponseEntity atualizar(PessoaDTO pessoaDTO) {
        Optional<Pessoa> cpf = pessoaRepository.findByCpf(pessoaDTO.getCpf());
        if (cpf.isEmpty()){
            return new ResponseEntity("Pessoa não encontrada", HttpStatus.BAD_REQUEST);
        }
        Pessoa pessoaAtualizada = cpf.get();
        pessoaAtualizada.setNome(pessoaDTO.getNome());
        pessoaAtualizada.setSobrenome(pessoaDTO.getSobrenome());
        pessoaAtualizada.setDataNascimento(pessoaDTO.getDataNascimento());
        pessoaAtualizada.setEndereco(pessoaDTO.getEndereco());

        pessoaRepository.save(pessoaAtualizada);
        return new ResponseEntity("A pessoa " + pessoaAtualizada.getNome() + " foi atualizada com sucesso.", HttpStatus.OK);
    }


}
