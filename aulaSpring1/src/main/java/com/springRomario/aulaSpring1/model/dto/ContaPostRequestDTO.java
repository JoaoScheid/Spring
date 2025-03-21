package com.springRomario.aulaSpring1.model.dto;

import com.springRomario.aulaSpring1.model.entity.Cliente;
import com.springRomario.aulaSpring1.model.entity.Conta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ContaPostRequestDTO(@NotNull Integer idTitular, @Positive @NotNull Integer numero, @PositiveOrZero Double limite) {
    public Conta convert(Cliente cliente) {
       return Conta.builder()
                .numero(numero)
                .titular(cliente)
                .limite(limite)
                .build();
    }
}
