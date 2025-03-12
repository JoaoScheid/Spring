package com.springRomario.aulaSpring1.model.dto;

public record ClienteContaGetResponseDTO(
        Integer id,
        String nome,
        Long cpf
) {
}
