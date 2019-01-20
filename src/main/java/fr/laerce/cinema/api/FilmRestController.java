package fr.laerce.cinema.api;

import fr.laerce.cinema.JsonMapping.JsonForFilm;
import fr.laerce.cinema.model.Film;
import fr.laerce.cinema.model.Genre;
import fr.laerce.cinema.model.Person;
import fr.laerce.cinema.service.FilmManager;
import fr.laerce.cinema.service.GenreManager;
import fr.laerce.cinema.service.PersonManager;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/film")
public class FilmRestController {

    private FilmManager filmManager;
    private GenreManager genreManager;
    private PersonManager personManager;

    public FilmRestController(FilmManager filmManager, GenreManager genreManager, PersonManager personManager){
        this.filmManager = filmManager;
        this.genreManager = genreManager;
        this.personManager = personManager;
        assert(genreManager != null);
        assert(filmManager != null);
        assert(personManager != null);
    }

    // ----------------------------------------------------------------------- //

    @GetMapping("")
    public List<Film> getAll(){
        return filmManager.getAll();
    }

    @GetMapping("/{id}")
    public Film getById(@PathVariable("id")long id){
        return filmManager.getById(id);
    }


    @PostMapping("")
    public Film mod(@RequestBody Film newData){

        Film film = filmManager.getById( newData.getId() );

        if( newData.getSummary() != null ) film.setSummary( newData.getSummary() );
        if( newData.getTitle() != null ) film.setTitle( newData.getTitle() );
        if( newData.getRating() != 0 ) film.setRating( newData.getRating() );
        if( newData.getReleaseDate() != null ) film.setReleaseDate( newData.getReleaseDate() );

        return filmManager.save(film);
    }

    @PostMapping("/mod")
    public Film modBis(@RequestBody JsonForFilm newData){
        Film film = filmManager.getById(newData.filmId);


        Set<Genre> newGenres = new HashSet<>();
        if (newData.genresIds != null){
            for (Long id : newData.genresIds){
                newGenres.add(genreManager.getById(id));
            }
        }


        if ( !newGenres.isEmpty() ) film.setGenres(newGenres);
        if ( newData.directorId != 0 ) film.setDirector( personManager.getById(newData.directorId) );

        filmManager.save(film);
        return film;
    }


    @PutMapping("/add")
    public Film add(@RequestBody Film film){
        return filmManager.save(film);
    }
    @PostMapping("/addBis")
    public String addBis(@RequestBody JsonForFilm newData){
        Film film = filmManager.getById(newData.filmId);


        Set<Genre> newGenres = new HashSet<>();
        if (newData.genresIds != null){
            for (Long id : newData.genresIds){
                newGenres.add(genreManager.getById(id));
            }
        }

        if ( !newGenres.isEmpty() ) film.setGenres(newGenres);
        if ( newData.directorId != 0 ) film.setDirector( personManager.getById(newData.directorId) );


//        return filmManager.save(film);
        return "redirect:/film/mod/" + film.getId();
    }
}
