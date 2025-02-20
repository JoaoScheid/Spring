package com.springRomario.aulaSpring1.model.dto;

import com.springRomario.aulaSpring1.model.entity.Conta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ContaPostRequestDTO(@NotBlank String titular, @Positive @NotNull Integer numero, @PositiveOrZero Double limite) {
    public Conta convert() {
       return Conta.builder()
                .numero(numero)
                .limite(limite)
                .limite(limite)
                .build();
    }
}
