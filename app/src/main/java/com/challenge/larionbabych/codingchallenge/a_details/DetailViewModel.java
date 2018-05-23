package com.challenge.larionbabych.codingchallenge.a_details;

import com.challenge.larionbabych.codingchallenge.api.Api;
import com.challenge.larionbabych.codingchallenge.api.BasicViewModel;
import com.challenge.larionbabych.codingchallenge.model.PlaceResponse;
import com.challenge.larionbabych.codingchallenge.utils.LogUtil;
import javax.inject.Inject;

public class DetailViewModel extends BasicViewModel<PlaceResponse> {

    @Inject
    DetailViewModel(Api apiClient, LogUtil logUtil) {
        super(apiClient, logUtil);
    }

    void requestById(String placeId) {
        getApiClient().getById(placeId, Api.KEY).
                enqueue(prepareCallback());
    }

}
