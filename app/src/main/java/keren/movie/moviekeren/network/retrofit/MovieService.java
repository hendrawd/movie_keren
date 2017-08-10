package keren.movie.moviekeren.network.retrofit;

import keren.movie.moviekeren.network.model.MovieResponse;
import keren.movie.moviekeren.network.model.ReviewResponse;
import keren.movie.moviekeren.network.model.VideoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author hendrawd on 7/30/17
 */

public interface MovieService {

    @GET("movie/{category}")
    Call<MovieResponse> getMovies(@Path("category") String category);

    @GET("movie/{movie_id}/reviews?language=en-US&page=1")
    Call<ReviewResponse> getReviews(@Path("movie_id") Integer movieId);

    @GET("movie/{movie_id}/videos")
    Call<VideoResponse> getVideos(@Path("movie_id") Integer movieId);
}
