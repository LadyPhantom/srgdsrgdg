package fr.laerce.cinema.JsonMapping;

import java.util.List;

public class JsonForFilm {

    public long filmId;

    public long directorId;

    // Ajout/Suppression d'un role
    public long roleId;
    public String roleName;
    public int rank;

    // Liste des genres
    public List<Long> genresIds;

}
