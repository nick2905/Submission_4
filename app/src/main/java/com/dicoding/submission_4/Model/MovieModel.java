package com.dicoding.submission_4.Model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dicoding.submission_4.BuildConfig;
import com.dicoding.submission_4.Item.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieModel extends ViewModel {
    private MutableLiveData<ArrayList<Movie>> mvList = new MutableLiveData<>();

    public void listMovie(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> mvItem = new ArrayList<>();
        client.get(BuildConfig.API_URL_MOVIE, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String rMovie = new String(responseBody);
                    JSONObject objMov = new JSONObject(rMovie);
                    JSONArray LMovie = objMov.getJSONArray("results");
                    for(int i = 0; i < LMovie.length(); i++){
                        JSONObject mv = LMovie.getJSONObject(i);
                        Movie IMovie = new Movie(mv);
                        mvItem.add(IMovie);
                    }
                    mvList.postValue(mvItem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public LiveData<ArrayList<Movie>> getMovie(){
        return mvList;
    }
}
