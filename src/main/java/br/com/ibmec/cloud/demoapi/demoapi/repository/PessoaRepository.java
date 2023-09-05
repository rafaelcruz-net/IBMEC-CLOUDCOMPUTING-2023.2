package br.com.ibmec.cloud.demoapi.demoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ibmec.cloud.demoapi.demoapi.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    long countByCpf(String cpf);
}
