package com.example.individual_assignment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.news> {

   
    Context context;
    List<Model> modelList;



    public Adapter(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

  

    @NonNull
    @Override
    public news onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contents, parent, false);
        news news = new news(view);
        return news;
    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull news holder, int position) {
        Model model = modelList.get(position);
        holder.title.setText( model.getTitle());
        holder.reporter.setText("Reporter: " + model.getReporter());
        holder.desc.setText(model.getDesc());
        holder.date.setText( model.getDate());
    }

  

    @Override
    public int getItemCount() {
        return modelList.size();
    }

 

    public static class news extends RecyclerView.ViewHolder {
        TextView title, reporter, desc, date;

        public news(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            reporter = itemView.findViewById(R.id.reporter);
            desc = itemView.findViewById(R.id.desc);
            date = itemView.findViewById(R.id.date);
        }
    }
}
