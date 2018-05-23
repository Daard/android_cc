package com.challenge.larionbabych.codingchallenge.a_main;

import com.challenge.larionbabych.codingchallenge.api.Api;
import com.challenge.larionbabych.codingchallenge.api.BasicViewModel;
import com.challenge.larionbabych.codingchallenge.model.Geometry;
import com.challenge.larionbabych.codingchallenge.model.PlacesResponse;
import com.challenge.larionbabych.codingchallenge.utils.LogUtil;
import javax.inject.Inject;

public class PlacesViewModel extends BasicViewModel<PlacesResponse> {

    @Inject
    PlacesViewModel(Api apiClient, LogUtil logUtil) {
       super(apiClient, logUtil);
    }

    void requestPlacesByDistance(Geometry.Location location) {
        getApiClient().getByDistance(location,"distance", "restaurant", Api.KEY).
                enqueue(prepareCallback());
    }

    void requestPlacesByRating(Geometry.Location location){
        getApiClient().getByRating(location,"prominence", 2500,"restaurant", Api.KEY).
                enqueue(prepareCallback());
    }

}
