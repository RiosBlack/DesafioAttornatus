package com.attornatus.desafioDouglasRios.service;

import com.attornatus.desafioDouglasRios.entity.Endereco;
import com.attornatus.desafioDouglasRios.entity.Pessoa;
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


    public ResponseEntity salvar(EnderecoDTO enderecoDTO) {
        Endereco endereco = mapper.convertValue(enderecoDTO, Endereco.class);
        try {
            if (endereco == null) {
                return new ResponseEntity("O endereço não pode ser nulo ou vazio", HttpStatus.BAD_REQUEST);
            }
            Endereco enderecoSalvo = enderecoRepository.save(endereco);
            return new ResponseEntity("O endereço "  + enderecoSalvo.getLogradouro() + " foi salvo com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("Erro ao salvar a endereço.", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity atualizar(EnderecoDTO enderecoDTO) {
        List<Endereco> cep = enderecoRepository.findByCep(enderecoDTO.getCep());
        if (cep.isEmpty()){
            return new ResponseEntity("Endereço não encontrado", HttpStatus.BAD_REQUEST);
        }
        for (Endereco endereco : cep) {
            if (endereco.getCep() == enderecoDTO.getCep() && endereco.getNumero() == enderecoDTO.getNumero()){
                Endereco enderecoAtualizado = endereco;
                enderecoAtualizado.setCep(enderecoDTO.getCep());
                enderecoAtualizado.setIsEnderecoPrincipal(enderecoDTO.getIsEnderecoPrincipal());
                enderecoAtualizado.setCidade(enderecoDTO.getCidade());
                enderecoAtualizado.setNumero(enderecoDTO.getNumero());
                enderecoAtualizado.setLogradouro(enderecoDTO.getLogradouro());
                enderecoRepository.save(enderecoAtualizado);
                return new ResponseEntity("O endereço de cep " + enderecoAtualizado.getCep() + " foi atualizado com sucesso.", HttpStatus.OK);
            }
        };
        return null;
    }
}
