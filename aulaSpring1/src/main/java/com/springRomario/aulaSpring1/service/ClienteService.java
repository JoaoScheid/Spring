package com.springRomario.aulaSpring1.service;

import com.springRomario.aulaSpring1.model.dto.ClientePostRequestDTO2;
import com.springRomario.aulaSpring1.model.exception.MesmoTitularException;
import com.springRomario.aulaSpring1.model.dto.ClientePostRequestDTO;
import com.springRomario.aulaSpring1.model.dto.ClientePutRequestDTO;
import com.springRomario.aulaSpring1.model.entity.Cliente;
import com.springRomario.aulaSpring1.model.entity.Conta;
import com.springRomario.aulaSpring1.repository.ClienteRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository    repository;
    private ContaService contaService;
    private final ModelMapper modelMapper;

    public Cliente cadastrar(@Valid ClientePostRequestDTO2 clienteDTO) {
        Cliente cliente = clienteDTO.convert();
        return repository.save(cliente);
    }

    public Cliente editar(@NotNull @Positive Integer id, @Valid ClientePutRequestDTO clienteDTO) {
        if(repository.existsById(id)){
            Cliente clienteAtual = buscar(id);
            Cliente clienteEditado = clienteDTO.convert();
            modelMapper.map(clienteEditado, clienteAtual);
            return repository.save(clienteAtual);
        }
        //repository.findByNomeOrderByCpf(clienteDTO.nome());
        throw new NoSuchElementException();
    }

    public Cliente alterarConta(@Valid @NotNull @Positive Integer id, @NotNull @Positive Integer idConta) {
    Cliente cliente = repository.findById(id).get();
    Conta conta = contaService.buscarConta(idConta);
    if (cliente.getContas().contains(conta)) {
        cliente.removeConta(conta);
    }else if(conta.getTitular() == null){
            cliente.addConta(conta);
    }else {
            throw new RuntimeException();
    }
    return repository.save(cliente);
    }

    public Cliente buscar(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void remover(@NotNull @Valid Integer id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Erro ao remover Cliente");
        }
        repository.deleteById(id);
    }

    public Cliente buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public Page<Cliente> buscarConta(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
