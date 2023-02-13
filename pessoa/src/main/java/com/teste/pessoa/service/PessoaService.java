package com.teste.pessoa.service;

import com.teste.pessoa.entity.Pessoa;
import com.teste.pessoa.repository.EnderecoRepository;
import com.teste.pessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;
    @Autowired
    public PessoaService(PessoaRepository pessoaRepository, EnderecoRepository enderecoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.enderecoRepository = enderecoRepository;
    }
    public List<Pessoa> obterTodasAsPessoas(){
        return pessoaRepository.findAll();
    }
    public Pessoa obterPessoaPorId(Long id){
        Pessoa pessoa = pessoaRepository.findById(id).orElse(null);
        if(pessoa != null){
            return pessoa;
        }
        return null;
    }
    public Pessoa adicionarPessoa(Pessoa novaPessoa){
        if(novaPessoa.getEnderecos().size() > 1) {
            throw new IllegalArgumentException("A pessoa só pode adicionar um endereço por vez");
        }
        return pessoaRepository.save(novaPessoa);
    }

    public Pessoa modificarPessoa(Long id, Pessoa modificarPessoa){
        Pessoa pessoa = pessoaRepository.findById(id).orElse(null);
        if(pessoa != null){
            pessoa.setNome(modificarPessoa.getNome());
            pessoa.setDataDeNascimento(modificarPessoa.getDataDeNascimento());
            return pessoaRepository.save(pessoa);
        }
        return null;
    }
    public void deletarPessoaPorId(Long id){
        Pessoa pessoa = obterPessoaPorId(id);
        if (pessoa != null ){
            pessoaRepository.deleteById(id);
        }
    }
}
