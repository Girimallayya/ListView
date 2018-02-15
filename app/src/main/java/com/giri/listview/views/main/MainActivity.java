package com.giri.listview.views.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.giri.listview.R;
import com.giri.listview.views.description.MovieDescription;
import com.giri.listview.views.movieslist.MoviesFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_main, new MoviesFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.frame_layout_main);

        if (fragment instanceof MovieDescription) {
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        } else if (fragment instanceof MoviesFragment) {
            super.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }
}
