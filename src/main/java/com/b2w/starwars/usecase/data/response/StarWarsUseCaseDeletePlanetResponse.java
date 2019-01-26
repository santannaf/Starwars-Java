package com.b2w.starwars.usecase.data.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StarWarsUseCaseDeletePlanetResponse {

    private Boolean deletado;
}
