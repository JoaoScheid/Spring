package com.springRomario.aulaSpring1.model.entity;

import com.springRomario.aulaSpring1.model.dto.ContaClienteResponseDTO;
import com.springRomario.aulaSpring1.model.dto.ContaResponseDTO;
import jakarta.persistence.*;
import lombok.*;


//@Getter
//@Setter
//@AllArgsConstructor //Todos atributos no construtor
//@NoArgsConstructor //Construtor vazio
//@RequiredArgsConstructor // Com isso devo colocar os nonNull em cima dos atributos que quero que sejam obrigatorios
//@ToString
@Data //Tem todas as anotaçõs
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_conta")
public class Conta {

    //@ToString.Exclude //Nao vai mostrar o atributo no toString

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   // @NonNull //Atributo obrigátorio
    @Column(name = "numero_da_conta",
    nullable = false, unique = true)
    private Integer numero;
   // @NonNull  //Atributo obrigátorio
     @Builder.Default
     private Double saldo = 0.0;
    @NonNull
    private Double limite;
//    @ManyToMany(mappedBy = "contas")
//    private List<Cliente> titulares;
//    @OneToOne
//    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente titular;

    public ContaResponseDTO convert() {
        return new ContaResponseDTO(this.id, this.numero, this.saldo, this.limite, this.titular.convertToClientContaGetResponseDTO());
    }

    public ContaClienteResponseDTO convertToContaClienteResponseDTO() {
        return new ContaClienteResponseDTO(
                this.id,
                this.saldo,
                this.limite,
                this.numero
                );
    }

    public ContaResponseDTO convertToContaResponseDTO() {
    return new ContaResponseDTO(this.id, this.numero, this.saldo, this.limite, this.titular.convertToClientContaGetResponseDTO());
    }
}
