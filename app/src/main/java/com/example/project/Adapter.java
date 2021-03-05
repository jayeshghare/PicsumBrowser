package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {



    LayoutInflater inflater;
    List<Data> data;

    public Adapter(Context ctx, List<Data> data){
        this.data=data;
        this.inflater = LayoutInflater.from(ctx);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_grid_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.author_name.setText(data.get(position).getAuthor());
        Picasso.get().load(data.get(position).getUrl()).into(holder.imageview);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView author_name;
        ImageView imageview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            author_name = itemView.findViewById(R.id.textView2);
            imageview = itemView.findViewById(R.id.imageView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Image Number " + (getAdapterPosition()+1), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
