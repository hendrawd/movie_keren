package keren.movie.moviekeren;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import keren.movie.moviekeren.adapter.MovieAdapter;
import keren.movie.moviekeren.network.model.Movie;
import keren.movie.moviekeren.network.model.Result;
import keren.movie.moviekeren.network.retrofit.MovieService;
import keren.movie.moviekeren.network.retrofit.ServiceGenerator;
import keren.movie.moviekeren.util.CustomToast;
import keren.movie.moviekeren.util.GridSpacingItemDecoration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<Movie>, MovieAdapter.ItemClickListener {

    private static final String TAG = "MainActivity";
    private static final int SPAN_COUNT = 2;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        initRecyclerView();
        getMovieList();
    }

    private void initRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, SPAN_COUNT);
        mRecyclerView.setLayoutManager(layoutManager);
        int gridSpacing = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(SPAN_COUNT, gridSpacing, true));
    }

    /**
     * Mendapatkan movie list dari network menggunakan retrofit
     */
    private void getMovieList() {
        final Call<Movie> call = ServiceGenerator
                .createService(MovieService.class)
                .getPopularMovies();

        call.enqueue(MainActivity.this);
    }

    private void showMovieList(List<Result> movieList) {
        MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this, movieList);
        mRecyclerView.setAdapter(movieAdapter);
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

    /**
     * Callback yang akan dipanggil ketika item di grid diklik
     *
     * @param position int posisi dari item yang diklik
     */
    @Override
    public void onItemClick(int position) {
        // TODO open detail activity
        CustomToast.show(MainActivity.this, "Item with position " + position + " clicked");
    }
}
