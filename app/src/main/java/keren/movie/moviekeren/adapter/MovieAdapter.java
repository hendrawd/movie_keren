package keren.movie.moviekeren.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import keren.movie.moviekeren.R;
import keren.movie.moviekeren.network.UrlComposer;
import keren.movie.moviekeren.network.model.Result;

/**
 * @author hendrawd on 8/5/17
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    private List<Result> movieList;
    private ItemClickListener itemClickListener;

    public MovieAdapter(@NonNull ItemClickListener itemClickListener,
                        @NonNull List<Result> movieList) {
        this.movieList = movieList;
        this.itemClickListener = itemClickListener;
    }

    /**
     * Method dimana ViewHolder dibuat
     *
     * @param parent   ViewGroup dimana View akan ditempatkan
     * @param viewType int tipe dari View, akan dipakai jika ingin satu RecyclerView memiliki beberapa
     *                 tipe View
     * @return ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Method dimana ViewHolder diberi data untuk posisi tertentu
     *
     * @param holder   ViewHolder yang mengandung reference dari view yang digunakan kembali(re-use)
     * @param position int posisi dari data
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Context context = holder.itemView.getContext();
        Result data = movieList.get(position);
        holder.tvTitle.setText(data.getTitle());
        Picasso.with(context)
                .load(
                        UrlComposer.getPosterUrl(
                                data.getPosterPath()
                        )
                )
                .into(holder.ivPoster);
    }

    /**
     * Method yang mengembalikan banyaknya data
     *
     * @return int jumlah data
     */
    @Override
    public int getItemCount() {
        return movieList.size();
    }

    /**
     * Kelas yang digunakan untuk menyimpan penunjuk(reference) dari View-View yang dibuat di XML
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView ivPoster;
        private TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPoster = (ImageView) itemView.findViewById(R.id.iv_poster);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final int position = getAdapterPosition();
            itemClickListener.onItemClick(position);
        }
    }
}
