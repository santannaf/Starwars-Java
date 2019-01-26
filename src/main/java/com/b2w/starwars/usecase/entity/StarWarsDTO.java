package com.b2w.starwars.usecase.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StarWarsDTO {

    private String nome;
    private String clima;
    private String terreno;
    private Integer aparicoes;
}
