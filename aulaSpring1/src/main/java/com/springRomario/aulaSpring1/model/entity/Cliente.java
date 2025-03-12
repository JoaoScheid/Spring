package com.springRomario.aulaSpring1.model.entity;

import com.springRomario.aulaSpring1.model.dto.ClienteContaGetResponseDTO;
import com.springRomario.aulaSpring1.model.dto.ClienteResponseDTO;
import com.springRomario.aulaSpring1.model.dto.ContaClienteResponseDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "tb_cliente")
@Inheritance(strategy = InheritanceType.JOINED)
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Long cpf;

//    @ManyToMany
//    @JoinTable(
//            name = "tb_cliente_conta",
//            joinColumns =
//            @JoinColumn(name = "cliente_id"),
//            inverseJoinColumns =
//            @JoinColumn(name ="conta_id"))
    @OneToMany(mappedBy = "titular", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Conta> contas;

    public List<Conta> getContas() {
        if (contas == null) {
            return new ArrayList<>();
        }
        return contas;
    }

    public void addConta(@NotNull Conta conta) {
        if (this.contas.contains(conta)) {
            throw new RuntimeException();
        }
        this.contas.add(conta);
        conta.setTitular(this);
    }


    public void removeConta(@NotNull Conta conta) {
        if (!this.contas.contains(conta)) {
            throw new RuntimeException();
        }
        this.contas.remove(conta);
        conta.setTitular(null);
    }

    public ClienteContaGetResponseDTO convertToClientContaGetResponseDTO() {
        return new ClienteContaGetResponseDTO(this.id, this.nome, this.cpf);
    }

    public ClienteResponseDTO convertToClientResponseDTO() {
        List<ContaClienteResponseDTO> contasDto = this.getContas().stream().map(Conta::convertToContaClienteResponseDTO).toList();
        return new ClienteResponseDTO(this.id, this.nome, this.cpf, contasDto);
    }
//    @OneToOne(mappedBy = "cliente")
//    private Conta conta;

}
