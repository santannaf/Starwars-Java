package com.b2w.starwars.usecase.converter.impl;

import com.b2w.starwars.gateway.data.response.StarWarsGatewayResponse;
import com.b2w.starwars.MocksConverter;
import com.b2w.starwars.gateway.database.data.StarWarsDb;
import com.b2w.starwars.usecase.converter.StarWarsUseCaseConverter;
import com.b2w.starwars.usecase.data.request.StarWarsUseCaseRequest;
import com.b2w.starwars.usecase.data.response.StarWarsPlanetsUseCaseResponse;
import com.b2w.starwars.usecase.entity.StarWarsDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StarWarsUseCaseConverterImplTest {

    StarWarsUseCaseConverter converter;

    MocksConverter mocksConverter = new MocksConverter();

    @Before
    public void setUp() {
        converter = new StarWarsUseCaseConverterImpl();
    }

    @Test
    public void shouldToDataBase() {

        StarWarsDTO request = mocksConverter.getStarWarsDTO();
        StarWarsDb expected = mocksConverter.getStarWarsDb();

        StarWarsDb response = converter.toDataBase(request);

        assertThat(response).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    public void shouldToEntity() {

        StarWarsUseCaseRequest starWarsUseCaseRequest = mocksConverter.getStarWarsUseCaseRequest();
        StarWarsGatewayResponse starWarsGatewayResponse = mocksConverter.getStarWarsGatewayResponse();

        StarWarsDTO expected = mocksConverter.getStarWarsDTO();
        StarWarsDTO response = converter.toEntity(starWarsUseCaseRequest, starWarsGatewayResponse);

        assertThat(response).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    public void shouldToListPlanets() {

        List<StarWarsDb> listRequest = new ArrayList<>();
        List<StarWarsPlanetsUseCaseResponse> listExpected = new ArrayList<>();

        StarWarsDb request = mocksConverter.getStarWarsDb();
        StarWarsPlanetsUseCaseResponse expected = mocksConverter.getStarWarsPlanetsUseCaseResponse();
        listRequest.add(request);
        listExpected.add(expected);

        List<StarWarsPlanetsUseCaseResponse> response = converter.toListPlanets(listRequest);

        assertThat(response).isEqualTo(listExpected);
    }

    @Test
    public void shouldToStarWarsUseCaseResponse() {

        StarWarsDb request = mocksConverter.getStarWarsDb();

        StarWarsPlanetsUseCaseResponse expected = mocksConverter.getStarWarsPlanetsUseCaseResponse();
        StarWarsPlanetsUseCaseResponse response = converter.toStarWarsUseCaseResponse(request);

        assertThat(response).isEqualToComparingFieldByFieldRecursively(expected);
    }
}