package com.android.news;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.news.adapter.HackerNewsAdapter;
import com.android.news.databinding.FragmentFirstBinding;
import com.android.news.model.HackerViewModel;
import com.android.news.model.Model;
import com.android.news.repo.HackNewsapi;
import com.android.news.repo.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class used to form the hacker news overall details
 */
public class FirstFragment extends Fragment {
    public static final String Base_URL = "https://hacker-news.firebaseio.com/";
    View root;
    Context mContext;
    SwipeRefreshLayout swipeRefreshLayout;
    HackNewsapi api;
    Call<List<Integer>> topstories;
    List<Integer> topStoriescountlist;
    int start, end, refresh = 1, size;
    LinearLayoutManager mLayoutManager;
    private FragmentFirstBinding binding;
    private HackerViewModel hackerViewModel;
    private List<Model> getCats;
    private HackerNewsAdapter HackerNewsAdapter;
    private RecyclerView recyclerView;
    private Repository repository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        return root;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        repository = new Repository(getActivity().getApplication());
        mContext = getContext();
        getCats = new ArrayList<>();
        recyclerView = root.findViewById(R.id.recyclerView);
        swipeRefreshLayout = root.findViewById(R.id.swipeRefreshLayout);
        mLayoutManager =
                new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false) {
                    @Override
                    public int computeVerticalScrollOffset(RecyclerView.State state) {
                        if (findFirstCompletelyVisibleItemPosition() == 0) {
                            return 0;
                        } else {
                            return super.computeVerticalScrollOffset(state);
                        }
                    }
                };
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        hackerViewModel = new ViewModelProvider(this).get(HackerViewModel.class);
        HackerNewsAdapter = new HackerNewsAdapter(mContext, getCats);
        HackerNewsAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                if (positionStart == 0 && positionStart == mLayoutManager.findFirstCompletelyVisibleItemPosition()) {
                    mLayoutManager.scrollToPosition(0);
                }
            }
        });

        makeRequest();
        hackerViewModel.getAllData().observe(getViewLifecycleOwner(), new Observer<List<Model>>() {
            @Override
            public void onChanged(List<Model> cats) {
                HackerNewsAdapter.getAllDatas(cats);
                recyclerView.setAdapter(HackerNewsAdapter);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                RearrangeItems(refresh++);
            }
        });
    }

    /**
     * Rearrange the data to top of the list
     *
     * @param count set the count based on the ie o topstories-id
     */
    public void RearrangeItems(int count) {
        start = count * 10;
        end = start + 10;
        if (topStoriescountlist != null) {
            int remain = topStoriescountlist.size() - end;
            if (start <= topStoriescountlist.size()) {
                if (end < remain) {
                    loadData(start, end);
                } else {
                    loadData(start, remain);
                }

            }
        }
        HackerNewsAdapter.notifyItemRangeInserted(0, 9);
        recyclerView.setAdapter(HackerNewsAdapter);

    }

    /**
     * Form request to access the data by retrofit
     * Get the list of itemid and based on the id retrive data for specific id
     */
    private void makeRequest() {
        Retrofit retrofit = null;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (retrofit == null) {
            OkHttpClient ok = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Base_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(ok.newBuilder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build())
                    .build();
        }
        api = retrofit.create(HackNewsapi.class);
        topstories = api.getTopStories();
        if (topstories != null & api != null) {
            topstories.enqueue(new retrofit2.Callback<List<Integer>>() {
                @Override
                public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                    topStoriescountlist = response.body();
                    size = topStoriescountlist.size();
                    loadData(0, 10);
                }

                @Override
                public void onFailure(Call<List<Integer>> call, Throwable t) {
                    Log.d("main", "onFailure: " + t.getMessage());
                    Toast.makeText(mContext, "No internet connection", Toast.LENGTH_LONG).show();

                }
            });
        }
    }

    /**
     * load the data by using the id and start and end index from the arraylist
     *
     * @param start start index of the arraylist
     * @param end   end index of the arraylist
     */

    public void loadData(int start, int end) {
        List<Model> mModel = new ArrayList<>();
        for (int i = start; i < end; i++) {
            int finalI = i;
            api.getArticle(topStoriescountlist.get(i)).enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {
                    if (response.isSuccessful()) {
                        Log.d("success", "title: " + response.body().getTitle() + "i[" + finalI + "]");
                        mModel.add(response.body());
                        repository.insert(mModel);
                    }

                }

                @Override
                public void onFailure(Call<Model> call, Throwable t) {
                    Log.d("TAG", "onFailure: of model");
                    Toast.makeText(mContext, "No internet connection", Toast.LENGTH_LONG).show();
                }
            });
        }
        HackerNewsAdapter.getAllDatas(mModel);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}


