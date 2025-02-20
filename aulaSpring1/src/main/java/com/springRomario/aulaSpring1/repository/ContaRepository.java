package com.springRomario.aulaSpring1.repository;

import com.springRomario.aulaSpring1.model.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

}
