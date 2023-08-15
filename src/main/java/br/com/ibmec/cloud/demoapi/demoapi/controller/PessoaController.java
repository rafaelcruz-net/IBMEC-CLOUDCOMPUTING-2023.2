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

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAll() {
        try {
            List<Pessoa> items = new ArrayList<Pessoa>();
            
            Pessoa pessoa1 = new Pessoa();
            pessoa1.setId(1);
            pessoa1.setNome("Rafael Cruz");
            pessoa1.setCpf("12345678900");

            items.add(pessoa1);

            Pessoa pessoa2 = new Pessoa();
            pessoa2.setId(2);
            pessoa2.setNome("João da Silva");
            pessoa2.setCpf("2165498700");

            items.add(pessoa2);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}