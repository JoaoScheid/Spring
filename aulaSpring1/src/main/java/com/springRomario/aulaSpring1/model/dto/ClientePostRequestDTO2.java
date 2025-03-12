package com.springRomario.aulaSpring1.model.dto;

import com.springRomario.aulaSpring1.model.entity.Cliente;
import com.springRomario.aulaSpring1.model.entity.Conta;

import java.util.List;

public record ClientePostRequestDTO2(
        String nome,
        Long cpf,
        List<Conta> contas
) {
    public Cliente convert() {
        return Cliente.builder().cpf(this.cpf).nome(this.nome).contas(this.contas).build();
    }
}
