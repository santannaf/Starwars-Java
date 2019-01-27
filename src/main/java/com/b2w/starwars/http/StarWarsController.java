package com.b2w.starwars.http;

import com.b2w.starwars.http.converter.StarWarsHttpConverter;
import com.b2w.starwars.http.data.request.StarWarsGetRequest;
import com.b2w.starwars.usecase.StarWarsUseCase;
import com.b2w.starwars.usecase.data.request.StarWarsUseCaseRequest;
import com.b2w.starwars.usecase.data.response.StarWarsPlanetsUseCaseResponse;
import com.b2w.starwars.usecase.data.response.StarWarsUseCaseAddPlanetResponse;
import com.b2w.starwars.usecase.data.response.StarWarsUseCaseDeletePlanetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StarWarsController {

    private final StarWarsHttpConverter starWarsHttpConverter;
    private final StarWarsUseCase starWarsUseCase;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/starwars/planeta")
    public ResponseEntity<StarWarsUseCaseAddPlanetResponse> addPlanets(@Valid @RequestBody StarWarsGetRequest starWarsGetRequest) throws Exception {

        StarWarsUseCaseRequest starWarsUseCaseRequest = starWarsHttpConverter.toUseCase(starWarsGetRequest);

        try {
            StarWarsUseCaseAddPlanetResponse starWarsUseCaseResponse = starWarsUseCase.addPlanet(starWarsUseCaseRequest);
            return new ResponseEntity<>(starWarsUseCaseResponse, HttpStatus.OK);
        } catch (Exception erro) {
            throw new Exception("Erro ao inserir um planeta > " + erro.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/starwars/planetas")
    public ResponseEntity<List<StarWarsPlanetsUseCaseResponse>> listPlanets() {

        List<StarWarsPlanetsUseCaseResponse> starWarsPlanetsUseCaseResponse = starWarsUseCase.listPlanets();
        return new ResponseEntity<>(starWarsPlanetsUseCaseResponse, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/starwars/planetas/nome/{nome}")
    public ResponseEntity<List<StarWarsPlanetsUseCaseResponse>> getPlanetByName(@NotBlank(message = "Nome do Planeta é obrigatorio.") @PathVariable String nome) {

        List<StarWarsPlanetsUseCaseResponse> starWarsPlanetsUseCaseResponse = starWarsUseCase.getPlanetByName(nome);
        return new ResponseEntity<>(starWarsPlanetsUseCaseResponse, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/starwars/planeta/id/{id}")
    public ResponseEntity<StarWarsPlanetsUseCaseResponse> getPlanetById(@NotBlank(message = "ID é obrigatorio.") @PathVariable String id) throws Exception {

        try {
            StarWarsPlanetsUseCaseResponse starWarsPlanetsUseCaseResponse = starWarsUseCase.getPlanetById(id);
            return new ResponseEntity<>(starWarsPlanetsUseCaseResponse, HttpStatus.OK);
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/starwars/planeta/delete/{id}")
    public ResponseEntity<StarWarsUseCaseDeletePlanetResponse> deletePlanet(@NotBlank(message = "ID é obrigatorio.") @PathVariable String id) throws Exception {

        try {
            StarWarsUseCaseDeletePlanetResponse starWarsUseCaseResponse = starWarsUseCase.deletePlanet(id);
            return new ResponseEntity<>(starWarsUseCaseResponse, HttpStatus.OK);
        } catch (Exception erro) {
            throw new Exception("Erro ao deletar um planeta > " + erro.getMessage());
        }
    }
}
