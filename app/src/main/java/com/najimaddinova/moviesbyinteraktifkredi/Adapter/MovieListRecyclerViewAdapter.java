package com.najimaddinova.moviesbyinteraktifkredi.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.najimaddinova.moviesbyinteraktifkredi.Common.Configuration;
import com.najimaddinova.moviesbyinteraktifkredi.Enum.ImageSizes;
import com.najimaddinova.moviesbyinteraktifkredi.Listeners.OnItemClickListener;
import com.najimaddinova.moviesbyinteraktifkredi.Model.MovieSummaryModel;
import com.najimaddinova.moviesbyinteraktifkredi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieListRecyclerViewAdapter extends RecyclerView.Adapter<MovieListRecyclerViewAdapter.MyViewHolder> implements Filterable{

    private List<MovieSummaryModel> movieSummaryList;
    private List<MovieSummaryModel> mFilterList;
    private final OnItemClickListener listener;

    public MovieListRecyclerViewAdapter(List<MovieSummaryModel> filterList, OnItemClickListener listener) {
        this.movieSummaryList = filterList;
        this.mFilterList = filterList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Picasso.get().load(Configuration.getImageBaseUrl(ImageSizes.THUMB, mFilterList.get(position).posterPath)).into(holder.movieCover);
        holder.txtMovieName.setText(mFilterList.get(position).title);
        holder.txtMovieDate.setText(mFilterList.get(position).releaseDate);
        holder.bind(mFilterList.get(position), listener);
    }
    @Override
    public int getItemCount() {
        if (mFilterList == null) {
            return 0;
        }
        return mFilterList.size();
    }

    public void newAddedData(List<MovieSummaryModel> movies) {
        movieSummaryList.addAll(movies);
        notifyDataSetChanged();
    }

    public void refreshAndFill(List<MovieSummaryModel> movies) {
        movieSummaryList.clear();
        movieSummaryList.addAll(movies);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public final ImageView movieCover;
        public final TextView txtMovieName, txtMovieDate;

        public MyViewHolder(View view) {
            super(view);
            movieCover = view.findViewById(R.id.movieCover);
            txtMovieName = view.findViewById(R.id.txtNameMovie);
            txtMovieDate = view.findViewById(R.id.txtDateMovie);
        }

        public void bind(final MovieSummaryModel item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClicked(item);
                }
            });
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilterList = movieSummaryList;
                } else {
                    List<MovieSummaryModel> filteredList = new ArrayList<>();
                    for (MovieSummaryModel row : movieSummaryList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase()) || row.getOriginalTitle().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    mFilterList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilterList = (ArrayList<MovieSummaryModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
