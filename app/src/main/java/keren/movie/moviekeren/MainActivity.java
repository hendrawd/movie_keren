package keren.movie.moviekeren;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import keren.movie.moviekeren.model.Movie;
import keren.movie.moviekeren.model.Result;
import keren.movie.moviekeren.retrofit.MovieService;
import keren.movie.moviekeren.retrofit.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<Movie> {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMovieList();
    }

    /**
     * Mendapatkan movie list dari network menggunakan retrofit
     */
    private void getMovieList() {
        final Call<Movie> call = ServiceGenerator.createService(MovieService.class).getPopularMovies();
        call.enqueue(MainActivity.this);
    }

    private void showMovieList(List<Result> movieList) {
        for (Result result : movieList) {
            Log.v(TAG, result.getTitle());
        }
    }

    /**
     * Callback yang dipanggil saat mendapatkan response dari server
     *
     * @param call     Call
     * @param response Response
     */
    @Override
    public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
        Movie movie = response.body();
        if (movie != null) {
            List<Result> movieList = movie.getResults();
            showMovieList(movieList);
        }
    }

    /**
     * Callback yang dipanggil saat terjadi error waktu mendapatkan response dari server
     *
     * @param call Call
     * @param t    Throwable yang berisi error
     */
    @Override
    public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
        //TODO implement action if there is a failure
    }
}
