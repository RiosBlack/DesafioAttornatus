package com.attornatus.desafioDouglasRios.service;

import com.attornatus.desafioDouglasRios.entity.Endereco;
import com.attornatus.desafioDouglasRios.entity.dto.EnderecoDTO;
import com.attornatus.desafioDouglasRios.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoServico {

    @Autowired
    EnderecoRepository enderecoRepository;

    ObjectMapper mapper = new ObjectMapper();

    public List<EnderecoDTO> buscarTodos() {
        List<Endereco> listaEndereco = enderecoRepository.findAll();
        List<EnderecoDTO> listaEnderecoDTO = new ArrayList<>();
        for (Endereco endereco : listaEndereco){
            EnderecoDTO enderecoDTO = mapper.convertValue(endereco, EnderecoDTO.class);
            listaEnderecoDTO.add(enderecoDTO);
        }
        return listaEnderecoDTO;
    }


    public ResponseEntity buscarCep(String cep) {
        List<Endereco> listaEnderecoCep = enderecoRepository.findByCep(cep);
        List<EnderecoDTO> listaEnderecoDTO = new ArrayList<>();
        if (listaEnderecoCep.isEmpty()){
            return new ResponseEntity("Endereço não encontrado", HttpStatus.BAD_REQUEST);
        }
        for (Endereco endereco : listaEnderecoCep){
            EnderecoDTO enderecoDTO = mapper.convertValue(endereco, EnderecoDTO.class);
            listaEnderecoDTO.add(enderecoDTO);
        }
        return new ResponseEntity(listaEnderecoDTO, HttpStatus.OK);
    }

    
}
