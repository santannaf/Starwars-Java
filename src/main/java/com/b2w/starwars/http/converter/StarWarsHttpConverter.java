package com.b2w.starwars.http.converter;

import com.b2w.starwars.http.data.request.StarWarsGetRequest;
import com.b2w.starwars.usecase.data.request.StarWarsUseCaseRequest;

public interface StarWarsHttpConverter {

    StarWarsUseCaseRequest toUseCase(StarWarsGetRequest starWarsGetRequest);
}
