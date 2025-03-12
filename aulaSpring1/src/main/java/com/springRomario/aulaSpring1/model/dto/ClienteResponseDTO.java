package com.springRomario.aulaSpring1.model.dto;

import java.util.List;
import java.util.Set;

public record ClienteResponseDTO(
        Integer id,
        String nome,
        Long cpf,
        List<ContaClienteResponseDTO> contas) {
}
