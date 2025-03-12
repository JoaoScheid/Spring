package com.springRomario.aulaSpring1.model.dto;

import com.springRomario.aulaSpring1.model.entity.Cliente;

public record ClientePostRequestDTO(
        String nome,
        Long cpf
) {
    public Cliente convert() {
        return Cliente.builder().cpf(this.cpf).nome(this.nome).build();
    }
}
