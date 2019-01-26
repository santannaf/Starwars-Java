package com.b2w.starwars.http.converter.impl;

import com.b2w.starwars.http.converter.StarWarsHttpConverter;
import com.b2w.starwars.http.data.request.StarWarsGetRequest;
import com.b2w.starwars.usecase.data.request.StarWarsUseCaseRequest;
import org.springframework.stereotype.Component;

@Component
public class StarWarsHttpConverterImpl implements StarWarsHttpConverter {

    @Override
    public StarWarsUseCaseRequest toUseCase(StarWarsGetRequest starWarsGetRequest) {
        return StarWarsUseCaseRequest.builder()
                .nome(starWarsGetRequest.getNome())
                .clima(starWarsGetRequest.getClima())
                .terreno(starWarsGetRequest.getTerreno())
                .build();
    }

}
