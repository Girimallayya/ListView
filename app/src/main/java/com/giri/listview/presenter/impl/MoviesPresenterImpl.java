package com.giri.listview.presenter.impl;

import android.content.Context;

import com.giri.listview.interactor.impl.MoviesInteractorImpl;
import com.giri.listview.interactor.interfaces.MoviesInteractor;
import com.giri.listview.presenter.interfaces.MoviesPresenter;
import com.giri.listview.views.movieslist.MoviesView;

import org.json.JSONArray;

/**
 * Created by Giri on 13-Feb-18.
 */

public class MoviesPresenterImpl implements MoviesPresenter, MoviesInteractor.OnDataSyncListener {

    private MoviesView moviesView;
    private MoviesInteractor moviesInteractor;

    public MoviesPresenterImpl(MoviesView moviesView) {
        this.moviesView = moviesView;
        this.moviesInteractor = new MoviesInteractorImpl();

    }

    @Override
    public void getListdata(Context context) {
        moviesInteractor.syncData(this);
    }

    @Override
    public void onLoading() {
        if (moviesView != null) {
            moviesView.showProgress();
        }
    }

    @Override
    public void onSuccess(JSONArray response) {
        if (moviesView != null) {
            moviesView.loadSuccess(response);
        }
    }


    @Override
    public void onError(String message) {
        if (moviesView != null) {
            moviesView.hideProgress();
            moviesView.showError(message);
        }
    }

    @Override
    public void onSyncComplete() {
        if (moviesView != null) {
            moviesView.hideProgress();
            moviesView.showSuccess();
        }
    }
}
