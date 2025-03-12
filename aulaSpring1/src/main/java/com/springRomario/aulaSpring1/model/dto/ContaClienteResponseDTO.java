package com.springRomario.aulaSpring1.model.dto;

public record ContaClienteResponseDTO(
        Integer id,
        Double saldo,
        Double limite,
        Integer numero
) {
}
