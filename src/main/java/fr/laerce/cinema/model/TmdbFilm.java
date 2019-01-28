package fr.laerce.cinema.model;

import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tmdb_movies")
public class TmdbFilm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    long id;

    @Basic
    @Column(name = "title", nullable = false, length = 50)
    String title;

    @Basic
    @Column(name = "tmdbid", nullable = false)
    @Unique
    long tmdbId;


    public TmdbFilm(String title, long tmdbId){
        this.tmdbId = tmdbId;
        this.title = title;
    }

    public TmdbFilm(){}


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public long getTmdbId() {
        return tmdbId;
    }
    public void setTmdbId(long tmdbId) {
        this.tmdbId = tmdbId;
    }
}
