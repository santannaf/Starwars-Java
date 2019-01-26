package com.b2w.starwars.usecase.impl;

import com.b2w.starwars.gateway.StarWarsGateway;
import com.b2w.starwars.gateway.data.response.StarWarsGatewayResponse;
import com.b2w.starwars.gateway.database.StarWarsRepository;
import com.b2w.starwars.gateway.database.data.StarWarsDb;
import com.b2w.starwars.usecase.StarWarsUseCase;
import com.b2w.starwars.usecase.converter.StarWarsUseCaseConverter;
import com.b2w.starwars.usecase.data.request.StarWarsUseCaseRequest;
import com.b2w.starwars.usecase.data.response.StarWarsPlanetsUseCaseResponse;
import com.b2w.starwars.usecase.data.response.StarWarsUseCaseAddPlanetResponse;
import com.b2w.starwars.usecase.data.response.StarWarsUseCaseDeletePlanetResponse;
import com.b2w.starwars.usecase.entity.StarWarsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StarWarsUseCaseImpl implements StarWarsUseCase {

    private final StarWarsUseCaseConverter starWarsUseCaseConverter;
    private final StarWarsRepository starWarsRepository;
    private final StarWarsGateway starWarsGateway;

    @Override
    public StarWarsUseCaseAddPlanetResponse addPlanet(StarWarsUseCaseRequest starWarsUseCaseRequest) throws Exception {

        StarWarsGatewayResponse starWarsGatewayResponse = starWarsGateway.getPlanets();
        StarWarsDTO starWarsDTO = starWarsUseCaseConverter.toEntity(starWarsUseCaseRequest, starWarsGatewayResponse);
        StarWarsDb starWarsDb = starWarsUseCaseConverter.toDataBase(starWarsDTO);

        try {
            starWarsRepository.save(starWarsDb);
            return StarWarsUseCaseAddPlanetResponse.builder().salvo(true).build();
        } catch (Exception err) {
            throw new Exception(err.getLocalizedMessage());
        }
    }

    @Override
    public List<StarWarsPlanetsUseCaseResponse> listPlanets() {

        List<StarWarsDb> starWarsDb = starWarsRepository.findAll();
        List<StarWarsPlanetsUseCaseResponse> starWarsPlanetsUseCaseResponses = starWarsUseCaseConverter.toListPlanets(starWarsDb);

        return starWarsPlanetsUseCaseResponses;
    }

    @Override
    public List<StarWarsPlanetsUseCaseResponse> getPlanetByName(String nome) {
        List<StarWarsDb> starWarsDb = starWarsRepository.findByNome(nome);

        if (starWarsDb.size() > 1) {
            List<StarWarsPlanetsUseCaseResponse> starWarsPlanetsUseCaseResponses = starWarsUseCaseConverter.toListPlanets(starWarsDb);
            return starWarsPlanetsUseCaseResponses;
        }

        List<StarWarsPlanetsUseCaseResponse> starWarsPlanetsUseCaseResponse = starWarsUseCaseConverter.toPlanetByName(starWarsDb);
        return starWarsPlanetsUseCaseResponse;
    }

    @Override
    public StarWarsPlanetsUseCaseResponse getPlanetById(String id) throws Exception {

        try {
            StarWarsDb planetsRepository = starWarsRepository.findById(id).get();
            StarWarsPlanetsUseCaseResponse starWarsPlanetsUseCaseResponse = starWarsUseCaseConverter.toStarWarsUseCaseResponse(planetsRepository);

            return starWarsPlanetsUseCaseResponse;
        } catch (Exception erro) {
            throw new Exception("Planeta nao encontrado");
        }

    }

    @Override
    public StarWarsUseCaseDeletePlanetResponse deletePlanet(String id) throws Exception {

        try {
            starWarsRepository.deleteById(id);
            return StarWarsUseCaseDeletePlanetResponse.builder().deletado(true).build();
        } catch (Exception erro) {
            throw new Exception(erro.getLocalizedMessage());
        }
    }
}
