package com.teste.pessoa.controller;

import com.teste.pessoa.dto.PessoaDto;
import com.teste.pessoa.entity.Endereco;
import com.teste.pessoa.entity.Pessoa;
import com.teste.pessoa.service.EnderecoService;
import com.teste.pessoa.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;
    private final EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<?> obterTodasAsPessoas(){
        List<Pessoa> pessoas = pessoaService.obterTodasAsPessoas();
        if (pessoas != null){
            List<PessoaDto> pessoasDto = PessoaDto.converterEntityParaDto(pessoas);
            return new ResponseEntity<>(pessoas, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obterPessoaPorId(@PathVariable Long id){
        Pessoa pessoa  = pessoaService.obterPessoaPorId(id);
        if (pessoa != null){
            PessoaDto pessoaDto = PessoaDto.converterEntityParaDto(pessoa);
            return new ResponseEntity<>(pessoa, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Pessoa não encontrada", HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> adicionarPessoa(@RequestBody PessoaDto novaPessoaDto){
        try {
            Pessoa novaPessoa = PessoaDto.converterDtoParaEntity(novaPessoaDto);
            Pessoa pessoa = pessoaService.adicionarPessoa(novaPessoa);
            PessoaDto pessoaDto = PessoaDto.converterEntityParaDto(pessoa);
            return new ResponseEntity<>(pessoaDto, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarPessoa(@PathVariable Long id, @RequestBody PessoaDto alterarPessoaDto){
        Pessoa pessoa = PessoaDto.converterDtoParaEntity(alterarPessoaDto);
        Pessoa novaPessoa = pessoaService.obterPessoaPorId(id);
        if (novaPessoa != null){
            Pessoa pessoa1 = pessoaService.modificarPessoa(id, pessoa);
            PessoaDto pessoaDto = PessoaDto.converterEntityParaDto(pessoa1);
            return new ResponseEntity<>(pessoaDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPessoaPorId(@PathVariable Long id){
         Pessoa pessoa  = pessoaService.obterPessoaPorId(id);
        if(pessoa != null){
            pessoaService.deletarPessoaPorId(id);
            return new ResponseEntity<String>("Pessoa deletada com sucesso!", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
