package com.giri.listview.views.movieslist;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.giri.listview.R;
import com.giri.listview.helpers.ConnectionStateMonitor;
import com.giri.listview.helpers.Utils;
import com.giri.listview.model.Movies;
import com.giri.listview.presenter.impl.MoviesPresenterImpl;
import com.giri.listview.presenter.interfaces.MoviesPresenter;
import com.giri.listview.views.description.MovieDescription;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements MoviesView, AdapterView.OnItemClickListener {

    private View rootView;
    private ListView listView;
    private ProgressDialog progressDialog;
    private MoviesPresenter presnter;
    private String[] title, releaseyear, rating, image;
    ArrayList<Movies> list = new ArrayList<>();
    private int clickcount = 1;

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_movies, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpViews();
    }

    private void setUpViews() {
        listView = (ListView) rootView.findViewById(R.id.list);
        progressDialog = new ProgressDialog(getContext());
        presnter = new MoviesPresenterImpl(this);
        listView.setOnItemClickListener(this);

        loadData();
    }

    private void showNoNetMessage() {
        Toast.makeText(getContext(), ConnectionStateMonitor.NO_INTERNET_ERROR, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
            progressDialog.setMessage(getResources().getString(R.string.loading_alert_message));
            progressDialog.setCancelable(false);
            progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showSuccess() {

    }


    @Override
    public void showError(String message) {

    }

    @Override
    public void loadSuccess(JSONArray response) {
        title = new String[response.length()];
        image = new String[response.length()];
        releaseyear = new String[response.length()];
        rating = new String[response.length()];

        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject jsonObject = response.getJSONObject(i);
                title[i] = jsonObject.getString("title");
                image[i] = jsonObject.getString("image");
                releaseyear[i] = jsonObject.getString("rating");
                rating[i] = jsonObject.getString("releaseYear");

                list.add(new Movies(title[i], image[i], releaseyear[i], rating[i]));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ArrayAdapter<Movies> adapter = new MoviesAdapter(getContext());
        listView.setAdapter(adapter);
    }

    @Override
    public void loadData() {
        if (ConnectionStateMonitor.checkInternetConnection(getContext())) {
            presnter.getListdata(getContext());
        } else {
            showNoNetMessage();
        }
    }

    public class MoviesAdapter extends ArrayAdapter<Movies> {
        public View itemview;

        public MoviesAdapter(Context context) {
            super(context, R.layout.item_list, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            itemview = convertView;

            // Check if an existing view is being reused, otherwise inflate the view
            if (itemview == null) {
                itemview = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
            }
            // Lookup view for data population
            final Movies movies = list.get(position);
            TextView movie_title = (TextView) itemview.findViewById(R.id.textMoviename);
            ImageView movie_img = (ImageView) itemview.findViewById(R.id.imageMovie);
            final TextView movie_release = (TextView) itemview.findViewById(R.id.releaseYear);
            final TextView movie_rating = (TextView) itemview.findViewById(R.id.rating);
            movie_title.setText(movies.getTitle());
            movie_img.setImageBitmap(Utils.getBitmapFromURL(movies.getImage()));
            final ImageButton more_less = (ImageButton) itemview.findViewById(R.id.imageLessmore);
            more_less.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            more_less.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickcount == 1) {
                        more_less.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                        movie_release.setVisibility(View.VISIBLE);
                        movie_rating.setVisibility(View.VISIBLE);
                        movie_release.setText(getResources().getString(R.string.release_year)+ movies.getReleaseyear());
                        movie_rating.setText(getResources().getString(R.string.rating) + movies.getRating());
                        clickcount = 0;

                    } else {
                        more_less.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                        movie_release.setVisibility(View.GONE);
                        movie_rating.setVisibility(View.GONE);
                        clickcount = 1;
                    }

                }
            });
            return itemview;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Fragment fragment = new MovieDescription();
        Bundle bundle = new Bundle();
        bundle.putString("Title", title[i]);
        bundle.putString("Theme", image[i]);
        bundle.putString("ReleaseYear", releaseyear[i]);
        bundle.putString("Rate", rating[i]);
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_main, fragment).commit();
    }
}
