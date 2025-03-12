package com.springRomario.aulaSpring1.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_pj")
public class PJuridica extends Cliente {

    private Long cnpj;
    private String razaoSocial;
}
