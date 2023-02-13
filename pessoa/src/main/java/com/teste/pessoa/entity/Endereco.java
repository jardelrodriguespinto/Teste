package com.teste.pessoa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enderecos")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "endereco_id")
    private Long enderecoId;
    @Column(nullable = false)
    private String logradouro;
    @Column(nullable = false)
    private String cep;
    @Column(nullable = false)
    private Integer numero;
    private Boolean isPrincipal;
    @Column(nullable = false)
    private String cidade;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "pessoa_id")
    @JsonIgnore
    private Pessoa pessoa;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco)) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(getEnderecoId(), endereco.getEnderecoId()) && Objects.equals(getLogradouro(), endereco.getLogradouro()) && Objects.equals(getCep(), endereco.getCep()) && Objects.equals(getNumero(), endereco.getNumero()) && Objects.equals(getIsPrincipal(), endereco.getIsPrincipal()) && Objects.equals(getCidade(), endereco.getCidade()) && Objects.equals(getPessoa(), endereco.getPessoa());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEnderecoId(), getLogradouro(), getCep(), getNumero(), getIsPrincipal(), getCidade(), getPessoa());
    }
}
