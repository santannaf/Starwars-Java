package com.b2w.starwars.http.converter.impl;

import com.b2w.starwars.MocksConverter;
import com.b2w.starwars.http.converter.StarWarsHttpConverter;
import com.b2w.starwars.http.data.request.StarWarsGetRequest;
import com.b2w.starwars.usecase.data.request.StarWarsUseCaseRequest;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class StarWarsHttpConverterImplTest {

    StarWarsHttpConverter converter;

    MocksConverter mocksConverter = new MocksConverter();

    @Before
    public void setUp() throws Exception {
        converter = new StarWarsHttpConverterImpl();
    }

    @Test
    public void toUseCase() {
        StarWarsGetRequest request = mocksConverter.getStarWarsGetRequest();
        StarWarsUseCaseRequest expected = mocksConverter.getStarWarsUseCaseRequest();

        StarWarsUseCaseRequest response = converter.toUseCase(request);
        assertThat(response).isEqualToComparingFieldByFieldRecursively(expected);
    }
}