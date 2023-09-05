package br.com.ibmec.cloud.demoapi.demoapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ibmec.cloud.demoapi.demoapi.model.Endereco;
import br.com.ibmec.cloud.demoapi.demoapi.model.Pessoa;
import br.com.ibmec.cloud.demoapi.demoapi.repository.EnderecoRepository;
import br.com.ibmec.cloud.demoapi.demoapi.repository.PessoaRepository;
import br.com.ibmec.cloud.demoapi.demoapi.service.EnderecoService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/endereco")
@Tag(name = "Endereco", description = "")
class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<Endereco>> getAll() {
        try {
            return new ResponseEntity<>(enderecoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Endereco> getById(@PathVariable("id") Long id) {
        Optional<Endereco> existingItemOptional = enderecoService.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{idPessoa}")
    public ResponseEntity<Endereco> create(@PathVariable("idPessoa") long idPessoa, @RequestBody Endereco endereco) {
        try {
            Endereco result = this.enderecoService.create(idPessoa, endereco);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
            
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Endereco> update(@PathVariable("id") Long id, @RequestBody Endereco endereco) {
        try {
            Endereco result = this.enderecoService.update(id, endereco);    
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            this.enderecoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
