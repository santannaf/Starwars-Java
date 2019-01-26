package com.b2w.starwars.http.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StarWarsResponses<T> {

    private T results;
    private List<String> erros;
}
