package com.teste.pessoa.controller;

import com.teste.pessoa.dto.EnderecoDto;
import com.teste.pessoa.entity.Endereco;
import com.teste.pessoa.service.EnderecoService;
import com.teste.pessoa.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class EnderecoController {
    private final PessoaService pessoaService;
    private final EnderecoService enderecoService;

    // Endpoints para o endereço
    @GetMapping("/{id}/enderecos")
    public ResponseEntity<?> obterTodosOsEnderecosPorPessoa(@PathVariable Long id){
        List<Endereco> enderecos = enderecoService.obterTodosOsEnderecosPorPessoa(id);
        if (enderecos != null){
            List<EnderecoDto> enderecosDto = EnderecoDto.converterDtoParaEntity(enderecos);
            return new ResponseEntity<>(enderecosDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/{id}/endereco-principal")
    public ResponseEntity<?> obterEnderecoPrincipal(@PathVariable Long id){
        Endereco enderecoPrincipal = enderecoService.obterEnderecoPrincipal(id);
        if (enderecoPrincipal != null){
            EnderecoDto enderecoDto = EnderecoDto.converterEntityParaDto(enderecoPrincipal);
            return new ResponseEntity<>(enderecoDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/{id}/endereco")
    public ResponseEntity<?> criarNovoEnderecoParaPessoa(@PathVariable Long id ,@RequestBody EnderecoDto novoEnderecoDto){
        Endereco novoEndereco = EnderecoDto.converterDtoParaEntity(novoEnderecoDto);
        Endereco endereco = enderecoService.criarNovoEnderecoParaPessoa(id,novoEndereco);
        if (endereco != null){
            EnderecoDto enderecoDto = EnderecoDto.converterEntityParaDto(endereco);
            return new ResponseEntity<>(enderecoDto, HttpStatus.CREATED);
        }
        return new ResponseEntity("Endereço já existe no banco de dados",HttpStatus.CONFLICT);
    }
    @PutMapping("/{id}/endereco/{enderecoId}")
    public ResponseEntity<?> modificarEndereco(@PathVariable Long id, @PathVariable Long enderecoId, @RequestBody EnderecoDto modificarEnderecoDto){
        try{
            Endereco endereco = EnderecoDto.converterDtoParaEntity(modificarEnderecoDto);
            Endereco enderecoAtual = enderecoService.obterEnderecoPorId(id, enderecoId);
            enderecoService.modificarEndereco(id, enderecoId, endereco);
            EnderecoDto enderecoDto = EnderecoDto.converterEntityParaDto(endereco);
            return new ResponseEntity<>(enderecoDto, HttpStatus.OK);
        } catch (IllegalAccessError ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
