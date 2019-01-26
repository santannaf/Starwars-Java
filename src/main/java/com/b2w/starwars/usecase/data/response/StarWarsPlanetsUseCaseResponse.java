package com.b2w.starwars.usecase.data.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StarWarsPlanetsUseCaseResponse {

    private String id;
    private String nome;
    private String terreno;
    private String clima;
    private Integer aparicoesEmFilmes;
}
