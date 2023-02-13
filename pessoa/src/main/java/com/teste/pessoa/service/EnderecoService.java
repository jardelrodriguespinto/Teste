package com.teste.pessoa.service;

import com.teste.pessoa.entity.Endereco;
import com.teste.pessoa.entity.Pessoa;
import com.teste.pessoa.repository.EnderecoRepository;
import com.teste.pessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;
    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaRepository = pessoaRepository;
    }
    public List<Endereco> obterTodosOsEnderecosPorPessoa(Long id){
        Pessoa pessoa = pessoaRepository.findById(id).orElse(null);
        if (pessoa != null) {
            return pessoa.getEnderecos();
        }
        return null;
    }
    public Endereco obterEnderecoPrincipal(Long id){
        //obter endereço principal da pessoa
        Pessoa pessoa = pessoaRepository.findById(id).orElse(null);
        if (pessoa != null){
            int numeroDeEnderecosPorPessoa = pessoa.getEnderecos().size();
            Endereco enderecoPrincipal = null;
            if(numeroDeEnderecosPorPessoa == 1){
                enderecoPrincipal = pessoa.getEnderecos().get(0);
            } else if (numeroDeEnderecosPorPessoa > 1) {
                enderecoPrincipal = pessoa.getEnderecos().stream().filter(e -> e.getIsPrincipal()).findFirst().orElse(null);
            }
            return enderecoPrincipal;
        }
        return null;
    }
    public Endereco criarNovoEnderecoParaPessoa( Long id , Endereco novoEndereco){

        Pessoa pessoa = pessoaRepository.findById(id).orElse(null);
        Endereco consultarEndereco = enderecoRepository.encontrarPeloEndereco(novoEndereco.getLogradouro(), novoEndereco.getCep(), novoEndereco.getNumero(),novoEndereco.getCidade(), id);
        if(pessoa != null && consultarEndereco == null){

            novoEndereco.setPessoa(pessoa);
            pessoa.getEnderecos().add(novoEndereco);
            novoEndereco.setIsPrincipal(true);

            for(Endereco endereco : pessoa.getEnderecos()){
                if(!endereco.equals(novoEndereco)){
                    endereco.setIsPrincipal(false);
                    enderecoRepository.save(novoEndereco);
                }
            }
            return enderecoRepository.save(novoEndereco);
        }
        return null;
    }
    public Endereco obterEnderecoPorId(Long id,  Long enderecoId){
        Pessoa pessoa = pessoaRepository.findById(id).orElse(null);
        Endereco endereco = enderecoRepository.findById(enderecoId).orElse(null);

        if (pessoa != null && endereco != null){
            return endereco;
        }

        return null;
    }
    public Endereco modificarEndereco(Long id, Long enderecoId, Endereco modificarEndereco){
        Pessoa pessoa = pessoaRepository.findById(id).orElse(null);
        Endereco endereco = enderecoRepository.findById(enderecoId).orElse(null);

        if (pessoa != null && endereco != null){
            List<Endereco> enderecos = pessoa.getEnderecos();
            boolean enderecoPertenceAPessoa = false;
            for (Endereco buscarEndereco : enderecos) {
                if (buscarEndereco.getEnderecoId().equals(enderecoId)) {
                    enderecoPertenceAPessoa = true;
                    break;
                }
            }
            modificarEndereco.setEnderecoId(enderecoId);
            if (enderecoPertenceAPessoa) {
                int countEnderecosPrincipais = 0;
                for (Endereco end : enderecos) {
                    if (end.getIsPrincipal()) {
                        countEnderecosPrincipais++;
                    }
                }
                endereco.setEnderecoId(modificarEndereco.getEnderecoId());
                endereco.setLogradouro(modificarEndereco.getLogradouro());
                endereco.setCep(modificarEndereco.getCep());
                endereco.setNumero(modificarEndereco.getNumero());
                endereco.setCidade(modificarEndereco.getCidade());
                if (!modificarEndereco.getIsPrincipal() && countEnderecosPrincipais == 1) {
                    throw new IllegalArgumentException("Não é possivel modifcar endereço, pois é necessário que pelo menos um endereço seja principal");
                } else {
                    for (Endereco buscarEnderecoNoRepository : pessoa.getEnderecos()) {
                        if (modificarEndereco.getIsPrincipal().equals(true)) {
                            buscarEnderecoNoRepository.setIsPrincipal(false);
                        }
                    }

                    endereco.setIsPrincipal(modificarEndereco.getIsPrincipal());
                    endereco.setPessoa(pessoa);
                }
                Endereco enderecoExiste = enderecoRepository.encontrarPeloEndereco(endereco.getLogradouro(),endereco.getCep(),endereco.getNumero(),endereco.getCidade() ,pessoa.getId());
                if(enderecoExiste == null){
                    return null;
                }
                enderecoRepository.save(endereco);
                return endereco;
            }
        }
        return null;
    }



}
