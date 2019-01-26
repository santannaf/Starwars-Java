package com.b2w.starwars.gateway.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class StarWarsGatewayResponse {

    @JsonProperty("results")
    private List<Planets> planets;
}
