package com.b2w.starwars.gateway.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Planets {

    @JsonProperty("name")
    private String nome;

    @JsonProperty("climate")
    private String clima;

    @JsonProperty("terrain")
    private String terreno;

    @JsonProperty("films")
    private List<Films> filmes;
}
