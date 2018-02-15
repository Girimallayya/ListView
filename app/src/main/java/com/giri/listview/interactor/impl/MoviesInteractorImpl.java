package com.giri.listview.interactor.impl;

import android.os.AsyncTask;
import com.giri.listview.helpers.ApiConstants;
import com.giri.listview.interactor.interfaces.MoviesInteractor;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Giri on 13-Feb-18.
 */

public class MoviesInteractorImpl implements MoviesInteractor {
    OnDataSyncListener listener;

    @Override
    public void syncData(OnDataSyncListener listener) {
        new RetrieveFeedTask().execute();
        this.listener = listener;
    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, JSONArray> {

        protected void onPreExecute() {

        }

        protected JSONArray doInBackground(Void... urls) {

            try {
                URL url = new URL(ApiConstants.url);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    JSONArray jsonArray = new JSONArray(stringBuilder.toString());
                    listener.onSyncComplete();
                    return jsonArray;
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                return null;
            }
        }

        protected void onPostExecute(JSONArray response) {
            super.onPostExecute(response);
            listener.onSuccess(response);
        }
    }
}