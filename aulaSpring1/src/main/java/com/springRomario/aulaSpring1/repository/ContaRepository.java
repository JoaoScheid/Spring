package com.springRomario.aulaSpring1.repository;

import com.springRomario.aulaSpring1.model.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    List<Conta> findByTitular_NomeContainsOrNumeroOrderByNumero(String titularNome, Integer numero);
    List<Conta> findBySaldoGreaterThan(Double saldo);

}

