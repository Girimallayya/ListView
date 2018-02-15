package com.giri.listview.interactor.interfaces;

import org.json.JSONArray;

/**
 * Created by Giri on 13-Feb-18.
 */

public interface MoviesInteractor {

    void syncData(OnDataSyncListener listener);

    interface OnDataSyncListener {
        void onLoading();

        void onSuccess(JSONArray response);

        void onError(String message);

        void onSyncComplete();
    }
}
