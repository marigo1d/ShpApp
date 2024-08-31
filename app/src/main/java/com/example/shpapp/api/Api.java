package com.example.shpapp.api;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Api {
    private static String mRequestUrl;
    private static HashMap<String, Object> mParams;
    private static OkHttpClient mClient;

//    public static Api api = new Api();
//
//    private Api() {
//
//    }

    public static void config(String url, HashMap<String, Object> params) {
        mClient = new OkHttpClient.Builder().build();
        mRequestUrl = ApiConfig.BASE_URL + url;
        mParams = params;
//        return api;
    }

    public static void postRequest(TtitCallback callback) {
        JSONObject jsonObject = new JSONObject(mParams);
        String jsonStr = jsonObject.toString();
        RequestBody requestBodyJson = RequestBody.create(MediaType.parse("application/json;charset=utf-8")
                , jsonStr);

        Request request = new Request.Builder()
                .url(mRequestUrl)
                .addHeader("contentType", "application/json;charset=UTF-8")
                .post(requestBodyJson)
                .build();

        final Call call = mClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                assert response.body() != null;
                callback.onSuccess(response.body().string());
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e.getMessage());
            }
        });
    }
}
