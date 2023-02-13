package com.teste.pessoa.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.teste.pessoa.entity.Endereco;
import com.teste.pessoa.entity.Pessoa;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDto {
    private Long id;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeNascimento;
    private List<Endereco> enderecos;

    public static PessoaDto converterEntityParaDto(Pessoa pessoa) {
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setId(pessoa.getId());
        pessoaDto.setNome(pessoa.getNome());
        pessoaDto.setDataDeNascimento(pessoa.getDataDeNascimento());
        pessoaDto.setEnderecos(pessoa.getEnderecos());
        return pessoaDto;
    }

    public static Pessoa converterDtoParaEntity(PessoaDto pessoaDto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaDto.getId());
        pessoa.setNome(pessoaDto.getNome());
        pessoa.setEnderecos(pessoaDto.getEnderecos());
        pessoa.setDataDeNascimento(pessoaDto.getDataDeNascimento());
        List<Endereco> enderecos = new ArrayList<>();
        for (Endereco endereco : pessoaDto.getEnderecos()) {
            enderecos.add(endereco);
        }
        pessoa.setEnderecos(enderecos);
        return pessoa;
    }



    public static List<PessoaDto>converterEntityParaDto(List<Pessoa> pessoas){
        List<PessoaDto> pessoasDto = new ArrayList<>();
        for(Pessoa pessoa : pessoas){
            if (pessoa.getEnderecos() != null) {
                pessoasDto.add(converterEntityParaDto(pessoa));
            }
        }
        return pessoasDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PessoaDto)) return false;
        PessoaDto pessoaDto = (PessoaDto) o;
        return Objects.equals(getId(), pessoaDto.getId()) && Objects.equals(getNome(), pessoaDto.getNome()) && Objects.equals(getDataDeNascimento(), pessoaDto.getDataDeNascimento()) && Objects.equals(getEnderecos(), pessoaDto.getEnderecos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getDataDeNascimento(), getEnderecos());
    }
}