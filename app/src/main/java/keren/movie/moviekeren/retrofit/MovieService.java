package keren.movie.moviekeren.retrofit;

import keren.movie.moviekeren.model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author hendrawd on 7/30/17
 */

public interface MovieService {

    @GET("movie/popular")
    Call<Movie> getPopularMovies();
}
