package com.b2w.starwars.gateway.database;

import com.b2w.starwars.gateway.database.data.StarWarsDb;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StarWarsRepository extends MongoRepository<StarWarsDb, String> {

    List<StarWarsDb> findByNome(String nome);
}
