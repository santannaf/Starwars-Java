package com.b2w.starwars.gateway;

import com.b2w.starwars.gateway.data.response.StarWarsGatewayResponse;

public interface StarWarsGateway {

    StarWarsGatewayResponse getPlanets() throws Exception;
}
