package com.b2w.starwars;

import com.b2w.starwars.gateway.data.response.Films;
import com.b2w.starwars.gateway.data.response.Planets;
import com.b2w.starwars.gateway.data.response.StarWarsGatewayResponse;
import com.b2w.starwars.gateway.database.data.StarWarsDb;
import com.b2w.starwars.http.data.request.StarWarsGetRequest;
import com.b2w.starwars.usecase.data.request.StarWarsUseCaseRequest;
import com.b2w.starwars.usecase.data.response.StarWarsPlanetsUseCaseResponse;
import com.b2w.starwars.usecase.data.response.StarWarsUseCaseAddPlanetResponse;
import com.b2w.starwars.usecase.data.response.StarWarsUseCaseDeletePlanetResponse;
import com.b2w.starwars.usecase.entity.StarWarsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                .aparicoesEmFilmes(1)
                .build();
    }

    public StarWarsUseCaseDeletePlanetResponse getStarWarsUseCaseDeletePlanetResponse() {
        return StarWarsUseCaseDeletePlanetResponse.builder()
                .deletado(true)
                .build();
    }

    public List<Planets> getListPlanets() {

        List<Planets> listPlanets = new ArrayList<>();
        List<Films> listFilms = new ArrayList<>();

        Films film = Films.builder()
                .url("https://swapi.co/api/films/2/")
                .build();

        listFilms.add(film);

        Planets planets = Planets.builder()
                .nome("Hoth")
                .clima("frozen")
                .terreno("tundra, ice caves, mountain range")
                .filmes(listFilms)
                .build();

        listPlanets.add(planets);

        return listPlanets;
    }

    public StarWarsGatewayResponse getStarWarsGatewayResponse() {
        return StarWarsGatewayResponse.builder()
                .planets(getListPlanets())
                .build();
    }

    public StarWarsDTO getStarWarsDTO() {
        return StarWarsDTO.builder()
                .nome("Hoth")
                .clima("frozen")
                .terreno("tundra, ice caves, mountain range")
                .aparicoes(1)
                .build();
    }

    public StarWarsDb getStarWarsDb() {
        return StarWarsDb.builder()
                .nome("Hoth")
                .clima("frozen")
                .terreno("tundra, ice caves, mountain range")
                .aparicoes(1)
                .build();
    }

    public List<StarWarsDb> getListStarWarsDb() {

        List<StarWarsDb> list = new ArrayList<>();
        list.add(getStarWarsDb());

        return list;
    }

    public  List<StarWarsPlanetsUseCaseResponse> getListStarWarsPlanetsUseCaseResponse() {

        List<StarWarsPlanetsUseCaseResponse> list = new ArrayList<>();
        list.add(getStarWarsPlanetsUseCaseResponse());

        return list;
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
