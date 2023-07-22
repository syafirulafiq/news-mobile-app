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

//create a class name it "adapter"

public class Adapter extends RecyclerView.Adapter<Adapter.news> {

    //then, create objects like this

    Context context;
    List<Model> modelList;

    //then create adapter constructor

    public Adapter(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    //then, here you link the contents file that has views

    @NonNull
    @Override
    public news onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contents, parent, false);
        news news = new news(view);
        return news;
    }

    //then Bind your content with Model class

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull news holder, int position) {
        Model model = modelList.get(position);
        holder.title.setText( model.getTitle());
        holder.reporter.setText("Reporter: " + model.getReporter());
        holder.desc.setText(model.getDesc());
        holder.date.setText( model.getDate());
    }

    //now, get number of items at a specific time

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    //now find your views

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
