package com.teste.pessoa.repository;

import com.teste.pessoa.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    @Query("SELECT e FROM Endereco e WHERE e.logradouro = :logradouro AND e.cep = :cep AND e.numero = :numero AND e.cidade = :cidade AND e.pessoa.id = :id")
    Endereco encontrarPeloEndereco(@Param("logradouro") String logradouro, @Param("cep") String cep, @Param("numero") Integer numero, @Param("cidade") String cidade, @Param("id") Long id);
}
