package com.b2w.starwars.usecase.impl;

import com.b2w.starwars.MocksConverter;
import com.b2w.starwars.gateway.StarWarsGateway;
import com.b2w.starwars.gateway.database.StarWarsRepository;
import com.b2w.starwars.gateway.impl.StarWarsGatewayImpl;
import com.b2w.starwars.usecase.StarWarsUseCase;
import com.b2w.starwars.usecase.converter.StarWarsUseCaseConverter;
import com.b2w.starwars.usecase.converter.impl.StarWarsUseCaseConverterImpl;
import com.b2w.starwars.usecase.data.request.StarWarsUseCaseRequest;
import com.b2w.starwars.usecase.data.response.StarWarsPlanetsUseCaseResponse;
import com.b2w.starwars.usecase.data.response.StarWarsUseCaseAddPlanetResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StarWarsUseCaseImplTest {

    StarWarsUseCase useCase;

    MocksConverter mocksConverter = new MocksConverter();

    @InjectMocks
    StarWarsUseCaseConverter converter = mock(StarWarsUseCaseConverterImpl.class);

    @InjectMocks
    StarWarsGateway gateway = mock(StarWarsGatewayImpl.class);

    @MockBean
    StarWarsRepository repository = mock(StarWarsRepository.class);

    @Before
    public void setUp() throws Exception {
        useCase = new StarWarsUseCaseImpl(converter, repository, gateway);
    }

    @Test
    public void shouldaddPlanet() throws Exception {

        StarWarsUseCaseRequest request = mocksConverter.getStarWarsUseCaseRequest();
        StarWarsUseCaseAddPlanetResponse expected = mocksConverter.getStarWarsUseCaseAddPlanetResponse();

        when(gateway.getPlanets()).thenReturn(mocksConverter.getStarWarsGatewayResponse());
        when(converter.toEntity(request, mocksConverter.getStarWarsGatewayResponse())).thenReturn(mocksConverter.getStarWarsDTO());
        when(converter.toDataBase(mocksConverter.getStarWarsDTO())).thenReturn(mocksConverter.getStarWarsDb());
        when(repository.save(mocksConverter.getStarWarsDb())).thenReturn(mocksConverter.getStarWarsDb());

        StarWarsUseCaseAddPlanetResponse response = useCase.addPlanet(request);

        assertThat(response).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    public void shouldlistPlanets() {

        List<StarWarsPlanetsUseCaseResponse> expected = mocksConverter.getListStarWarsPlanetsUseCaseResponse();

        when(repository.findAll()).thenReturn(mocksConverter.getListStarWarsDb());
        when(converter.toListPlanets(mocksConverter.getListStarWarsDb())).thenReturn(mocksConverter.getListStarWarsPlanetsUseCaseResponse());

        List<StarWarsPlanetsUseCaseResponse> response = converter.toListPlanets(mocksConverter.getListStarWarsDb());

        assertThat(response).isEqualTo(expected);
    }

    @Test
    public void shouldGetPlanetByName() {

        String nome = "Hoth";

        when(repository.findByNome(nome)).thenReturn(mocksConverter.getListStarWarsDb());
        when(converter.toListPlanets(mocksConverter.getListStarWarsDb())).thenReturn(mocksConverter.getListStarWarsPlanetsUseCaseResponse());
        when(converter.toPlanetByName(mocksConverter.getListStarWarsDb())).thenReturn(mocksConverter.getListStarWarsPlanetsUseCaseResponse());

        List<StarWarsPlanetsUseCaseResponse> expected = mocksConverter.getListStarWarsPlanetsUseCaseResponse();
        List<StarWarsPlanetsUseCaseResponse> response = converter.toPlanetByName(mocksConverter.getListStarWarsDb());

        assertThat(response).isEqualTo(expected);
    }
}