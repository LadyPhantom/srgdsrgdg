package fr.laerce.cinema;

import fr.laerce.cinema.service.TmdbFilmManager;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaApplication {

    public static void main(String[] args) {
//        SpringApplication.run(CinemaApplication.class, args);

        TmdbFilmManager tfm;

    }

  /* @Bean

    public CommandLineRunner runner(FilmDao dao){
        return new CommandLineRunner() {
            @Override
            @Transactional
            public void run(String... args) throws Exception {
                List<Film> films = dao.getAll();
                for (Film film: films
                     ) {
                    System.out.println("Titre : "+film.getTitle()+" Réalisateur "+film.getDirector().getSurname());
                    List<Play> roles = film.getRoles();
                    for (Play role:  roles
                         ) {
                        System.out.println(role.getKey().getPerson().getSurname()+" joue le rôle de "+role.getName());
                    }
                }

            }
        };
    }*/

}

