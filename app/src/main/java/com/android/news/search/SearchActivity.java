package com.android.news.search;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.news.R;
import com.android.news.adapter.SearchAdapter;
import com.android.news.repo.SearchApi;
import com.android.news.searchmodel.ItemsItem;
import com.android.news.searchmodel.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class used for the search and load the search results in recyclerview
 */
public class SearchActivity extends Activity {
    private final String QUERY_PARAM_API_KEY = "AIzaSyCGTHMilAaxeKEn12Xzq99zYGjY7YYAurE";
    private final String QUERY_PARAM_CX = "d6359fc248fc6acdb";
    private final String BASE_URL = "https://www.googleapis.com/";
    EditText oseachquery;
    ImageButton oSearch;
    SearchActivity mContext;
    RecyclerView msearhResult;
    SearchApi mSearchApi;
    Map<String, String> query = new HashMap<>();
    Call<Response> mSearchResponse;
    View mView;
    private List<ItemsItem> getItems;
    private SearchAdapter mSearchAdapter;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getItems = new ArrayList<>();
        mSearchAdapter = new SearchAdapter(this, getItems);
        mContext = this;
        oseachquery = findViewById(R.id.searchquery);
        oSearch = findViewById(R.id.goearch);
        oseachquery.setHintTextColor(R.color.black);
        mView = findViewById(R.id.searchresult);
        msearhResult = mView.findViewById(R.id.recyclerView);
        msearhResult.setLayoutManager(new LinearLayoutManager(mContext));
        oSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(oseachquery.getText().toString()!=null && !oseachquery.getText().toString().isEmpty())
                {
                    makerequest(oseachquery.getText().toString());
                }else{
                    Toast.makeText(mContext,"Enter valid search",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    /**
     * This method used to access the query by google api.
     * @param mquery Used or the query formation in API
     */
    public void makerequest(String mquery) {
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
        query.put("q", mquery);
        query.put("key", QUERY_PARAM_API_KEY);
        query.put("cx", QUERY_PARAM_CX);
        query.put("num", "10");
        query.put("alt ", "json");

        mSearchResponse = mSearchApi.getSearchResponse(query);

        if (mSearchResponse != null & mSearchApi != null) {
            mSearchResponse.enqueue(new retrofit2.Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    if (response.isSuccessful()) {
                        Response mResponse = response.body();
                        assert mResponse != null;
                        getItems.addAll(mResponse.getItems());
                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Toast.makeText(mContext,"No internet connection",Toast.LENGTH_LONG).show();
                }
            });
        }
        mSearchAdapter.getAllItems(getItems);
        msearhResult.setAdapter(mSearchAdapter);

    }
}