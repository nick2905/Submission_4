package com.dicoding.submission_4.Model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dicoding.submission_4.BuildConfig;
import com.dicoding.submission_4.Item.TVShow;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TVShowModel extends ViewModel {
    private MutableLiveData<ArrayList<TVShow>> tvList = new MutableLiveData<>();

    public void listTVShow(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TVShow> tvItem = new ArrayList<>();
        client.get(BuildConfig.API_URL_TV, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String rTVShow = new String(responseBody);
                    JSONObject objTVShow = new JSONObject(rTVShow);
                    JSONArray LTVShow = objTVShow.getJSONArray("results");
                    for(int i = 0; i < LTVShow.length(); i++ ){
                        JSONObject tv = LTVShow.getJSONObject(i);
                        TVShow ITVShow = new TVShow(tv);
                        tvItem.add(ITVShow);
                    }
                    tvList.postValue(tvItem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public LiveData<ArrayList<TVShow>> getTVShow(){
        return tvList;
    }
}
