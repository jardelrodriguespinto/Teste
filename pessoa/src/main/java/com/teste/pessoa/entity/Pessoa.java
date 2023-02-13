package com.teste.pessoa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pessoa")
public class Pessoa{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pessoa_id")
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, name = "data_de_nascimento")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeNascimento;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pessoa_id", foreignKey = @ForeignKey(name = "fk_pessoa_id"))
    private List<Endereco> enderecos;
    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
        if (enderecos != null && enderecos.size() == 1) {
            enderecos.get(0).setIsPrincipal(true);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa)) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(getId(), pessoa.getId()) && Objects.equals(getNome(), pessoa.getNome()) && Objects.equals(getDataDeNascimento(), pessoa.getDataDeNascimento()) && Objects.equals(getEnderecos(), pessoa.getEnderecos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getDataDeNascimento(), getEnderecos());
    }
}
