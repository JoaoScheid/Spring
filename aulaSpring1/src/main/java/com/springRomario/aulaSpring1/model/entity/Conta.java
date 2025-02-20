package com.springRomario.aulaSpring1.model.entity;

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
public class Conta {

    //@ToString.Exclude //Nao vai mostrar o atributo no toString
    @NonNull //Atributo obrigátorio
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull //Atributo obrigátorio
    @Column(name = "numero_da_conta",
    nullable = false, unique = true)
    private Integer numero;
    @NonNull
    private Double saldo;
    @NonNull
    private Double limite;
    @NonNull
    @Column(nullable = false)
    private String titular;
}
