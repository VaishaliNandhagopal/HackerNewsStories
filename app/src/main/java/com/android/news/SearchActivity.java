package com.android.news;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.news.repo.SearchApi;
import com.android.news.searchmodel.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    EditText oseachquery;
    ImageButton oSearch;
    RecyclerView msearhResult;
    SearchApi mSearchApi;
    Map<String, String> query = new HashMap<>();
    Call<Response> mSearchResponse;

    View mView;
    private final String QUERY_PARAM_API_KEY = "AIzaSyCGTHMilAaxeKEn12Xzq99zYGjY7YYAurE";
    private final String QUERY_PARAM_CX = "d6359fc248fc6acdb";
    private final String BASE_URL = "https://www.googleapis.com/";

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        oseachquery = findViewById(R.id.searchquery);
        oSearch = findViewById(R.id.goearch);
        oseachquery.setHintTextColor(R.color.black);
        mView = findViewById(R.id.searchresult);
        msearhResult = mView.findViewById(R.id.recyclerView);
        oSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makerequest();
            }
        });
    }

    public void makerequest() {
        Retrofit retrofit = null;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (retrofit == null) {
            OkHttpClient ok = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(ok.newBuilder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build())
                    .build();
        }
        mSearchApi = retrofit.create(SearchApi.class);
        query.put("q", "love");
        query.put("key", QUERY_PARAM_API_KEY);
        query.put("cx", QUERY_PARAM_CX);
        query.put("num", "10");
        query.put("alt ", "json");

        mSearchResponse = mSearchApi.getSearchResponse(query);

        if (mSearchResponse != null & mSearchApi != null) {
            mSearchResponse.enqueue(new retrofit2.Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Log.d("main", "onFailure: " + t.getMessage());
                }
            });
        }
    }
}