package com.teste.pessoa.repository;

import com.teste.pessoa.entity.Endereco;
import com.teste.pessoa.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
