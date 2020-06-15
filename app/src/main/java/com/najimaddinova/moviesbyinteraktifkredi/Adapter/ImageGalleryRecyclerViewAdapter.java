package com.najimaddinova.moviesbyinteraktifkredi.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.najimaddinova.moviesbyinteraktifkredi.Common.Configuration;
import com.najimaddinova.moviesbyinteraktifkredi.Enum.ImageSizes;
import com.najimaddinova.moviesbyinteraktifkredi.Model.ImageModel;
import com.najimaddinova.moviesbyinteraktifkredi.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageGalleryRecyclerViewAdapter extends RecyclerView.Adapter<ImageGalleryRecyclerViewAdapter.MyViewHolder> {

    List<ImageModel.PosterModel> mData;

    public ImageGalleryRecyclerViewAdapter(List<ImageModel.PosterModel> mData)
    {
        this.mData = mData;
    }

    //Bu method adaptör oluşturulduğunda oluşturduğumuz ViewHolder'ın başlatılması için çağrılır.
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_gallery_fragment, parent, false);
        return new MyViewHolder(v);
    }
    //onCreateViewHolder’dan dönen verileri bağlama işlemini gerçekleştirildiği metotdur.
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(Configuration.getImageBaseUrl(ImageSizes.POSTER, mData.get(position).filePath)).into(holder.poster);
    }
//Listemizin eleman sayısını döndüren metottur.
    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    public void newAddedData(List<ImageModel.PosterModel> posters) {
        mData.addAll(posters);
        notifyDataSetChanged();
    }

    //Her bir satırımızın içinde bulunan bileşenleri tanımlama işleminin yapıldığı metottur.
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public final ImageView poster;

        public MyViewHolder(View view) {
            super(view);

            poster = view.findViewById(R.id.poster);
        }
    }


}
