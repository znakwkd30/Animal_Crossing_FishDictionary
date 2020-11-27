package kr.hs.dgsw.animal_crossingfish_application;

import android.content.Context;
import android.content.Intent;
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
    private ArrayList<FishData> mData;
    private Context context;

    public FishListAdapter(ArrayList<FishData> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String text = mData.get(position).getName();

        holder.nameTextView.setText(text);
        Glide.with(holder.itemView.getContext()).load(mData.get(position).getImageUrl()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FishDetailActivity.class);
                intent.putExtra("fishInfo", mData.get(position));
                context.startActivity(intent);
            }
        });
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
