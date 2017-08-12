package keren.movie.moviekeren.db;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import keren.movie.moviekeren.network.model.Movie;

/**
 * @author hendrawd on 8/12/17
 */
public class FavoriteMovie extends RealmObject {

    private Integer voteCount;
    private Integer id;
    private Boolean video;
    private Double voteAverage;
    private String title;
    private Double popularity;
    private String posterPath;
    private String originalLanguage;
    private String originalTitle;
    private RealmList<IntegerObject> genreIds;
    private String backdropPath;
    private Boolean adult;
    private String overview;
    private String releaseDate;

    public static FavoriteMovie fromMovie(Movie movie) {
        FavoriteMovie favoriteMovie = new FavoriteMovie();
        favoriteMovie.voteCount = movie.getVoteCount();
        favoriteMovie.id = movie.getId();
        favoriteMovie.video = movie.getVideo();
        favoriteMovie.voteAverage = movie.getVoteAverage();
        favoriteMovie.title = movie.getTitle();
        favoriteMovie.popularity = movie.getPopularity();
        favoriteMovie.posterPath = movie.getPosterPath();
        favoriteMovie.originalLanguage = movie.getOriginalLanguage();
        favoriteMovie.originalTitle = movie.getOriginalTitle();
        favoriteMovie.backdropPath = movie.getBackdropPath();
        favoriteMovie.adult = movie.getAdult();
        favoriteMovie.overview = movie.getOverview();
        favoriteMovie.releaseDate = movie.getReleaseDate();

        favoriteMovie.genreIds = new RealmList<>();
        List<Integer> genreIds = movie.getGenreIds();
        for (Integer genreId : genreIds) {
            IntegerObject integerObject = new IntegerObject();
            integerObject.integer = genreId;
            favoriteMovie.genreIds.add(integerObject);
        }
        return favoriteMovie;
    }

    public static Movie toMovie(FavoriteMovie favoriteMovie) {
        Movie movie = new Movie();
        movie.setVoteCount(favoriteMovie.voteCount);
        movie.setId(favoriteMovie.id);
        movie.setVideo(favoriteMovie.video);
        movie.setVoteAverage(favoriteMovie.voteAverage);
        movie.setTitle(favoriteMovie.title);
        movie.setPopularity(favoriteMovie.popularity);
        movie.setPosterPath(favoriteMovie.posterPath);
        movie.setOriginalLanguage(favoriteMovie.originalLanguage);
        movie.setOriginalTitle(favoriteMovie.originalTitle);
        movie.setBackdropPath(favoriteMovie.backdropPath);
        movie.setAdult(favoriteMovie.adult);
        movie.setOverview(favoriteMovie.overview);
        movie.setReleaseDate(favoriteMovie.releaseDate);

        List<Integer> genreList = new ArrayList<>();
        RealmList<IntegerObject> integerObjectRealmList = favoriteMovie.genreIds;
        for (IntegerObject integerObject : integerObjectRealmList) {
            genreList.add(integerObject.integer);
        }
        movie.setGenreIds(genreList);
        return movie;
    }
}
