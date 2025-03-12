package com.springRomario.aulaSpring1.service;

import com.springRomario.aulaSpring1.model.dto.ContaPostRequestDTO;
import com.springRomario.aulaSpring1.model.dto.ContaPutRequestDTO;
import com.springRomario.aulaSpring1.model.entity.Cliente;
import com.springRomario.aulaSpring1.model.entity.Conta;
import com.springRomario.aulaSpring1.repository.ContaRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContaService {

    @Lazy @Autowired
    private ClienteService clienteService;
    @NonNull
    private final ContaRepository repository;

    public Conta criarConta(ContaPostRequestDTO contaDTO){
        Cliente cliente = clienteService.buscar(contaDTO.idTitular());
        Conta conta = contaDTO.convert(cliente);
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

    public Conta atualizarConta(ContaPutRequestDTO contaDto, Integer id) {
        Conta contaAntiga = buscarConta(id);
        Conta contaEditada = contaDto.convert();
        contaEditada.setId(id);
        contaEditada.setNumero(contaAntiga.getNumero());
        contaEditada.setSaldo(contaAntiga.getSaldo());
        return repository.save(contaEditada);
    }

    public Conta alterarLimite(Integer id, Double limite) {
        Conta conta = buscarConta(id);
        conta.setLimite(limite);
        return repository.save(conta);
    }
}
