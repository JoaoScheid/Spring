package com.springRomario.aulaSpring1.model.dto;


public record ContaResponseDTO(
        Integer id,
        Integer numero,
        Double saldo,
        Double limite,
        ClienteContaGetResponseDTO titular
) {
}
