package keren.movie.moviekeren;

import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author hendrawd on 7/30/17
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<Movie> data;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPoster;
        private TextView tvTitle;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
