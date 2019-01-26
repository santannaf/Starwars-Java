package com.b2w.starwars.usecase.converter.impl;

import com.b2w.starwars.gateway.data.response.Planets;
import com.b2w.starwars.gateway.data.response.StarWarsGatewayResponse;
import com.b2w.starwars.gateway.database.data.StarWarsDb;
import com.b2w.starwars.usecase.converter.StarWarsUseCaseConverter;
import com.b2w.starwars.usecase.data.request.StarWarsUseCaseRequest;
import com.b2w.starwars.usecase.data.response.StarWarsPlanetsUseCaseResponse;
import com.b2w.starwars.usecase.entity.StarWarsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StarWarsUseCaseConverterImpl  implements StarWarsUseCaseConverter {

    @Override
    public StarWarsDb toDataBase(StarWarsDTO starWarsDTO) {
        return StarWarsDb.builder()
                .nome(starWarsDTO.getNome())
                .clima(starWarsDTO.getClima())
                .terreno(starWarsDTO.getTerreno())
                .aparicoes(starWarsDTO.getAparicoes())
                .build();
    }

    @Override
    public StarWarsDTO toEntity(StarWarsUseCaseRequest starWarsUseCaseRequest, StarWarsGatewayResponse starWarsGatewayResponse) {

        String nome = starWarsUseCaseRequest.getNome();

        List<Planets> planet = starWarsGatewayResponse.getPlanets().stream()
                .filter(p -> p.getNome().equals(nome))
                .collect(Collectors.toList());

        Integer aparicoes = planet.get(0).getFilmes().size();

        return StarWarsDTO.builder()
                .nome(starWarsUseCaseRequest.getNome())
                .clima(starWarsUseCaseRequest.getClima())
                .terreno(starWarsUseCaseRequest.getTerreno())
                .aparicoes(aparicoes)
                .build();
    }

    @Override
    public List<StarWarsPlanetsUseCaseResponse> toListPlanets(List<StarWarsDb> starWarsDb) {

        List<StarWarsPlanetsUseCaseResponse> list = new ArrayList<>();

        starWarsDb.forEach(x -> {
            StarWarsPlanetsUseCaseResponse starWarsPlanetsUseCaseResponse = toUseCaseResponse(x);
            list.add(starWarsPlanetsUseCaseResponse);
        });

        return list;
    }

    private StarWarsPlanetsUseCaseResponse toUseCaseResponse(StarWarsDb x) {
        return StarWarsPlanetsUseCaseResponse.builder()
                .id(x.getId())
                .nome(x.getNome())
                .clima(x.getClima())
                .terreno(x.getTerreno())
                .aparicoesEmFilmes(x.getAparicoes())
                .build();
    }

    @Override
    public List<StarWarsPlanetsUseCaseResponse> toPlanetByName(List<StarWarsDb> starWarsDb) {
        return toListPlanets(starWarsDb);
    }

    @Override
    public StarWarsPlanetsUseCaseResponse toStarWarsUseCaseResponse(StarWarsDb x) {
        return StarWarsPlanetsUseCaseResponse.builder()
                .id(x.getId())
                .nome(x.getNome())
                .clima(x.getClima())
                .terreno(x.getTerreno())
                .aparicoesEmFilmes(x.getAparicoes())
                .build();
    }
}
