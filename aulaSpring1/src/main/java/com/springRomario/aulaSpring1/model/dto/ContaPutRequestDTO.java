package com.springRomario.aulaSpring1.model.dto;

import com.springRomario.aulaSpring1.model.entity.Cliente;
import com.springRomario.aulaSpring1.model.entity.Conta;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ContaPutRequestDTO(@NotNull Cliente titular, @PositiveOrZero Double limite) {
    public Conta convert() {
       return Conta.builder()
                .titular(titular)
                .limite(limite)
                .build();
    }
}
