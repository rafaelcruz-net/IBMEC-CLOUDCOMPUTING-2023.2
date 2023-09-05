package br.com.ibmec.cloud.demoapi.demoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.ibmec.cloud.demoapi.demoapi.model.Endereco;
import br.com.ibmec.cloud.demoapi.demoapi.model.Pessoa;
import br.com.ibmec.cloud.demoapi.demoapi.repository.EnderecoRepository;

@Service
public class EnderecoService {
    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    PessoaService pessoaService;

    public List<Endereco> findAll() {
        return this.enderecoRepository.findAll();
    }

    public Optional<Endereco> findById(long id) {
        return this.enderecoRepository.findById(id);
    }

    public Endereco create(long idPessoa, Endereco newEndereco) throws Exception {
        Optional<Pessoa> opPessoa = this.pessoaService.findById(idPessoa);

        if (opPessoa.isPresent() == false) {
            throw new Exception("Não encontrei a pessoa para adicionar o endereço");
        }

        Pessoa pessoa = opPessoa.get();
        pessoa.addEndereco(newEndereco);
        this.pessoaService.saveEndereco(pessoa);

        Endereco result = pessoa.getEnderecos().get(pessoa.getEnderecos().size() - 1);
        return result;
    }

    public Endereco update(long id, Endereco newData) throws Exception {
        Optional<Endereco> existingItemOptional = enderecoRepository.findById(id);

        if (existingItemOptional.isPresent() == false)
            throw new Exception("Não encontrei o endereco a ser atualizado");

        Endereco existingItem = existingItemOptional.get();

        existingItem.setLogradouro(newData.getLogradouro());
        existingItem.setCep(newData.getCep());
        existingItem.setCidade(newData.getCidade());
        existingItem.setComplemento(newData.getComplemento());
        existingItem.setEstado(newData.getEstado());

        enderecoRepository.save(existingItem);
        return existingItem;
    }

    public void delete(long id) throws Exception {
        Optional<Endereco> endereco = this.enderecoRepository.findById(id);

        if (endereco.isPresent() == false)
            throw new Exception("Não encontrei o endereco a ser atualizado");

        this.enderecoRepository.delete(endereco.get());
    }

}
