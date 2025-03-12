package com.springRomario.aulaSpring1.model.dto;

import com.springRomario.aulaSpring1.model.entity.Cliente;
import com.springRomario.aulaSpring1.model.entity.Conta;

import java.util.List;
import java.util.Set;

public record ClientePutRequestDTO(
        String nome,
        Long cpf,
        List<Conta> contas
) {
    public Cliente convert() {
        return Cliente.builder().nome(this.nome).cpf(this.cpf).contas(this.contas).build();
    }
}
