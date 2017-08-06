package keren.movie.moviekeren.network.retrofit;

import keren.movie.moviekeren.network.model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author hendrawd on 7/30/17
 */

public interface MovieService {

    @GET("movie/{category}")
    Call<Movie> getMovies(@Path("category") String category);
}
