package com.attornatus.desafioDouglasRios.repository;

import com.attornatus.desafioDouglasRios.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository <Pessoa, Long> {
    Optional<Pessoa> findByCpf(String cpf);
}
