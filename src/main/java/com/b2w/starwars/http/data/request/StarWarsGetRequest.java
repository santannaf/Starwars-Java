package com.b2w.starwars.http.data.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StarWarsGetRequest {

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String clima;

    @NotNull
    @NotBlank
    private String terreno;
}
