package com.giri.listview.views.movieslist;

import org.json.JSONArray;

/**
 * Created by Giri on 13-Feb-18.
 */

public interface MoviesView {

    void showProgress();

    void hideProgress();

    void showSuccess();

    void showError(String message);

    void loadSuccess(JSONArray response);

    void loadData();


}
