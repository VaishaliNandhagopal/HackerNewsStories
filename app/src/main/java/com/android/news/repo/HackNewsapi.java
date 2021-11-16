package com.android.news.repo;

import com.android.news.model.Model;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * This is used to form the queries and retrieve the data in form of json
 */
public interface HackNewsapi {
    @GET("v0/topstories.json?print=pretty")
    Call<List<Integer>> getTopStories();
    @GET("v0/item/{articleid}.json?print=pretty")
    Call<Model> getArticle(@Path("articleid") int id);


}
