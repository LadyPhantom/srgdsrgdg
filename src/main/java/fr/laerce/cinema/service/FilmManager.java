package fr.laerce.cinema.service;

import fr.laerce.cinema.dao.FilmDao;
import fr.laerce.cinema.dao.PlayDao;
import fr.laerce.cinema.model.Film;
import fr.laerce.cinema.model.Play;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Service pour la gestion des films
 */
@Component
public class FilmManager {
    /**
     * Les DAO qui gèrent les films et roles dans le système de persistance
     */
    private FilmDao filmDao;
    private PlayDao playDao;

    /**
     * Constructeur utilisé par Spring pour la construction du bean
     * @param filmDao le DAO qui gère les films dans le système de persistance, ne peut être null
     * @param playDao le DAO qui gère les roles dans le système de persistance, ne peut être null
     */
    public FilmManager(FilmDao filmDao, PlayDao playDao){
        this.filmDao = filmDao;
        this.playDao = playDao;
        assert(filmDao != null);
        assert(playDao != null);
    }

    // ----------------------------------------------------------------------- //

    public Film getById(long id){
        return filmDao.findById(id).get();
    }

    public List<Film> getAll(){
        return filmDao.findAllByOrderByTitle();
    }

    /**
     * Sauvegarder le film
     * @param film le film à créer ou modifier
     * @return l'id du film créé ou modifié
     */
    /*public Long save(Film film){
        filmDao.save(film);
        return film.getId();
    }
*/
    public Film save(Film film){
        return filmDao.save(film);
    }
    /**
     * Supprime un rôle dans un film
     * @param roleId l'identifiant du rôle à supprimer
     * @return l'id du film auquel appartenait le rôle
     */

    public Film removeRole(long roleId){
        Play role = playDao.findById(roleId).get();
        long filmId = role.getFilm().getId();
        Film film = filmDao.findById(filmId).get();
        film.getRoles().remove(role);
        filmDao.save(film);
        playDao.delete(role);
        return film;
    }

    /**
     * Crée un role associé à un film
     * @param filmId l'identifiant du film
     * @param play le role à créer
     * @return l'id du film associé au rôle
     */
    public Film addRole(long filmId, Play play){
        Film film = filmDao.findById(filmId).get();
        play.setFilm(film);
        playDao.save(play);
        return play.getFilm();
    }

    public Play saveRole(Play play){
        return playDao.save(play);
    }
}
