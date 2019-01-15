package fr.laerce.cinema.api;

import fr.laerce.cinema.model.Film;
import fr.laerce.cinema.service.FilmManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/film")
public class FilmRestController {
    private FilmManager filmManager;

    public FilmRestController(FilmManager filmManager){
        this.filmManager = filmManager;
        assert(filmManager != null);
    }


    @GetMapping("")
    public List<Film> getAll(){
        return filmManager.getAll();
    }

    @GetMapping("/{id}")
    public Film getById(@PathVariable("id")long id){
        return filmManager.getById(id);
    }


    @PutMapping("")
    public Film mod(@RequestBody Film film){
//        System.out.println("arrive");
//        return new Film();
        return filmManager.save(film);
    }
}
