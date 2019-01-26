package com.b2w.starwars.usecase;

import com.b2w.starwars.usecase.data.request.StarWarsUseCaseRequest;
import com.b2w.starwars.usecase.data.response.StarWarsPlanetsUseCaseResponse;
import com.b2w.starwars.usecase.data.response.StarWarsUseCaseAddPlanetResponse;
import com.b2w.starwars.usecase.data.response.StarWarsUseCaseDeletePlanetResponse;

import java.util.List;

public interface StarWarsUseCase {

    StarWarsUseCaseAddPlanetResponse addPlanet(StarWarsUseCaseRequest starWarsUseCaseRequest) throws Exception;
    List<StarWarsPlanetsUseCaseResponse> listPlanets();
    List<StarWarsPlanetsUseCaseResponse> getPlanetByName(String nome);
    StarWarsPlanetsUseCaseResponse getPlanetById(String id) throws Exception;
    StarWarsUseCaseDeletePlanetResponse deletePlanet(String id) throws Exception;
}
