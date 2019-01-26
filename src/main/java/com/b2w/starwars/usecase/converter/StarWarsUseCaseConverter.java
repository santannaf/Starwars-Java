package com.b2w.starwars.usecase.converter;

import com.b2w.starwars.gateway.data.response.StarWarsGatewayResponse;
import com.b2w.starwars.gateway.database.data.StarWarsDb;
import com.b2w.starwars.usecase.data.request.StarWarsUseCaseRequest;
import com.b2w.starwars.usecase.data.response.StarWarsPlanetsUseCaseResponse;
import com.b2w.starwars.usecase.entity.StarWarsDTO;

import java.util.List;

public interface StarWarsUseCaseConverter {

    StarWarsDb toDataBase(StarWarsDTO starWarsDTO);
    StarWarsDTO toEntity(StarWarsUseCaseRequest starWarsUseCaseRequest, StarWarsGatewayResponse starWarsGatewayResponse);

    List<StarWarsPlanetsUseCaseResponse> toListPlanets(List<StarWarsDb> starWarsDb);
    List<StarWarsPlanetsUseCaseResponse> toPlanetByName(List<StarWarsDb> starWarsDb);

    StarWarsPlanetsUseCaseResponse toStarWarsUseCaseResponse(StarWarsDb x);

}
