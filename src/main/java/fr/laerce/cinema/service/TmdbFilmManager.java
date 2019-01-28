package fr.laerce.cinema.service;

import com.google.common.io.ByteStreams;
import fr.laerce.cinema.dao.TmdbFilmDao;
import fr.laerce.cinema.model.TmdbFilm;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;


@Component
public class TmdbFilmManager {

    private TmdbFilmDao tmdbFilmDao;

    /**
     * Constructeur utilisé par Spring pour la construction du bean
     * @param tmdbFilmDao le DAO qui gère les films de l'api dans le système de persistance, ne peut être null
     */
    public TmdbFilmManager(TmdbFilmDao tmdbFilmDao){
        this.tmdbFilmDao = tmdbFilmDao;
        assert(tmdbFilmDao != null);
    }

    public void importFilms(){

        String temporaryFilePath = "" ;

        try {
           /*
           InputStream httpIS = new URL("http://files.tmdb.org/p/exports/movie_ids_01_27_2019.json.gz").openStream();

            GZIPInputStream gzipIS = new GZIPInputStream(httpIS);

            BufferedInputStream bufferedIS = new BufferedInputStream(gzipIS);

            int byteCount = 0;
            String s = "";
            while ( bufferedIS.available() > 0 && byteCount <= 100000 ){
                s += (char) bufferedIS.read();
                byteCount++;
                System.out.println(byteCount);
            }

            System.out.println(s);

            bufferedIS.close();
            gzipIS.close();
            httpIS.close();
            */

            InputStream is = new URL("http://files.tmdb.org/p/exports/movie_ids_01_27_2019.json.gz").openStream();

            FileOutputStream fos = new FileOutputStream( temporaryFilePath );

            ByteStreams.copy(is, fos);
            is.close();
            fos.flush();
            fos.close();


            BufferedInputStream bis = new BufferedInputStream(
                    new GZIPInputStream(
                            new URL("http://files.tmdb.org/p/exports/movie_ids_01_27_2019.json.gz").openStream()
                    )
            );

            BufferedReader br = new BufferedReader(
                    new InputStreamReader( bis, StandardCharsets.UTF_8)
            );
            String line;

            while ( (line = br.readLine()) != null ){
                JSONObject json = new JSONObject(line);
                String title = json.get("original_title").toString();
                long tmId = Long.valueOf( json.get("id").toString() );
                boolean adult = Boolean.valueOf(json.get("adult").toString() );

                TmdbFilm film = new TmdbFilm(title,tmId);

                if ( !adult && tmdbFilmDao.findByTmdbid(tmId) == null ){
                    tmdbFilmDao.save(film);
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
