package com.android.news.repo;

import com.android.news.model.Model;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HackNewsapi {
    @GET("v0/topstories.json?print=pretty")
   //  @GET("search")
    Call<List<Integer>> getTopStories();
    @GET("v0/item/{articleid}.json?print=pretty")
    Call<Model> getArticle(@Path("articleid") int id);


}
