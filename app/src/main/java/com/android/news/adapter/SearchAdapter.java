package com.android.news.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.news.R;
import com.android.news.model.Model;
import com.android.news.searchmodel.ItemsItem;
import com.android.news.utils.TimeConvertion;
import com.bumptech.glide.Glide;

import java.net.URISyntaxException;
import java.util.List;

/**
 * This class used to load the search filter results
 */
public class SearchAdapter  extends RecyclerView.Adapter<SearchAdapter.SearchHolder>{

    private Context context;
    private List<ItemsItem> cats;

    public SearchAdapter(Context context, List<ItemsItem> cats) {
        this.context = context;
        this.cats = cats;
    }

    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder holder, int position) {
        Log.d("TAG", "onBindViewHolder: ");
        ItemsItem cat=cats.get(position);
        if(cat!=null) {


            holder.oName.setText(cat.getTitle());
            holder.oDescription.setText(cat.getSnippet());
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

    public void getAllItems(List<ItemsItem> cats)
    {
        this.cats=cats;
    }

    public static class SearchHolder extends RecyclerView.ViewHolder
    {
        TextView oName,oDescription;
        ImageView oImage;
        CardView mView;
        public SearchHolder(@NonNull View itemView) {
            super(itemView);
            oName=itemView.findViewById(R.id.searchtitle);
            mView=itemView.findViewById(R.id.item);
            oDescription=itemView.findViewById(R.id.snippet);
        }
    }

}