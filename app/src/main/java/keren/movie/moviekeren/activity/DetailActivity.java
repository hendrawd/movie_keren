package keren.movie.moviekeren.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import keren.movie.moviekeren.R;
import keren.movie.moviekeren.network.UrlComposer;
import keren.movie.moviekeren.network.model.Movie;
import keren.movie.moviekeren.network.model.Review;
import keren.movie.moviekeren.network.model.ReviewResponse;
import keren.movie.moviekeren.network.model.Video;
import keren.movie.moviekeren.network.model.VideoResponse;
import keren.movie.moviekeren.network.retrofit.MovieService;
import keren.movie.moviekeren.network.retrofit.ServiceGenerator;
import keren.movie.moviekeren.util.CustomToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";
    public static final String MOVIE_KEY = "movie";

    private Call<VideoResponse> mRequestVideoCall;
    private Call<ReviewResponse> mRequestReviewCall;

    @BindView(R.id.tv_original_title)
    TextView tvOriginalTitle;
    @BindView(R.id.iv_poster_image)
    ImageView ivPosterImage;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.tv_rating)
    TextView tvRating;
    @BindView(R.id.tv_release_date)
    TextView tvReleaseDate;
    @BindView(R.id.ll_video_container)
    LinearLayout llVideoContainer;
    @BindView(R.id.ll_review_container)
    LinearLayout llReviewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setupBackButton();

        // mendapatkan data yang dipassing dari MainActivity
        Movie data = getIntent().getParcelableExtra(MOVIE_KEY);

        // set text ke original title
        tvOriginalTitle.setText(data.getOriginalTitle());

        // set text ke deskripsi
        tvDescription.setText(data.getOverview());

        // set text ke rata-rata vote
        double voteAverage = data.getVoteAverage();
        double maxVote = 10;
        tvRating.setText(voteAverage + "/" + maxVote);

        // set text ke tanggal rilis
        tvReleaseDate.setText(data.getReleaseDate());

        // set image poster
        Picasso.with(this)
                .load(
                        UrlComposer.getPosterUrl(
                                data.getPosterPath()
                        )
                )
                .into(ivPosterImage);

        requestVideoList(data.getId());
        requestReviewList(data.getId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // cancelling calls if activity already destroyed, to avoid activity leak
        // cancelling calls if activity already destroyed, to avoid activity leak
        mRequestVideoCall.cancel();
        mRequestReviewCall.cancel();
    }

    /**
     * Membuat back button
     */
    private void setupBackButton() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int actionId = item.getItemId();

        // Handle back button click
        switch (actionId) {
            case android.R.id.home: {
                // kembali ke parent Activity
                NavUtils.navigateUpFromSameTask(this);
                return true;
            }
            case R.id.action_favorite: {
                CustomToast.show(this, "Action favorite clicked!");
                return true;
            }
        }

        return false;
    }

    /**
     * Mendapatkan video list dari network menggunakan retrofit
     */
    private void requestVideoList(Integer movieId) {
        mRequestVideoCall = ServiceGenerator
                .createService(MovieService.class)
                .getVideos(movieId);

        mRequestVideoCall.enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(@NonNull Call<VideoResponse> call, @NonNull Response<VideoResponse> response) {
                VideoResponse videoResponse = response.body();
                if (videoResponse != null) {
                    List<Video> videoList = videoResponse.getResults();
                    for (Video video : videoList) {
                        Log.d(TAG, video.getName());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<VideoResponse> call, @NonNull Throwable t) {
                // TODO handle request failure
            }
        });
    }

    /**
     * Mendapatkan review list dari network menggunakan retrofit
     */
    private void requestReviewList(Integer movieId) {
        mRequestReviewCall = ServiceGenerator
                .createService(MovieService.class)
                .getReviews(movieId);

        mRequestReviewCall.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(@NonNull Call<ReviewResponse> call, @NonNull Response<ReviewResponse> response) {
                ReviewResponse reviewResponse = response.body();
                if (reviewResponse != null) {
                    List<Review> reviewList = reviewResponse.getResults();
                    for (Review review : reviewList) {
                        Log.d(TAG, review.getContent());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ReviewResponse> call, @NonNull Throwable t) {
                // TODO handle request failure
            }
        });
    }

}
