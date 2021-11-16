package com.android.news;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.android.news.searchmodel.Context;
import com.android.news.utils.TimeConvertion;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.android.news.databinding.ActivityMainBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;

import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

   // private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    MainActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        mActivity=this;
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        firebaseDatabase.setLogLevel(Logger.Level.DEBUG);

//        try {
//            String name = TimeConvertion.getDomainName("https://timesofe.com/how-michigan-grew-its-startup-ecosystem-and-became-the-state-with-the-fastest-growing-venture-capital/");
//            Log.d("TAG", "onCreate: "+name);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
      /*   FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId(FIREBASE_APPLICATION_ID)
                .setProjectId(FIREBASE_PROJECT_ID)
                .setGcmSenderId(FIREBASE_GCM_SENDER_ID)
                .setApiKey(FIREBASE_APIKEY_ID)
                .build();

        FirebaseApp.initializeApp( getApplicationContext(), options);*/
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent search =new Intent(mActivity,SearchActivity.class);
                startActivity(search);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return  super.onSupportNavigateUp();
    }
}