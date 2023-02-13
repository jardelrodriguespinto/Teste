package com.teste.pessoa.dto;

import com.teste.pessoa.entity.Pessoa;
import com.teste.pessoa.entity.Endereco;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDto {
    private Long enderecoId;
    private String logradouro;
    private String cep;
    private Integer numero;
    private Boolean isPrincipal;
    private String cidade;

    public static EnderecoDto converterEntityParaDto(Endereco endereco) {
        EnderecoDto enderecoDto = new EnderecoDto();
        enderecoDto.setEnderecoId(endereco.getEnderecoId());
        enderecoDto.setLogradouro(endereco.getLogradouro());
        enderecoDto.setCep(endereco.getCep());
        enderecoDto.setNumero(endereco.getNumero());
        enderecoDto.setIsPrincipal(endereco.getIsPrincipal());
        enderecoDto.setCidade(endereco.getCidade());
        return enderecoDto;
    }
    public static Endereco converterDtoParaEntity(EnderecoDto enderecoDto) {
        Endereco endereco = new Endereco();
        endereco.setEnderecoId(enderecoDto.getEnderecoId());
        endereco.setLogradouro(enderecoDto.getLogradouro());
        endereco.setCep(enderecoDto.getCep());
        endereco.setNumero(enderecoDto.getNumero());
        endereco.setIsPrincipal(enderecoDto.getIsPrincipal());
        endereco.setCidade(enderecoDto.getCidade());
        return endereco;
    }

    public static List<EnderecoDto> converterDtoParaEntity(List<Endereco> enderecos) {
        List<EnderecoDto> enderecosDto = new ArrayList<>();
        for (Endereco endereco : enderecos) {
            enderecosDto.add(converterEntityParaDto(endereco));
        }
        return enderecosDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnderecoDto)) return false;
        EnderecoDto that = (EnderecoDto) o;
        return Objects.equals(getEnderecoId(), that.getEnderecoId()) && Objects.equals(getLogradouro(), that.getLogradouro()) && Objects.equals(getCep(), that.getCep()) && Objects.equals(getNumero(), that.getNumero()) && Objects.equals(getIsPrincipal(), that.getIsPrincipal()) && Objects.equals(getCidade(), that.getCidade());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEnderecoId(), getLogradouro(), getCep(), getNumero(), getIsPrincipal(), getCidade());
    }
}
