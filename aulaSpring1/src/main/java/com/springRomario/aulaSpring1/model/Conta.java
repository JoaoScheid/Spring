package com.springRomario.aulaSpring1.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor //Todos atributos no construtor
@NoArgsConstructor //Construtor vazio
@RequiredArgsConstructor // Com isso devo colocar os nonNull em cima dos atributos que quero que sejam obrigatorios
@ToString
@Data //Tem todas as anotaçõs
public class Conta {
    @NonNull //Atributo obrigátorio
    @ToString.Exclude //Nao vai mostrar o numero no toString
    private int numero;
    @NonNull
    private double saldo;
    private double limite;
    private String titular;


}
