package com.b2w.starwars.http;

import com.b2w.starwars.MocksConverter;
import com.b2w.starwars.http.converter.StarWarsHttpConverter;
import com.b2w.starwars.http.data.request.StarWarsGetRequest;
import com.b2w.starwars.usecase.StarWarsUseCase;
import com.b2w.starwars.usecase.data.request.StarWarsUseCaseRequest;
import com.b2w.starwars.usecase.data.response.StarWarsPlanetsUseCaseResponse;
import com.b2w.starwars.usecase.data.response.StarWarsUseCaseAddPlanetResponse;
import com.b2w.starwars.usecase.data.response.StarWarsUseCaseDeletePlanetResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StarWarsController.class})
public class StarWarsControllerTest {

    private MockMvc mockMvc;
    StarWarsController controller;

    MocksConverter mocksConverter = new MocksConverter();

    @MockBean
    StarWarsHttpConverter starWarsHttpConverter;

    @MockBean
    StarWarsUseCase starWarsUseCase;

    @Before
    public void setUp() throws Exception {

        starWarsHttpConverter = mock(StarWarsHttpConverter.class);
        starWarsUseCase = mock(StarWarsUseCase.class);

        controller = new StarWarsController(starWarsHttpConverter, starWarsUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void shouldAddPlanets() throws Exception {

        StarWarsGetRequest request = mocksConverter.getStarWarsGetRequest();
        StarWarsUseCaseRequest useCaseRequest = mocksConverter.getStarWarsUseCaseRequest();
        StarWarsUseCaseAddPlanetResponse response = mocksConverter.getStarWarsUseCaseAddPlanetResponse();

        when(starWarsHttpConverter.toUseCase(request)).thenReturn(useCaseRequest);
        when(starWarsUseCase.addPlanet(useCaseRequest)).thenReturn(response);

        MockHttpServletResponse responseController = mockMvc.perform(post("/starwars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mocksConverter.asJsonString(request)))
                .andReturn()
                .getResponse();

        assertThat(responseController.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertFalse(responseController.getContentAsString().isEmpty());
    }

    @Test
    public void shouldListPlanets() throws Exception {

        StarWarsPlanetsUseCaseResponse useCaseResponse = mocksConverter.getStarWarsPlanetsUseCaseResponse();

        List<StarWarsPlanetsUseCaseResponse> list = new ArrayList<>();
        list.add(useCaseResponse);

        when(starWarsUseCase.listPlanets()).thenReturn(list);

        MockHttpServletResponse responseController = mockMvc.perform(get("/starwars/planetas")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(responseController.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertFalse(responseController.getContentAsString().isEmpty());
    }

    @Test
    public void shouldGetPlanetByName() throws Exception {

        String nome = "Hoth";
        StarWarsPlanetsUseCaseResponse useCaseResponse = mocksConverter.getStarWarsPlanetsUseCaseResponse();

        List<StarWarsPlanetsUseCaseResponse> list = new ArrayList<>();
        list.add(useCaseResponse);

        when(starWarsUseCase.getPlanetByName(nome)).thenReturn(list);

        MockHttpServletResponse responseController = mockMvc.perform(get("/starwars/planetas/nome/" + nome)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(responseController.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertFalse(responseController.getContentAsString().isEmpty());
    }

    @Test
    public void shouldGetPlanetById() throws Exception {

        String id = "5c4cd39cf38de14eea81210a";
        StarWarsPlanetsUseCaseResponse useCaseResponse = mocksConverter.getStarWarsPlanetsUseCaseResponse();

        when(starWarsUseCase.getPlanetById(id)).thenReturn(useCaseResponse);

        MockHttpServletResponse responseController = mockMvc.perform(get("/starwars/planeta/id/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(responseController.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertFalse(responseController.getContentAsString().isEmpty());
    }

    @Test
    public void shoudlDeletePlanet() throws Exception {

        String id = "5c4cd39cf38de14eea81210a";
        StarWarsUseCaseDeletePlanetResponse starWarsUseCaseDeletePlanetResponse = mocksConverter.getStarWarsUseCaseDeletePlanetResponse();

        when(starWarsUseCase.deletePlanet(id)).thenReturn(starWarsUseCaseDeletePlanetResponse);

        MockHttpServletResponse responseController = mockMvc.perform(delete("/starwars/planeta/delete/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(responseController.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertFalse(responseController.getContentAsString().isEmpty());
    }
}