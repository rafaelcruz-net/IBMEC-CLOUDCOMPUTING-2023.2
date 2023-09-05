package br.com.ibmec.cloud.demoapi.demoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.ibmec.cloud.demoapi.demoapi.model.Pessoa;
import br.com.ibmec.cloud.demoapi.demoapi.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository _pessoaRepository;

    public List<Pessoa> findAll() {
        return this._pessoaRepository.findAll();
    }

    public Optional<Pessoa> findById(long id) {
        return this._pessoaRepository.findById(id);
    }

    public Pessoa save(Pessoa pessoa) throws Exception {
        if (this._pessoaRepository.countByCpf(pessoa.getCpf()) > 0) {
            throw new Exception("Este CPF já existe na base de dados");
        }
        this._pessoaRepository.save(pessoa);
        return pessoa;
    }

    public Pessoa update(long id, Pessoa newData) throws Exception {
        Optional<Pessoa> result = this._pessoaRepository.findById(id);

        if (result.isPresent() == false) {
            throw new Exception("Não encontrei a pessoa a ser atualizada");
        }

        Pessoa pessoaASerAtualizada = result.get();
        pessoaASerAtualizada.setNome(newData.getNome());
        pessoaASerAtualizada.setCpf(newData.getCpf());
        this._pessoaRepository.save(pessoaASerAtualizada);
        return pessoaASerAtualizada;
    }

    public void delete(long id) throws Exception {
        Optional<Pessoa> pessoaASerExcluida = this._pessoaRepository.findById(id);
        // Não achei a pessoa a ser excluida
        if (pessoaASerExcluida.isPresent() == false) {
            throw new Exception("Não encontrei a pessoa a ser atualizada");
        }
        this._pessoaRepository.delete(pessoaASerExcluida.get());
    }

    public void saveEndereco(Pessoa pessoa) {
        this._pessoaRepository.save(pessoa);
    }

}
