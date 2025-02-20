package com.springRomario.aulaSpring1.service;

import com.springRomario.aulaSpring1.model.dto.ContaPostRequestDTO;
import com.springRomario.aulaSpring1.model.entity.Conta;
import com.springRomario.aulaSpring1.repository.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContaService {

    private ContaRepository repository;

    public Conta criarConta(ContaPostRequestDTO contaDTO){
        Conta conta = contaDTO.convert();
        return repository.save(conta);
    }

    public List<Conta> buscarContas() {
        return repository.findAll();
    }

    public Page<Conta> buscarContas(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Conta buscarConta(Integer id) {
        return repository.findById(id).get();
    }

    public void deletarConta(Integer id) {
        repository.deleteById(id);
    }

    public Conta atualizarConta(Conta conta, Integer id) {
        conta.setId(id);
        return repository.save(conta);
    }

    public Conta alterarLimite(Integer id, Double limite) {
        Conta conta = buscarConta(id);
        conta.setLimite(limite);
        return repository.save(conta);
    }
}
