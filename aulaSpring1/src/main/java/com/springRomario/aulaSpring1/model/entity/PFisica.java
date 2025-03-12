package com.springRomario.aulaSpring1.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "tb_pf")
public class PFisica extends Cliente {

    private String sobrenome;
    private Long rg;
    private Integer id;

}
