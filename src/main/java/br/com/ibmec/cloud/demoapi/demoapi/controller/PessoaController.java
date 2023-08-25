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

import br.com.ibmec.cloud.demoapi.demoapi.model.Pessoa;
import br.com.ibmec.cloud.demoapi.demoapi.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository _pessoaRepository;

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAll() {
        try {
            return new ResponseEntity<>(this._pessoaRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Pessoa> create(@RequestBody Pessoa item) {
        try {
            Pessoa result = this._pessoaRepository.save(item);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Pessoa> getById(@PathVariable("id") long id) {

        Optional<Pessoa> result = this._pessoaRepository.findById(id);

        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Pessoa> update(@PathVariable("id") long id, @RequestBody Pessoa pessoaNovosDados) {

        Optional<Pessoa> result = this._pessoaRepository.findById(id);

        // Não achei a pessoa a ser atualizada
        if (result.isPresent() == false) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Pessoa pessoaASerAtualizada = result.get();
        pessoaASerAtualizada.setNome(pessoaNovosDados.getNome());
        pessoaASerAtualizada.setCpf(pessoaNovosDados.getCpf());

        this._pessoaRepository.save(pessoaASerAtualizada);

        return new ResponseEntity<>(pessoaASerAtualizada, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        try {

            Optional<Pessoa> pessoaASerExcluida = this._pessoaRepository.findById(id);

            // Não achei a pessoa a ser excluida
            if (pessoaASerExcluida.isPresent() == false) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            this._pessoaRepository.delete(pessoaASerExcluida.get());

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}