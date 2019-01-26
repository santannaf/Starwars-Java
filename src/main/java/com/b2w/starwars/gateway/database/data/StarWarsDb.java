package com.b2w.starwars.gateway.database.data;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "planets")
public class StarWarsDb {

    @Id
    private String id;
    private String nome;
    private String clima;
    private String terreno;
    private Integer aparicoes;
}
