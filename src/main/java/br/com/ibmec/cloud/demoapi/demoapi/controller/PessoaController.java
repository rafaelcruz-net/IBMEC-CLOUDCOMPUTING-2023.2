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

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private static ArrayList<Pessoa> Pessoas = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAll() {
        try {
            return new ResponseEntity<>(Pessoas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Pessoa> create(@RequestBody Pessoa item) {
        try {
            Pessoas.add(item);
            return new ResponseEntity<>(item, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Pessoa> getById(@PathVariable("id") Integer id) {

        Pessoa result = null;

        for (Pessoa item : Pessoas) {
            if (item.getId() == id) {
                result = item;
                break;
            }
        }

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Pessoa> update(@PathVariable("id") Integer id, @RequestBody Pessoa pessoaNovosDados) {

        Pessoa pessoaASerAtualizada = null;

        for (Pessoa item : Pessoas) {
            if (item.getId() == id) {
                pessoaASerAtualizada = item;
                break;
            }
        }

        // Não achei a pessoa a ser atualizada
        if (pessoaASerAtualizada == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        pessoaASerAtualizada.setNome(pessoaNovosDados.getNome());
        pessoaASerAtualizada.setCpf(pessoaNovosDados.getCpf());

        return new ResponseEntity<>(pessoaASerAtualizada, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {

            Pessoa pessoaASerExcluida = null;

            for (Pessoa item : Pessoas) {
                if (item.getId() == id) {
                    pessoaASerExcluida = item;
                    break;
                }
            }

            // Não achei a pessoa a ser excluida
            if (pessoaASerExcluida == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Pessoas.remove(pessoaASerExcluida);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}