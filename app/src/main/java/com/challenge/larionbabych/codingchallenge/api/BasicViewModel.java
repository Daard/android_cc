package com.challenge.larionbabych.codingchallenge.api;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.challenge.larionbabych.codingchallenge.utils.LogUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BasicViewModel<T> extends ViewModel {

    private MutableLiveData<T> data = new MutableLiveData<>();
    private final Api apiClient;
    private final LogUtil logUtil;

    protected BasicViewModel(Api apiClient, LogUtil logUtil) {
        this.apiClient = apiClient;
        this.logUtil = logUtil;
    }

    protected Api getApiClient(){
        return apiClient;
    }

    protected Callback<T> prepareCallback() {
        return new Callback<T>() {
            @Override
            public void onResponse(Call<T> call,
                                   Response<T> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    logUtil.log(LogUtil.type.d, "simpleRequest error:", response.message());
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<T> call, Throwable t) {
                logUtil.log(LogUtil.type.d, "simpleRequest failure:", t.getMessage());
            }

        };
    }

    public MutableLiveData<T> getData() {
        return data;
    }

}
