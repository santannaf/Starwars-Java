package com.b2w.starwars.usecase.data.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StarWarsUseCaseRequest {

    private String nome;
    private String clima;
    private String terreno;
}
