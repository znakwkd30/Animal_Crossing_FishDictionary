package kr.hs.dgsw.animal_crossingfish_application;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kr.hs.dgsw.animal_crossingfish_application.data.FishData;

/**
 * Created by NA on 2020-07-04
 * skehdgur8591@naver.com
 */
public class FishListAdapter extends RecyclerView.Adapter<FishListAdapter.ViewHolder>{
    private ArrayList<FishData> mData = null;

    public FishListAdapter(ArrayList<FishData> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String text = mData.get(position).getName();
//        Drawable drawable = mData.get(position).getImageUrl();

        holder.nameTextView.setText(text);
//        holder.imageView.setImageURI(mData.get(position).getImageUrl());
//        holder.imageView.setImageDrawable(drawable);
        Glide.with(holder.itemView.getContext()).load(mData.get(position).getImageUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView imageView;

        ViewHolder (View view) {
            super(view);
            nameTextView = view.findViewById(R.id.name);
            imageView = view.findViewById(R.id.img);
        }
    }


}
