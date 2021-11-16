package com.android.news.repo;

import com.android.news.searchmodel.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface SearchApi {
    @GET("customsearch/v1")
    Call<Response> getSearchResponse(@QueryMap Map<String, String> options);

}
