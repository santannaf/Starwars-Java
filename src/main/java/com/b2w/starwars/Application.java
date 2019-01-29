package com.b2w.starwars;

import com.b2w.starwars.gateway.database.StarWarsRepository;
import com.b2w.starwars.gateway.database.data.StarWarsDb;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {

	private final StarWarsRepository starWarsRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			starWarsRepository.deleteAll();
			addPlanets();
		};
	}

	private void addPlanets() {
		starWarsRepository.save(StarWarsDb.builder()
				.nome("Tatooine")
				.clima("arid")
				.terreno("desert")
				.aparicoes(5)
				.build());

		starWarsRepository.save(StarWarsDb.builder()
				.nome("Alderaan")
				.clima("temperate")
				.terreno("grasslands, mountains")
				.aparicoes(2)
				.build());

		starWarsRepository.save(StarWarsDb.builder()
				.nome("Yavin IV")
				.clima("temperate, tropical")
				.terreno("jungle, rainforests")
				.aparicoes(1)
				.build());

		starWarsRepository.save(StarWarsDb.builder()
				.nome("Hoth")
				.clima("frozen")
				.terreno("tundra, ice caves, mountain ranges")
				.aparicoes(1)
				.build());

		starWarsRepository.save(StarWarsDb.builder()
				.nome("Dagobah")
				.clima("murky")
				.terreno("swamp, jungles")
				.aparicoes(3)
				.build());
	}

}

