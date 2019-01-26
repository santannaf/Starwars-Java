package com.b2w.starwars.http.converter;

import com.b2w.starwars.http.data.request.StarWarsGetRequest;
import com.b2w.starwars.usecase.data.request.StarWarsUseCaseRequest;
import com.b2w.starwars.usecase.data.response.StarWarsPlanetsUseCaseResponse;
import com.b2w.starwars.usecase.data.response.StarWarsUseCaseAddPlanetResponse;
import com.b2w.starwars.usecase.data.response.StarWarsUseCaseDeletePlanetResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MocksConverter {

    public StarWarsUseCaseRequest getStarWarsUseCaseRequest() {
        return StarWarsUseCaseRequest.builder()
                .nome("Hoth")
                .clima("frozen")
                .terreno("tundra, ice caves, mountain range")
                .build();
    }

    public StarWarsGetRequest getStarWarsGetRequest() {
        return StarWarsGetRequest.builder()
                .nome("Hoth")
                .clima("frozen")
                .terreno("tundra, ice caves, mountain range")
                .build();
    }

    public StarWarsUseCaseAddPlanetResponse getStarWarsUseCaseAddPlanetResponse() {
        return StarWarsUseCaseAddPlanetResponse.builder()
                .salvo(true)
                .build();
    }

    public StarWarsPlanetsUseCaseResponse getStarWarsPlanetsUseCaseResponse() {
        return StarWarsPlanetsUseCaseResponse.builder()
                .nome("Hoth")
                .clima("frozen")
                .terreno("tundra, ice caves, mountain range")
                .aparicoesEmFilmes(2)
                .build();
    }

    public StarWarsUseCaseDeletePlanetResponse getStarWarsUseCaseDeletePlanetResponse() {
        return StarWarsUseCaseDeletePlanetResponse.builder()
                .deletado(true)
                .build();
    }

    public String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
