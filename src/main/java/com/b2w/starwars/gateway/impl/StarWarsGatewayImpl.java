package com.b2w.starwars.gateway.impl;

import com.b2w.starwars.gateway.StarWarsGateway;
import com.b2w.starwars.gateway.data.response.StarWarsGatewayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class StarWarsGatewayImpl implements StarWarsGateway {

    private final RestTemplate restTemplate;

    @Override
    public StarWarsGatewayResponse getPlanets() throws Exception {

        try {

            ResponseEntity<StarWarsGatewayResponse> response = restTemplate.exchange("https://swapi.co/api/planets/",
                    HttpMethod.GET,
                    new HttpEntity<>("", getHeaders()),
                    StarWarsGatewayResponse.class);

            return response.getBody();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private HttpHeaders getHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        return headers;
    }

}
