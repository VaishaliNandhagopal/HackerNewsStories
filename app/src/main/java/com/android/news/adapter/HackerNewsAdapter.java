package com.android.news.adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.news.R;
import com.android.news.model.Model;
import com.android.news.utils.TimeConvertion;

import java.net.URISyntaxException;
import java.util.List;

public class HackerNewsAdapter extends RecyclerView.Adapter<HackerNewsAdapter.HackerNewsHolder>{

    private Context context;
    private List<Model> cats;

    public HackerNewsAdapter(Context context, List<Model> cats) {
        this.context = context;
        this.cats = cats;
    }

    @NonNull
    @Override
    public HackerNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HackerNewsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HackerNewsHolder holder, int position) {
        Log.d("TAG", "onBindViewHolder: ");
        Model cat=cats.get(position);
        if(cat!=null) {
            Log.d("TAG", "onBindViewHolder: " + TimeConvertion.toDuration(cat.getTime()));
            holder.otitle.setText(cat.getTitle());
            try {
                if(cat.getUrl()!=null)
                holder.oUrl.setText("www." + TimeConvertion.getDomainName(cat.getUrl()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            holder.oUrl.getPaint().setUnderlineText(true);
            holder.oBy.setText("By : " + cat.getBy() + " | " + TimeConvertion.toDuration(cat.getTime()));
            if (cat.getDescendants()!=null && cat.getDescendants() != 0 ) {
                holder.oLike.setText(cat.getDescendants().toString());
            } else {
                holder.oLike.setText("0");
            }
            if (cat.getScore()!=null && cat.getScore() != 0) {
                holder.oComment.setText(cat.getScore().toString());
            } else {
                holder.oComment.setText("0");
            }
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    public void getAllDatas(List<Model> cats)
    {
        this.cats=cats;
    }

    public static class HackerNewsHolder extends RecyclerView.ViewHolder
    {
        TextView otitle,oUrl,oBy,oComment,oLike;
        CardView mView;
        public HackerNewsHolder(@NonNull View itemView) {
            super(itemView);
            otitle=itemView.findViewById(R.id.title);
            mView=itemView.findViewById(R.id.item);
            oUrl=itemView.findViewById(R.id.url);
            oBy=itemView.findViewById(R.id.time_author);
            oLike=itemView.findViewById(R.id.likecount);
            oComment=itemView.findViewById(R.id.commentcount);
        }
    }

}