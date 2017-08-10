package keren.movie.moviekeren.activity;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import keren.movie.moviekeren.R;
import keren.movie.moviekeren.network.UrlComposer;
import keren.movie.moviekeren.network.model.Movie;

public class DetailActivity extends AppCompatActivity {

    public static final String MOVIE_KEY = "movie";

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
    public boolean onOptionsItemSelected(MenuItem item) {
        int actionId = item.getItemId();

        // Handle back button click
        if (actionId == android.R.id.home) {
            // kembali ke parent Activity
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        return false;
    }
}
